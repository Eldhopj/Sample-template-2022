package com.eldhopj.myapplication.utils.bases.baseRepository

import com.eldhopj.myapplication.data.remote.ErrorData
import com.eldhopj.myapplication.data.remote.Result
import java.io.IOException
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.retry
import retrofit2.Response

class SafeApiCall : ApiCall {

    override fun <T> safeApiCall(
        dispatcher: CoroutineDispatcher,
        apiCall: suspend () -> Response<T>
    ): Flow<Result<T>> = flow {
        emit(Result.Loading(true))
        val response = apiCall()
        emit(Result.Loading(false))
        if (response.isSuccessful) {
            val data = response.body()
            if (data != null) {
                emit(Result.Success(data))
            } else {
                val errorCode = response.code()
                val error = response.errorBody()
                if (error != null) {
                    emit(Result.Error(ErrorData(errorCode, error.toString())))
                } else {
                    emit(Result.Error(ErrorData(errorCode)))
                }
            }
        } else {
            emit(Result.Error(ErrorData(response.code(), response.message())))
        }
    }.retry(retries = 3) { cause ->
        if (cause is IOException) {
            delay(500)
            return@retry true
        } else {
            return@retry false
        }
    }.catch { e ->
        emit(Result.Loading(false))
        emit(Result.Exception(e))
    }.flowOn(dispatcher)
}

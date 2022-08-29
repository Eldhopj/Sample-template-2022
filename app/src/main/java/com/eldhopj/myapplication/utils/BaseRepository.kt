package com.eldhopj.myapplication.utils

import com.eldhopj.myapplication.data.remote.ErrorData
import com.eldhopj.myapplication.data.remote.Result
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.Response

/**
 * Base repository
 *
 * @constructor Create empty Base repository
 */
open class BaseRepository {


    /**
     * Safe d b call
     *
     * @param T  data type
     * @param dispatcher CoroutineDispatcher
     * @param dbCall roomDb call
     * @receiver
     * @return
     */
    fun <T> safeDBCall(
        dispatcher: CoroutineDispatcher = Dispatchers.IO,
        dbCall: suspend () -> T
    ): Flow<Result<T>> = flow {
        emit(Result.Loading(true))
        emit(Result.Success(dbCall.invoke()))
        emit(Result.Loading(false))
    }.catch { e ->
        emit(Result.Loading(false))
        emit(Result.Exception(e))
    }.flowOn(dispatcher)

    /**
     * Safe api call
     *
     * @param T Response data type
     * @param dispatcher CoroutineDispatcher
     * @param apiCall api Call
     * @receiver
     * @return
     */
    fun <T> safeApiCall(
        dispatcher: CoroutineDispatcher = Dispatchers.IO,
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
    }.catch { e ->
        emit(Result.Loading(false))
        emit(Result.Exception(e))
    }.flowOn(dispatcher)
}

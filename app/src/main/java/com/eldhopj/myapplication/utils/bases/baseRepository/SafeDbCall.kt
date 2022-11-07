package com.eldhopj.myapplication.utils.bases.baseRepository

import com.eldhopj.myapplication.data.remote.Result
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class SafeDbCall: DbCall {
    override fun <T> safeDBCall(
        dispatcher: CoroutineDispatcher,
        dbCall: suspend () -> T
    ): Flow<Result<T>> = flow {
        emit(Result.Loading(true))
        emit(Result.Success(dbCall.invoke()))
        emit(Result.Loading(false))
    }.catch { e ->
        emit(Result.Loading(false))
        emit(Result.Exception(e))
    }.flowOn(dispatcher)
}

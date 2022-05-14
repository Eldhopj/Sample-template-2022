package com.eldhopj.myapplication.data.repositories

import com.eldhopj.myapplication.data.remote.NetworkResponse
import com.eldhopj.myapplication.data.remote.handler.EveryThingApiHandler
import com.eldhopj.myapplication.domain.model.News
import com.eldhopj.myapplication.domain.utils.constants.StringConstants
import javax.inject.Inject
import javax.inject.Singleton
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

/**
 * Api repo
 *
 * @property everyThingApiHandler
 * @constructor Create empty Api repo
 */
@Singleton
class EveryThingApiRepo @Inject constructor(private val everyThingApiHandler: EveryThingApiHandler) {

    /**
     * Fetch news
     *
     * @param query
     * @param sortBy
     * @return
     */
    fun fetchNews(query: String, sortBy: String): Flow<NetworkResponse<News>> = flow {
        emit(NetworkResponse.Loading)
        val response = everyThingApiHandler.news(query, sortBy)
        if (response.isSuccessful) {
            emit(NetworkResponse.Success(response.body()))
        } else {
            // in real project handle response.errorBody(), if backend provides
            emit(NetworkResponse.Error(response.message() ?: StringConstants.UNKNOWN_ERROR))
        }
    }.flowOn(Dispatchers.IO)
}

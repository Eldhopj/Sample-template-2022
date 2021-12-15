package com.eldhopj.myapplication.ui.repositories

import com.eldhopj.myapplication.api.handler.EveryThingApiHandler
import com.eldhopj.myapplication.model.News
import com.eldhopj.myapplication.utils.constants.StringConstants
import com.eldhopj.myapplication.utils.network.NetworkResponse
import javax.inject.Inject
import javax.inject.Singleton
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

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
    suspend fun fetchNews(query: String, sortBy: String): Flow<NetworkResponse<News>> = flow {
        emit(NetworkResponse.Loading)
        val response = everyThingApiHandler.news(query, sortBy)
        if (response.isSuccessful) {
            emit(NetworkResponse.Success(response.body()))
        } else {
            // in real project handle response.errorBody(), if backend provides
            emit(NetworkResponse.Error(response.message() ?: StringConstants.UNKNOWN_ERROR))
        }
    }
}

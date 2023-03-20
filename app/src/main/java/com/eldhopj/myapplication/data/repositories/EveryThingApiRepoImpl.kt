package com.eldhopj.myapplication.data.repositories

import com.eldhopj.myapplication.data.remote.Output
import com.eldhopj.myapplication.data.remote.handler.EveryThingApiHandler
import com.eldhopj.myapplication.domain.model.response.NewsResponse
import com.eldhopj.myapplication.utils.bases.baseRepository.ApiCall
import com.eldhopj.myapplication.utils.bases.baseRepository.SafeApiCall
import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow

/**
 * Api repo
 *
 * @property everyThingApiHandler
 * @constructor Create empty Api repo
 */
@ViewModelScoped
class EveryThingApiRepoImpl @Inject constructor(private val everyThingApiHandler: EveryThingApiHandler) :
    ApiCall by SafeApiCall(), EveryThingApiRepo {

    /**
     * Fetch news
     *
     * @param query
     * @param sortBy
     * @return
     */
    override fun fetchNews(query: String, sortBy: String): Flow<Output<NewsResponse>> = safeApiCall {
        everyThingApiHandler.news(query, sortBy)
    }
}

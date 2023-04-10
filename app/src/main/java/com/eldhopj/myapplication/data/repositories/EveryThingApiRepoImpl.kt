package com.eldhopj.myapplication.data.repositories

import com.eldhopj.myapplication.data.remote.queries.EveryNewsApiQuery
import com.eldhopj.myapplication.data.remote.service.EveryThingApiService
import com.eldhopj.myapplication.domain.RepoInterfaces.EveryThingApiRepo
import com.eldhopj.myapplication.domain.model.handlers.Output
import com.eldhopj.myapplication.domain.model.response.NewsResponse
import com.eldhopj.myapplication.utils.bases.baseRepository.ApiCall
import com.eldhopj.myapplication.utils.bases.baseRepository.SafeApiCall
import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow

/**
 * Api repo
 *
 * @property apiService
 * @constructor Create empty Api repo
 */
@ViewModelScoped
class EveryThingApiRepoImpl @Inject constructor(private val apiService: EveryThingApiService) :
    ApiCall by SafeApiCall(), EveryThingApiRepo {

    /**
     * Fetch news
     *
     * @param query
     * @param sortBy
     * @return
     */
    override fun fetchNews(query: String, sortBy: String): Flow<Output<NewsResponse>> = safeApiCall {
        apiService.everyNews(EveryNewsApiQuery(query, sortBy).toMap())
    }
}

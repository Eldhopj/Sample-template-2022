package com.eldhopj.myapplication.data.repositories

import com.eldhopj.myapplication.data.remote.Result
import com.eldhopj.myapplication.data.remote.handler.EveryThingApiHandler
import com.eldhopj.myapplication.domain.model.News
import com.eldhopj.myapplication.utils.bases.BaseRepository
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
    BaseRepository(), EveryThingApiRepo {

    /**
     * Fetch news
     *
     * @param query
     * @param sortBy
     * @return
     */
    override fun fetchNews(query: String, sortBy: String): Flow<Result<News>> = safeApiCall {
        everyThingApiHandler.news(query, sortBy)
    }
}

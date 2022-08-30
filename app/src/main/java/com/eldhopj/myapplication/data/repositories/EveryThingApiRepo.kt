package com.eldhopj.myapplication.data.repositories

import com.eldhopj.myapplication.data.remote.Result
import com.eldhopj.myapplication.domain.model.News
import kotlinx.coroutines.flow.Flow

/**
 * Everything api repo interface impl
 *
 * @constructor Create empty Every thing api repo
 */
interface EveryThingApiRepo {
    /**
     * Fetch news
     *
     * @param query
     * @param sortBy
     * @return
     */
    fun fetchNews(query: String, sortBy: String): Flow<Result<News>>
}

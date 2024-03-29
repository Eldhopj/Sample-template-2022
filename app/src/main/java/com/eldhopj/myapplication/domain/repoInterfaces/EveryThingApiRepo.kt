package com.eldhopj.myapplication.domain.repoInterfaces

import com.eldhopj.myapplication.domain.model.handlers.Output
import com.eldhopj.myapplication.domain.model.response.NewsResponse
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
    fun fetchNews(query: String, sortBy: String): Flow<Output<NewsResponse>>
}

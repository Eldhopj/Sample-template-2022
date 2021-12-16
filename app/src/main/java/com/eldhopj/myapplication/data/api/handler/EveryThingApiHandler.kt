package com.eldhopj.myapplication.data.api.handler

import com.eldhopj.myapplication.data.api.queries.EveryThingApiQuery
import com.eldhopj.myapplication.data.api.service.EveryThingApiService
import com.eldhopj.myapplication.data.model.News
import javax.inject.Inject
import javax.inject.Singleton
import retrofit2.Response
import retrofit2.Retrofit

/**
 * Api handler
 *
 * @constructor
 *
 * @param retrofit
 */
@Singleton
class EveryThingApiHandler @Inject constructor(retrofit: Retrofit) {

    private val everyThingApiService: EveryThingApiService by lazy {
        retrofit.create(
            EveryThingApiService::class.java
        )
    }

    /**
     * API to get categories
     */
    suspend fun news(query: String, sortBy: String): Response<News> {
        return everyThingApiService.everyThing(EveryThingApiQuery(query, sortBy).toMap())
    }
}

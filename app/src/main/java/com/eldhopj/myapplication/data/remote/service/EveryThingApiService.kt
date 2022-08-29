package com.eldhopj.myapplication.data.remote.service

import com.eldhopj.myapplication.data.constants.ApiEndPoints
import com.eldhopj.myapplication.data.remote.Result
import com.eldhopj.myapplication.domain.model.News
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.QueryMap

/**
 * Api service
 *
 * @constructor Create empty Api service
 */
interface EveryThingApiService {
    /**
     * API to perform login op with the server & returns the subscriber information once it is success.
     *
     * @return [Result]
     */
    @GET(ApiEndPoints.EVERYTHING)
    suspend fun everyThing(
        @QueryMap map: Map<String, String>
    ): Response<News>
}

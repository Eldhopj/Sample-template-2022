package com.eldhopj.myapplication.data.api.service

import com.eldhopj.myapplication.data.model.News
import com.eldhopj.myapplication.utils.constants.ApiEndPoints
import com.eldhopj.myapplication.utils.network.NetworkResponse
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
     * @return [NetworkResponse]
     */
    @GET(ApiEndPoints.EVERYTHING)
    suspend fun everyThing(
        @QueryMap map: Map<String, String>
    ): Response<News>
}

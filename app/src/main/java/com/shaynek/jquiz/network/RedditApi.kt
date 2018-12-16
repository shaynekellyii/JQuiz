package com.shaynek.jquiz.network

import com.shaynek.jquiz.model.RedditResponse
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface RedditApi {

    @GET("/top.json")
    fun getTop(
        @Query("after") after: String?,
        @Query("limit") limit: Int
    ): Observable<RedditResponse>

    @GET("/hot.json")
    fun getHot(
        @Query("after") after: String?,
        @Query("limit") limit: Int
    ): Observable<RedditResponse>
}
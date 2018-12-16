package com.shaynek.jquiz.network

import com.shaynek.jquiz.model.RedditResponse
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface RedditApi {

    @GET("/best.json")
    fun getBest(
        @Query("after") after: String?,
        @Query("limit") limit: Int
    ): Observable<RedditResponse>

    @GET("/hot.json")
    fun getHot(
        @Query("after") after: String?,
        @Query("limit") limit: Int
    ): Observable<RedditResponse>

    @GET("/top.json")
    fun getTop(
        @Query("after") after: String?,
        @Query("limit") limit: Int
    ): Observable<RedditResponse>

    @GET("/new.json")
    fun getNew(
        @Query("after") after: String?,
        @Query("limit") limit: Int
    ): Observable<RedditResponse>

    @GET("/rising.json")
    fun getRising(
        @Query("after") after: String?,
        @Query("limit") limit: Int
    ): Observable<RedditResponse>

    @GET("/controversial.json")
    fun getControversial(
        @Query("after") after: String?,
        @Query("limit") limit: Int
    ): Observable<RedditResponse>
}
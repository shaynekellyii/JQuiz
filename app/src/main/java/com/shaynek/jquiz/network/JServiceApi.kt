package com.shaynek.jquiz.network

import com.shaynek.jquiz.model.Clue
import io.reactivex.Observable
import retrofit2.http.GET

interface JServiceApi {

    @GET("/api/clues")
    fun getClues(): Observable<List<Clue>>
}
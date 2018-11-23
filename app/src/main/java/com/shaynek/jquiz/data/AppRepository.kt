package com.shaynek.jquiz.data

import com.shaynek.jquiz.model.Clue
import com.shaynek.jquiz.network.JServiceApi
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class AppRepository : BaseRepository() {

    @Inject
    lateinit var jServiceApi: JServiceApi

    /**
     * Fetches clues from the jService API and returns an observable.
     * @return observable from the API call
     */
    fun fetchClues(): Observable<List<Clue>> = jServiceApi.getClues()
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
}

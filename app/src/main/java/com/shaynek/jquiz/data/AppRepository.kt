package com.shaynek.jquiz.data

import com.shaynek.jquiz.model.RedditResponse
import com.shaynek.jquiz.network.RedditApi
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class AppRepository : BaseRepository() {

    @Inject
    lateinit var redditApi: RedditApi

    /**
     * Fetches top posts from the reddit API and returns an observable.
     * @return observable from the API call
     */
    fun fetchPosts(): Observable<RedditResponse> = redditApi.getTop(null, 10)
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
}

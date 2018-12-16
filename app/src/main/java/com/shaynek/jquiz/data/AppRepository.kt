package com.shaynek.jquiz.data

import com.shaynek.jquiz.enums.Sort
import com.shaynek.jquiz.model.RedditResponse
import com.shaynek.jquiz.network.RedditApi
import com.shaynek.jquiz.util.POSTS_PER_PAGE
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
    fun fetchPosts(sort: Sort): Observable<RedditResponse> {
        return when (sort) {
            Sort.BEST -> redditApi.getBest(null, POSTS_PER_PAGE)
            Sort.HOT -> redditApi.getHot(null, POSTS_PER_PAGE)
            Sort.TOP -> redditApi.getTop(null, POSTS_PER_PAGE)
            Sort.NEW -> redditApi.getNew(null, POSTS_PER_PAGE)
            Sort.RISING -> redditApi.getRising(null, POSTS_PER_PAGE)
            Sort.CONTROVERSIAL -> redditApi.getControversial(null, POSTS_PER_PAGE)
        }.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }
}

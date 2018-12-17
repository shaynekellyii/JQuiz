package com.shaynek.jquiz.view

import android.content.Context
import android.util.AttributeSet
import android.view.View
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.integration.recyclerview.RecyclerViewPreloader
import com.shaynek.jquiz.model.RedditPostData
import kotlinx.android.synthetic.main.activity_feed.view.*

class FeedView : CoordinatorLayout {

    private val recyclerAdapter by lazy { FeedAdapter() }

    constructor(context: Context): super(context)
    constructor(context: Context, attrs: AttributeSet?): super(context, attrs)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int = 0): super(context, attrs, defStyleAttr)

    fun init() {
        with(posts_recyclerview) {
            layoutManager = LinearLayoutManager(context)
            addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
            setHasFixedSize(true)
        }
    }

    fun showLoading() {
        posts_activity_progressbar.visibility = View.VISIBLE
        posts_recyclerview.visibility = View.GONE
    }

    fun hideLoading() {
        posts_activity_progressbar.visibility = View.GONE
        posts_recyclerview.visibility = View.VISIBLE
    }

    fun setPosts(posts: List<RedditPostData>, preloader: RecyclerViewPreloader<Any>) {
        with(posts_recyclerview) {
            recyclerAdapter.posts = posts
            adapter = recyclerAdapter
            recyclerAdapter.notifyDataSetChanged()
            addOnScrollListener(preloader)
        }
    }
}
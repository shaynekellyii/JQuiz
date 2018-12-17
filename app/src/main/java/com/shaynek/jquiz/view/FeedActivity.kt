package com.shaynek.jquiz.view

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.bumptech.glide.integration.recyclerview.RecyclerViewPreloader
import com.bumptech.glide.util.ViewPreloadSizeProvider
import com.shaynek.jquiz.R
import com.shaynek.jquiz.data.AppRepository
import com.shaynek.jquiz.data.DataStatus
import com.shaynek.jquiz.data.FeedViewModel
import com.shaynek.jquiz.enums.Sort
import com.shaynek.jquiz.injection.AppModule
import com.shaynek.jquiz.injection.DaggerAppComponent
import com.shaynek.jquiz.injection.GlideApp
import com.shaynek.jquiz.model.RedditPostData
import com.shaynek.jquiz.network.GlidePreloadModelProvider
import com.shaynek.jquiz.util.MAX_PRELOAD_IMAGES
import kotlinx.android.synthetic.main.activity_feed.*
import javax.inject.Inject

class FeedActivity : BaseActivity() {

    @Inject
    lateinit var repository: AppRepository

    private val viewModel: FeedViewModel by lazy {
        ViewModelProviders.of(this, viewModelFactory { FeedViewModel(repository) }).get(FeedViewModel::class.java)
    }

    private val injector by lazy {
        DaggerAppComponent
            .builder()
            .appModule(AppModule(applicationContext))
            .build()
    }

    private var currentSort = Sort.HOT

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_feed)

        injector.inject(this)
        feed_view.init()
        setSupportActionBar(feed_toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)

        viewModel.dataStatus.observe(this, Observer {
            if (it == DataStatus.LOADING) feed_view.showLoading() else feed_view.hideLoading()
        })
        viewModel.posts.observe(this, Observer { onPostsLoaded(it) })
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_post_sort, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        item?.itemId?.let {
            if (it != currentSort.menuId) viewModel.onSortSelected(Sort.fromMenuId(it))
        }
        return super.onOptionsItemSelected(item)
    }

    private fun onPostsLoaded(posts: List<RedditPostData>) {
        val sizeProvider = ViewPreloadSizeProvider<Any>()
        val modelProvider = GlidePreloadModelProvider(posts.map {
            when {
                it.post_hint == "image" -> it.url
                !it.is_self -> it.thumbnail
                else -> ""
            }
        }, this)
        val preloader = RecyclerViewPreloader(GlideApp.with(this), modelProvider, sizeProvider, MAX_PRELOAD_IMAGES)

        feed_view.setPosts(posts, preloader)
    }
}

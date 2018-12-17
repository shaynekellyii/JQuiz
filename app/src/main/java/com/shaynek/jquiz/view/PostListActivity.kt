package com.shaynek.jquiz.view

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.integration.recyclerview.RecyclerViewPreloader
import com.bumptech.glide.util.ViewPreloadSizeProvider
import com.shaynek.jquiz.R
import com.shaynek.jquiz.data.AppRepository
import com.shaynek.jquiz.data.DataStatus
import com.shaynek.jquiz.data.PostListViewModel
import com.shaynek.jquiz.enums.Sort
import com.shaynek.jquiz.injection.AppModule
import com.shaynek.jquiz.injection.DaggerAppComponent
import com.shaynek.jquiz.injection.GlideApp
import com.shaynek.jquiz.model.RedditPostData
import com.shaynek.jquiz.network.GlidePreloadModelProvider
import com.shaynek.jquiz.util.MAX_PRELOAD_IMAGES
import kotlinx.android.synthetic.main.activity_posts.*
import javax.inject.Inject

class PostListActivity : BaseActivity() {

    @Inject
    lateinit var repository: AppRepository

    private val viewModel: PostListViewModel by lazy {
        ViewModelProviders.of(this, viewModelFactory { PostListViewModel(repository) }).get(PostListViewModel::class.java)
    }

    private val injector by lazy {
        DaggerAppComponent
            .builder()
            .appModule(AppModule(applicationContext))
            .build()
    }

    private val recyclerAdapter by lazy { PostListAdapter() }

    private var currentSort = Sort.HOT

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_posts)

        injector.inject(this)

        with(posts_recyclerview) {
            layoutManager = LinearLayoutManager(this@PostListActivity)
            addItemDecoration(DividerItemDecoration(this@PostListActivity, DividerItemDecoration.VERTICAL))
            setHasFixedSize(true)
        }

        viewModel.dataStatus.observe(this, Observer {
            posts_activity_progressbar.visibility = if (it == DataStatus.LOADING) View.VISIBLE else View.GONE
            posts_recyclerview.visibility = if (it == DataStatus.LOADING) View.GONE else View.VISIBLE
        })
        viewModel.posts.observe(this, Observer { onPostsLoaded(it) })
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_post_sort, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        item?.itemId.let {
            if (it != currentSort.menuId) viewModel.onSortSelected(Sort.fromMenuId(it!!))
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

        with(posts_recyclerview) {
            recyclerAdapter.posts = posts
            adapter = recyclerAdapter
            recyclerAdapter.notifyDataSetChanged()
            addOnScrollListener(preloader)
        }
    }
}

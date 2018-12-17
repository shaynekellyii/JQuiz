package com.shaynek.jquiz

import android.content.Context
import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.ListPreloader.PreloadModelProvider
import com.bumptech.glide.RequestBuilder
import com.bumptech.glide.integration.recyclerview.RecyclerViewPreloader
import com.bumptech.glide.util.ViewPreloadSizeProvider
import com.shaynek.jquiz.data.DataStatus
import com.shaynek.jquiz.data.PostsViewModel
import com.shaynek.jquiz.enums.Sort
import com.shaynek.jquiz.injection.GlideApp
import com.shaynek.jquiz.model.RedditPostData
import com.shaynek.jquiz.network.GlidePreloadModelProvider
import com.shaynek.jquiz.util.MAX_PRELOAD_IMAGES
import com.shaynek.jquiz.view.PostsAdapter
import kotlinx.android.synthetic.main.activity_posts.*
import java.util.*


class PostsActivity : AppCompatActivity() {

    private val view by lazy {
        LayoutInflater.from(this).inflate(R.layout.activity_posts, null, false)
    }
    private val viewModel: PostsViewModel by lazy {
        ViewModelProviders.of(this).get(PostsViewModel::class.java)
    }
    private val recyclerAdapter by lazy { PostsAdapter() }

    private var currentSort = Sort.HOT

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(view)

        with(posts_recyclerview) {
            layoutManager = LinearLayoutManager(this@PostsActivity)
            addItemDecoration(DividerItemDecoration(this@PostsActivity, DividerItemDecoration.VERTICAL))
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

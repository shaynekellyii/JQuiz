package com.shaynek.jquiz

import android.content.Context
import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.ListPreloader.PreloadModelProvider
import com.bumptech.glide.RequestBuilder
import com.bumptech.glide.integration.recyclerview.RecyclerViewPreloader
import com.bumptech.glide.util.ViewPreloadSizeProvider
import com.shaynek.jquiz.data.PostsViewModel
import com.shaynek.jquiz.injection.GlideApp
import com.shaynek.jquiz.model.RedditPostData
import com.shaynek.jquiz.util.MAX_PRELOAD_IMAGES
import com.shaynek.jquiz.view.PostsAdapter
import kotlinx.android.synthetic.main.activity_posts.*
import java.util.*


class PostsActivity : AppCompatActivity() {

    private val view by lazy { LayoutInflater.from(this).inflate(R.layout.activity_posts, null, false) }

    private val viewModel: PostsViewModel by lazy {
        ViewModelProviders.of(this).get(PostsViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(view)

        with(posts_recyclerview) {
            layoutManager = LinearLayoutManager(this@PostsActivity)
            addItemDecoration(DividerItemDecoration(this@PostsActivity, DividerItemDecoration.VERTICAL))
        }

        viewModel.dataStatus.observe(this, Observer {  })
        viewModel.posts.observe(this, Observer { onPostsLoaded(it) })
    }

    private fun onPostsLoaded(posts: List<RedditPostData>) {
        val sizeProvider = ViewPreloadSizeProvider<Any>()
        val modelProvider = MyPreloadModelProvider(posts.map {
            when (it.post_hint) { "image" -> it.url else -> "" }
        }, this)
        val preloader = RecyclerViewPreloader(
            GlideApp.with(this), modelProvider, sizeProvider, MAX_PRELOAD_IMAGES)

        with (posts_recyclerview) {
            adapter = PostsAdapter(posts)
            setHasFixedSize(true)
            addOnScrollListener(preloader)
        }
    }

    private inner class MyPreloadModelProvider(val urls: List<String>, val context: Context)
        : PreloadModelProvider<Any> {

        override fun getPreloadItems(position: Int): List<Any> {
            val url = urls[position]
            return if (TextUtils.isEmpty(url)) Collections.emptyList() else Collections.singletonList(url)
        }

        override fun getPreloadRequestBuilder(item: Any): RequestBuilder<*>? =
                GlideApp.with(context).load(item)
    }
}

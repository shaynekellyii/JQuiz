package com.shaynek.jquiz

import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.shaynek.jquiz.data.PostsViewModel
import com.shaynek.jquiz.view.PostsAdapter
import kotlinx.android.synthetic.main.activity_posts.*


class PostsActivity : AppCompatActivity() {

    private val view by lazy { LayoutInflater.from(this).inflate(R.layout.activity_posts, null, false) }

    private val viewModel: PostsViewModel by lazy {
        ViewModelProviders.of(this).get(PostsViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(view)

        with (posts_recyclerview) {
            layoutManager = LinearLayoutManager(this@PostsActivity)
            addItemDecoration(DividerItemDecoration(this@PostsActivity, DividerItemDecoration.VERTICAL))
        }

        viewModel.dataStatus.observe(this, Observer { })
        viewModel.posts.observe(this, Observer {
            posts_recyclerview.adapter = PostsAdapter(it)
        })
    }
}

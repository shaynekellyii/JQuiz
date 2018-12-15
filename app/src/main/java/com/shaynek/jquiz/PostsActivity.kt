package com.shaynek.jquiz

import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.shaynek.jquiz.data.PostsViewModel
import kotlinx.android.synthetic.main.activity_posts.*


class PostsActivity : AppCompatActivity() {

    private val view by lazy { LayoutInflater.from(this).inflate(R.layout.activity_posts, null, false) }

    private val viewModel: PostsViewModel by lazy {
        ViewModelProviders.of(this).get(PostsViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(view)

        viewModel.dataStatus.observe(this,
            Observer { status -> main_textview.text = status.toString() })
    }
}

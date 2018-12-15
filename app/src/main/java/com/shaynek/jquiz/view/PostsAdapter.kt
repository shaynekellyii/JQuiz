package com.shaynek.jquiz.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.shaynek.jquiz.R
import com.shaynek.jquiz.model.RedditPostData

class PostsAdapter(private val posts: List<RedditPostData>) : RecyclerView.Adapter<PostsAdapter.PostViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder =
        PostViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.view_post, parent, false) as PostView)

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) =
        (holder.itemView as PostView).setModel(posts[position])

    override fun getItemCount(): Int = posts.size

    class PostViewHolder(view: PostView) : RecyclerView.ViewHolder(view)
}
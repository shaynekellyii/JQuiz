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
        PostViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.post_item, parent, false))

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        holder.title.text = posts[position].title
        holder.author.text = posts[position].author
    }

    override fun getItemCount(): Int = posts.size

    class PostViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val title: TextView = view.findViewById(R.id.post_item_title)
        val author: TextView = view.findViewById(R.id.post_item_author)
    }
}
package com.shaynek.jquiz.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.shaynek.jquiz.R
import com.shaynek.jquiz.injection.GlideApp
import com.shaynek.jquiz.model.RedditPostData

class PostsAdapter(private val posts: List<RedditPostData>) : RecyclerView.Adapter<PostsAdapter.PostViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder =
        PostViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.view_post, parent, false) as PostView)

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        with (holder) {
            (itemView as PostView).setModel(posts[position])
            if (posts[position].post_hint == "image") {
                Glide.with(itemView.context)
                    .load(posts[position].url)
                    .into(imageView)
            } else {
                Glide.with(itemView.context)
                    .clear(imageView)
                imageView.setImageDrawable(null)
            }
        }
    }

    override fun getItemCount(): Int = posts.size

    class PostViewHolder(view: PostView) : RecyclerView.ViewHolder(view) {
        val imageView: ImageView = view.findViewById(R.id.post_image)
    }
}
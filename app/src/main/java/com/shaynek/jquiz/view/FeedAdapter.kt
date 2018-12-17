package com.shaynek.jquiz.view

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.shaynek.jquiz.R
import com.shaynek.jquiz.model.RedditPostData

class FeedAdapter : RecyclerView.Adapter<FeedAdapter.PostViewHolder>() {

    lateinit var posts: List<RedditPostData>

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder =
        PostViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.view_post, parent, false) as PostView)

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        with(holder) {
            (itemView as PostView).setModel(posts[position])
            when {
                posts[position].post_hint == "image" -> {
                    Glide.with(itemView.context)
                        .load(posts[position].url)
                        .into(imageView)
                    Glide.with(itemView.context)
                        .clear(thumbnailImageView)
                    thumbnailImageView.setImageDrawable(null)
                }
                !posts[position].is_self -> {
                    Glide.with(itemView.context)
                        .load(posts[position].thumbnail)
                        .into(thumbnailImageView)
                    Glide.with(itemView.context)
                        .clear(imageView)
                    imageView.setImageDrawable(null)
                }
                else -> {
                    Glide.with(itemView.context)
                        .clear(imageView)
                    imageView.setImageDrawable(null)
                }
            }
        }
    }

    override fun getItemCount(): Int = posts.size

    class PostViewHolder(view: PostView) : RecyclerView.ViewHolder(view) {
        val imageView: ImageView = view.findViewById(R.id.post_image)
        val thumbnailImageView: ImageView = view.findViewById(R.id.post_thumbnail)
    }
}
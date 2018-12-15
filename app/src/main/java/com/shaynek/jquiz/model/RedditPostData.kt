package com.shaynek.jquiz.model

data class RedditPostData(
    val subreddit: String,
    val selftext: String,
    val author: String,
    val gilded: Int,
    val title: String,
    val thumbnail_height: Int,
    val score: Int,
    val thumbnail: String,
    val permalink: String,
    val url: String,
    val num_comments: Int,
    val created: Long,
    val is_self: Boolean,
    val post_hint: String
)
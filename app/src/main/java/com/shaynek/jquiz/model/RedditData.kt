package com.shaynek.jquiz.model

data class RedditData(
    val modhash: String,
    val dist: Int,
    val children: List<RedditChildData>,
    val after: String,
    val before: String
)
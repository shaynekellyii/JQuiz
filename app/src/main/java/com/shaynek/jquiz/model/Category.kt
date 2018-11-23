package com.shaynek.jquiz.model

import java.util.*

data class Category(
    val id: Int,
    val title: String,
    val created_at: Date,
    val updated_at: Date,
    val clues_count: Int
)
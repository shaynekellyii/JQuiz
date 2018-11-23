package com.shaynek.jquiz.model

import java.util.*

data class Clue(
    val id: Int,
    val answer: String,
    val question: String,
    val value: Int,
    val airdate: Date,
    val created_at: Date,
    val updated_at: Date,
    val category_id: Int,
    val game_id: Int,
    val invalid_count: Int,
    val category: Category
)
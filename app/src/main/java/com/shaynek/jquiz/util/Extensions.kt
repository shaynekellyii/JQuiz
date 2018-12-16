package com.shaynek.jquiz.util

fun Int.shortNumString(): String {
    return when {
        this < 1000 -> this.toString()
        this < 1000000 -> "${Math.round((this / 1000) * 10.0 / 10.0)}K"
        else -> "${Math.round((this/1000000) * 10.0 / 10.0)}M"
    }
}

fun Long.timeSinceNow(): String {
    val elapsedTime = System.currentTimeMillis()/1000 - this
    return when {
        elapsedTime < 60 -> "${elapsedTime}s"
        elapsedTime < 60*60 -> "${elapsedTime / 60}m"
        elapsedTime < 60*60*24 -> "${elapsedTime / (60*60)}h"
        else -> "${elapsedTime / (60*60*24)}d"
    }
}
package com.shaynek.jquiz.view

import android.content.Context
import android.util.AttributeSet
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import com.shaynek.jquiz.R

/**
 * Custom view class for display a list of clues.
 * Observes PostsViewModel and reacts to data changes.
 */
class PostsView : ConstraintLayout {

    constructor(context: Context) : this(context, null)
    constructor(context: Context, attrs: AttributeSet? = null) : this(context, attrs, 0)
    constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) : super(context, attrs, defStyleAttr)

    init {
        View.inflate(context, R.layout.activity_posts, this)
    }
}
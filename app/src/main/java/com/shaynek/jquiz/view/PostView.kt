package com.shaynek.jquiz.view

import android.content.Context
import android.util.AttributeSet
import androidx.constraintlayout.widget.ConstraintLayout
import com.shaynek.jquiz.R
import com.shaynek.jquiz.model.RedditPostData
import kotlinx.android.synthetic.main.view_post.view.*

class PostView : ConstraintLayout {

    private lateinit var model: RedditPostData

    constructor(context: Context): super(context)
    constructor(context: Context, attrs: AttributeSet?): super(context, attrs)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int = 0): super(context, attrs, defStyleAttr)

    fun setModel(model: RedditPostData) {
        this.model = model
        with (model) {
            post_title_text.text = title
            post_author_text.text = resources.getString(R.string.by, author)
            post_self_text.text = if (selftext.isNotEmpty()) selftext else "No self text"
            post_subreddit_text.text = subreddit
        }
    }
}
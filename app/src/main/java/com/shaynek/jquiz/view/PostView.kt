package com.shaynek.jquiz.view

import android.content.Context
import android.util.AttributeSet
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import com.shaynek.jquiz.R
import com.shaynek.jquiz.model.RedditPostData
import com.shaynek.jquiz.util.shortNumString
import com.shaynek.jquiz.util.timeSinceNow
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
            post_subreddit_text.text = subreddit
            post_link_text.text = url
            post_score_text.text = score.shortNumString()
            post_comment_count_text.text = num_comments.shortNumString()
            post_time_text.text = created_utc.timeSinceNow()

            when {
                is_self -> {
                    post_self_text.text = selftext
                    post_self_text.visibility = View.VISIBLE
                    post_image.visibility = View.GONE
                    post_thumbnail.visibility = View.GONE
                    post_link_text.visibility = View.GONE
                }
                post_hint == "image" -> {
                    post_self_text.visibility = View.GONE
                    post_image.visibility = View.VISIBLE
                    post_thumbnail.visibility = View.GONE
                    post_link_text.visibility = View.GONE
                }
                else -> {
                    post_self_text.visibility = View.GONE
                    post_image.visibility = View.GONE
                    post_thumbnail.visibility = View.VISIBLE
                    post_link_text.visibility = View.VISIBLE
                }
            }
        }
    }
}
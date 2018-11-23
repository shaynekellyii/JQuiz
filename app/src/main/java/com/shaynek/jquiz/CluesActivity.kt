package com.shaynek.jquiz

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.shaynek.jquiz.data.CluesViewModel
import android.view.View.inflate
import com.shaynek.jquiz.view.CluesView
import kotlinx.android.synthetic.main.activity_clues.*


class CluesActivity : AppCompatActivity() {

    private lateinit var view: CluesView

    private val viewModel: CluesViewModel by lazy {
        ViewModelProviders.of(this).get(CluesViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        view = inflate(this, R.layout.activity_clues, null) as CluesView
        setContentView(view)

        viewModel.dataStatus.observe(this,
            Observer { status -> main_textview.text = status.toString() })
    }
}

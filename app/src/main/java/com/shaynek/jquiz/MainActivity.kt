package com.shaynek.jquiz

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.shaynek.jquiz.data.ClueListViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val viewModel: ClueListViewModel by lazy {
        ViewModelProviders.of(this).get(ClueListViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel.dataStatus.observe(this,
            Observer { status -> main_textview.text = status.toString() })
    }
}

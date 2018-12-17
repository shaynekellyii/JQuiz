package com.shaynek.jquiz.view

import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

/**
 * As of now, contains a method to provide a ViewModelFactory and create ViewModels with arguments in their constructor.
 */
open class BaseActivity : AppCompatActivity() {

    protected inline fun <VM : ViewModel> viewModelFactory(crossinline f: () -> VM) =
        object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(aClass: Class<T>): T = f() as T
        }
}
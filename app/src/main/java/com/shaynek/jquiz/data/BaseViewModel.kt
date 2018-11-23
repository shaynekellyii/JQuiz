package com.shaynek.jquiz.data

import androidx.lifecycle.ViewModel
import com.shaynek.jquiz.injection.AppComponent
import com.shaynek.jquiz.injection.AppModule
import com.shaynek.jquiz.injection.DaggerAppComponent

open class BaseViewModel : ViewModel() {

    private val injector: AppComponent = DaggerAppComponent
        .builder()
        .appModule(AppModule)
        .build()

    init {
        inject()
    }

    private fun inject() {
        when (this) {
            is ClueListViewModel -> injector.inject(this)
        }
    }
}
package com.shaynek.jquiz.data

import com.shaynek.jquiz.injection.AppComponent
import com.shaynek.jquiz.injection.AppModule
import com.shaynek.jquiz.injection.DaggerAppComponent

abstract class BaseRepository {

    private val injector: AppComponent = DaggerAppComponent
        .builder()
        .appModule(AppModule)
        .build()

    init {
        inject()
    }

    private fun inject() {
        when (this) {
            is AppRepository -> injector.inject(this)
        }
    }
}
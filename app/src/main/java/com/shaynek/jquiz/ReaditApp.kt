package com.shaynek.jquiz

import android.app.Application
import com.shaynek.jquiz.injection.AppModule
import com.shaynek.jquiz.injection.DaggerAppComponent
import com.shaynek.jquiz.pref.Preferences

class ReaditApp : Application() {

    override fun onCreate() {
        super.onCreate()
        val injector = DaggerAppComponent
            .builder()
            .appModule(AppModule(applicationContext))
            .build()
        injector.inject(Preferences)
    }
}

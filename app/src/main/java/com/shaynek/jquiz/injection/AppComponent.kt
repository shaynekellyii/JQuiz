package com.shaynek.jquiz.injection

import com.shaynek.jquiz.pref.Preferences
import com.shaynek.jquiz.view.FeedActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class])
interface AppComponent {

    fun inject(activity: FeedActivity)
    fun inject(preferences: Preferences)

    @Component.Builder
    interface Builder {
        fun build(): AppComponent
        fun appModule(appModule: AppModule): Builder
    }
}
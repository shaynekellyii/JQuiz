package com.shaynek.jquiz.injection

import com.shaynek.jquiz.data.AppRepository
import com.shaynek.jquiz.data.PostsViewModel
import com.shaynek.jquiz.view.PostsActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class])
interface AppComponent {

    fun inject(activity: PostsActivity)
    fun inject(postsViewModel: PostsViewModel)
    fun inject(appRepository: AppRepository)

    @Component.Builder
    interface Builder {

        fun build(): AppComponent

        fun appModule(appModule: AppModule): Builder
    }
}
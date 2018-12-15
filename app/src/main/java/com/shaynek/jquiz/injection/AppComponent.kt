package com.shaynek.jquiz.injection

import com.shaynek.jquiz.data.AppRepository
import com.shaynek.jquiz.data.PostsViewModel
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class])
interface AppComponent {

    fun inject(repository: AppRepository)
    fun inject(viewModel: PostsViewModel)

    @Component.Builder
    interface Builder {

        fun build(): AppComponent

        fun appModule(appModule: AppModule): Builder
    }
}
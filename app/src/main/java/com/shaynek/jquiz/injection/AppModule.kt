package com.shaynek.jquiz.injection

import android.content.Context
import android.preference.PreferenceManager
import com.shaynek.jquiz.data.AppRepository
import com.shaynek.jquiz.network.RedditApi
import com.shaynek.jquiz.util.API_BASE_URL
import dagger.Module
import dagger.Provides
import dagger.Reusable
import io.reactivex.schedulers.Schedulers
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

@Module
class AppModule(private val app: Context) {

    @Provides
    @Reusable
    internal fun provideApi(retrofit: Retrofit): RedditApi = retrofit.create(RedditApi::class.java)

    @Provides
    @Reusable
    internal fun provideRetrofit(): Retrofit =
        Retrofit.Builder()
            .baseUrl(API_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
            .build()

    @Provides
    @Reusable
    internal fun provideRepository(redditApi: RedditApi): AppRepository = AppRepository(redditApi)

    @Provides
    @Reusable
    internal fun provideSharedPreferences() = PreferenceManager.getDefaultSharedPreferences(app)
}
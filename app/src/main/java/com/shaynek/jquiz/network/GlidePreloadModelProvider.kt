package com.shaynek.jquiz.network

import android.content.Context
import android.text.TextUtils
import com.bumptech.glide.ListPreloader
import com.bumptech.glide.RequestBuilder
import com.shaynek.jquiz.injection.GlideApp
import java.util.*

class GlidePreloadModelProvider(val urls: List<String>, val context: Context) :
    ListPreloader.PreloadModelProvider<Any> {

    override fun getPreloadItems(position: Int): List<Any> {
        val url = urls[position]
        return if (TextUtils.isEmpty(url)) Collections.emptyList() else Collections.singletonList(url)
    }

    override fun getPreloadRequestBuilder(item: Any): RequestBuilder<*>? =
        GlideApp.with(context).load(item)
}
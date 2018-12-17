package com.shaynek.jquiz.pref

import android.content.SharedPreferences
import com.shaynek.jquiz.enums.Sort
import javax.inject.Inject

object Preferences {

    @set:Inject
    lateinit var sharedPreferences: dagger.Lazy<SharedPreferences>

    const val SORT_KEY = "SORT"

    fun setLastSort(sort: Sort) = sharedPreferences.get().edit().putInt(SORT_KEY, sort.ordinal).commit()
    fun getLastSort() = Sort.values()[sharedPreferences.get().getInt(SORT_KEY, Sort.BEST.ordinal)]
}
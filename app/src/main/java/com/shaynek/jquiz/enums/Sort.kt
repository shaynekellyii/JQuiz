package com.shaynek.jquiz.enums

import com.shaynek.jquiz.R

enum class Sort(val menuId: Int) {
    BEST(R.id.menu_best),
    HOT(R.id.menu_hot),
    TOP(R.id.menu_top),
    NEW(R.id.menu_new),
    RISING(R.id.menu_rising),
    CONTROVERSIAL(R.id.menu_controversial);

    companion object {
        private val map = Sort.values().associateBy(Sort::menuId);
        fun fromMenuId(menuId: Int) = map[menuId]
    }
}
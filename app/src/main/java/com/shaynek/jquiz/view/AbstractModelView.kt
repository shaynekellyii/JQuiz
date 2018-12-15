package com.shaynek.jquiz.view

import android.content.Context
import android.util.AttributeSet
import android.view.View

abstract class AbstractModelView<T: Any> : View {

    constructor(context: Context): super(context)
    constructor(context: Context, attrs: AttributeSet?): super(context, attrs)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int = 0): super(context, attrs, defStyleAttr)

    abstract fun setModel(model: T)
}
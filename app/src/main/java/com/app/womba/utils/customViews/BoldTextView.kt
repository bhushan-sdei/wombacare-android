package com.app.womba.utils.customViews

import android.content.Context
import android.graphics.Typeface
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatTextView

class BoldTextView : AppCompatTextView {

    constructor(context: Context) : super(context) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        init()
    }

    private fun init() {
        if (!isInEditMode) {
            val typeface = Typeface.createFromAsset(
                context
                    .assets, "OpenSans_Bold.ttf"
            )
            setTypeface(typeface)
        }
    }
}
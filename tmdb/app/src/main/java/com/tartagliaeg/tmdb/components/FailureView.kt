package com.tartagliaeg.tmdb.components

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.LinearLayout
import androidx.appcompat.widget.AppCompatTextView
import com.tartagliaeg.tmdb.R
import kotlinx.android.synthetic.main.components_view_failure.view.*


class FailureView : LinearLayout {
    private val selfView: View = inflate(context, R.layout.components_view_failure, this)
    private val tvTitle: AppCompatTextView = cmps_tv_failure_title
    private val tvDescription: AppCompatTextView = cmps_tv_failure_description

    var title: String = ""
        set(value) {
            tvTitle.text = value
            field = value
        }

    var description: String = ""
        set(value) {
            tvDescription.text = value
            field = value
        }


    constructor(context: Context) : super(context) {}
    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        loadProps(context, attrs)
    }

    constructor(context: Context, attrs: AttributeSet, style: Int) : super(context, attrs, style) {
        loadProps(context, attrs)
    }

    private fun loadProps(context: Context, attrs: AttributeSet) {
        val styledAttrs = context.obtainStyledAttributes(attrs, R.styleable.FailureView)
        this.description = styledAttrs.getString(R.styleable.FailureView_description) ?: ""
        this.title = styledAttrs.getString(R.styleable.FailureView_title) ?: ""
        styledAttrs.recycle()
    }

    init {
        orientation = if (orientation != VERTICAL && orientation != HORIZONTAL) VERTICAL else orientation
    }

}

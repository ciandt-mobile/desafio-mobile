package com.tartagliaeg.tmdb.tools_android

import android.graphics.Point
import androidx.fragment.app.FragmentActivity
import kotlin.math.floor


class DimensionTools(val activity: FragmentActivity) {

    fun widthInPx(): Int {
        val size = Point()
        activity.windowManager.defaultDisplay.getSize(size)
        return size.x
    }

    fun heightInPx(): Int {
        val size = Point()
        activity.windowManager.defaultDisplay.getSize(size)
        return size.y
    }

    fun pxToDp(px: Float): Float {
        return px / activity.resources.displayMetrics.density
    }

    fun dpToPx(dp: Float): Float {
        return dp * activity.resources.displayMetrics.density
    }

    fun widthInDp(): Float {
        return pxToDp(widthInPx().toFloat())
    }

    fun itemsToFitInScreen(itemSizeDp: Int): Float {
        return floor((widthInDp() / itemSizeDp))
    }

    fun heightInDp(): Float {
        return pxToDp(heightInPx().toFloat())
    }


}



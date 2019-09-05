package com.conrradocamacho.desafio.utils

import androidx.recyclerview.widget.RecyclerView

/**
 * Created by Conrrado Camacho on 01/09/2019.
 * con.webmaster@gmail.com
 */
abstract class HidingScrollListener: RecyclerView.OnScrollListener() {
    companion object {
        private const val HIDE_THRESHOLD = 20
    }

    private var scrolledDistance = 0
    private var controlsVisible = false

    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        super.onScrolled(recyclerView, dx, dy)

        if (scrolledDistance > HIDE_THRESHOLD && controlsVisible) {
            onHide()
            controlsVisible = false
            scrolledDistance = 0
        }
        else if (scrolledDistance < -HIDE_THRESHOLD && !controlsVisible) {
            onShow()
            controlsVisible = true
            scrolledDistance = 0
        }

        if ((controlsVisible && dy > 0) || (!controlsVisible && dy < 0)) {
            scrolledDistance += dy
        }
    }

    abstract fun onHide()
    abstract fun onShow()
}
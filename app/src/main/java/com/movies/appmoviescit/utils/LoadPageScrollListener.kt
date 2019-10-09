package com.movies.appmoviescit.utils

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class LoadPageScrollListener(private val loadPageScrollLoadMoreListener: LoadPageScrollLoadMoreListener) : RecyclerView.OnScrollListener() {

    private val visibleThreshold = 5
    private var currentPage = 1
    private var previousTotalItemCount = 0L
    private var loading = true
    private val startingPageIndex = 1

    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        super.onScrolled(recyclerView, dx, dy)
        val layoutManager = recyclerView.layoutManager
        if (layoutManager is LinearLayoutManager) {
            val lastVisibleItemPosition = layoutManager.findLastVisibleItemPosition()

            val totalItemCount = layoutManager.getItemCount().toLong()

            if (totalItemCount < previousTotalItemCount) {
                this.currentPage = this.startingPageIndex
                this.previousTotalItemCount = totalItemCount
                if (totalItemCount == 0L) {
                    this.loading = true
                }
            }

            if (loading && totalItemCount > previousTotalItemCount) {
                loading = false
                previousTotalItemCount = totalItemCount
            }

            if (!loading && lastVisibleItemPosition + visibleThreshold > totalItemCount) {
                currentPage++
                loadPageScrollLoadMoreListener.onLoadMore(currentPage, totalItemCount, recyclerView)
                loading = true
            }
        }

    }

    interface LoadPageScrollLoadMoreListener {

        fun onLoadMore(
            currentPage: Int,
            totalItemCount: Long,
            recyclerView: RecyclerView
        )
    }
}
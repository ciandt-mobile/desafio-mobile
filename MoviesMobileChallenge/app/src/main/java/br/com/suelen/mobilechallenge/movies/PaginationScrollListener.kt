package br.com.suelen.mobilechallenge.movies

import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

abstract class PaginationScrollListener(private val layoutManager: RecyclerView.LayoutManager) : RecyclerView.OnScrollListener() {

    var loading = false

    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        super.onScrolled(recyclerView, dx, dy)

        val visibleItemCount = layoutManager.childCount
        val totalItemCount = layoutManager.itemCount
        val firstVisibleItemPos = (layoutManager as GridLayoutManager).findFirstVisibleItemPosition()

        if(visibleItemCount + firstVisibleItemPos >= totalItemCount) {
            if(!loading) {
                loading = true
                loadMoreItems()
            }
        } else {
            loading = false
        }

    }

    abstract fun loadMoreItems()
}
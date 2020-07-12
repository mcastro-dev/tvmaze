package com.mcastro.tvmaze.exploretvshows

import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

abstract class EndlessRecyclerViewScrollListener(
    private val gridLayoutManager: GridLayoutManager
) : RecyclerView.OnScrollListener() {

    private val visibleThreshold = 5 * gridLayoutManager.spanCount
    private var previousTotalItemCount = 0
    private var loading = true

    // Called when needs to load more items
    abstract fun onLoadMore()

    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        val lastVisibleItemPosition = gridLayoutManager.findLastVisibleItemPosition()
        val totalItemCount: Int = gridLayoutManager.itemCount

        if (totalItemCount < previousTotalItemCount) {
            this.previousTotalItemCount = totalItemCount
            if (totalItemCount == 0) {
                this.loading = true
            }
        }

        if (loading && totalItemCount > previousTotalItemCount) {
            loading = false
            previousTotalItemCount = totalItemCount
        }

        if (!loading && lastVisibleItemPosition + visibleThreshold > totalItemCount) {
            loading = true
            onLoadMore()
        }
    }
}
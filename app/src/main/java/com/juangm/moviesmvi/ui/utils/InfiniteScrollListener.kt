package com.juangm.moviesmvi.ui.utils

import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import timber.log.Timber

class InfiniteScrollListener(
    val loadMore: () -> Unit,
    private val layoutManager: RecyclerView.LayoutManager
) : RecyclerView.OnScrollListener() {

    private val visibleThreshold = 2
    private var previousTotal = 0
    private var loading = true
    private var firstVisibleItem = 0
    private var visibleItemCount = 0
    private var totalItemCount = 0

    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        super.onScrolled(recyclerView, dx, dy)

        if (dy > 0) {
            visibleItemCount = recyclerView.childCount
            totalItemCount = layoutManager.itemCount

            when(layoutManager) {
                is LinearLayoutManager -> firstVisibleItem = layoutManager.findLastVisibleItemPosition()
                is GridLayoutManager -> firstVisibleItem = layoutManager.findLastVisibleItemPosition()
            }

            if (loading) {
                if (totalItemCount > previousTotal) {
                    loading = false
                    previousTotal = totalItemCount
                }
            }
            if (!loading && (totalItemCount - visibleItemCount) <= (firstVisibleItem + visibleThreshold)) {
                Timber.i("End of the RecyclerView reached")
                loadMore()
                loading = true
            }
        }
    }
}
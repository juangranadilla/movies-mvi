package com.juangm.movies_mvi.presentation.utils

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager

class GridMarginItemDecoration(private val spacing: Int) : androidx.recyclerview.widget.RecyclerView.ItemDecoration() {
    override fun getItemOffsets(outRect: Rect, view: View, parent: androidx.recyclerview.widget.RecyclerView, state: androidx.recyclerview.widget.RecyclerView.State) {
        val position = parent.getChildViewHolder(view).adapterPosition
        val itemCount = state.itemCount
        val layoutManager = parent.layoutManager

        val gridLayoutManager = layoutManager as GridLayoutManager
        val cols = gridLayoutManager.spanCount
        val rows = itemCount / cols

        with(outRect) {
            top = spacing

            left = spacing

            right = spacing
            if(position % cols != cols - 1)
                right = 0

            bottom = spacing
            if(position / cols != rows - 1)
                bottom = 0
        }
    }
}
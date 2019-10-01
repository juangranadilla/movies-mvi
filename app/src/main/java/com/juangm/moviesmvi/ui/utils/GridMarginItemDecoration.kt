package com.juangm.moviesmvi.ui.utils

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

class GridMarginItemDecoration(private val spacing: Int) : RecyclerView.ItemDecoration() {
    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
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
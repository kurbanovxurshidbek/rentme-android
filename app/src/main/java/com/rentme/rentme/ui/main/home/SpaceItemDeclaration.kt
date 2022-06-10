package com.rentme.rentme.ui.main.home

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ItemDecoration


class SpacesItemDecoration(private val mSpaces: Int) : ItemDecoration() {
    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        outRect.left = mSpaces
//        outRect.bottom = mSpaces
        outRect.right = mSpaces

//        // Add top margin only for the first item to avoid double space between items
//        if (parent.getChildAdapterPosition(view) == 0) outRect.top = mSpaces
    }
}
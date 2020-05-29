package com.sdei.charmr.utils

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class SpaceItemDecoration(spacing: Int,spanCount:Int,includeEdge:Boolean) : RecyclerView.ItemDecoration() {
    private var spacing:Int = 0
    private var spanCount = 0
    private var includeEdge = false

    init {
        this.spacing = spacing
        this.spanCount = spanCount
        this.includeEdge = includeEdge
    }

    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
        /*outRect.left = space
        outRect.top = space
        outRect.bottom = space
        // Add top margin only for the first item to avoid double space between items
        if(parent.getChildLayoutPosition(view)==0){
           outRect.top = space
        }else{
            outRect.top = 0
        }*/


        var position = parent.getChildAdapterPosition(view); // item position
        var column = position % spanCount; // item column

        if (includeEdge) {
            outRect.left = spacing - column * spacing / spanCount; // spacing - column * ((1f / spanCount) * spacing)
            outRect.right = (column + 1) * spacing / spanCount; // (column + 1) * ((1f / spanCount) * spacing)

            if (position < spanCount) { // top edge
                outRect.top = spacing;
            }
            outRect.bottom = spacing; // item bottom
        } else {
            outRect.left = column * spacing / spanCount; // column * ((1f / spanCount) * spacing)
            outRect.right = spacing - (column + 1) * spacing / spanCount; // spacing - (column + 1) * ((1f /    spanCount) * spacing)
            if (position >= spanCount) {
                outRect.top = spacing; // item top
            }
        }
    }
}


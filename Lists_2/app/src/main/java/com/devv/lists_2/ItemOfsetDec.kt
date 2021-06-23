package com.devv.lists_2

import android.content.Context
import android.graphics.Rect
import android.util.DisplayMetrics
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class ItemOfsetDec(private val context: Context) : RecyclerView.ItemDecoration() {

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State,
    ) {
        val offset = 10.fromDpToPixel(context)
        with(outRect) {
            left = offset
            top = offset
            bottom = offset
            right = offset
        }
    }

    private fun Int.fromDpToPixel(context: Context): Int {
        val density = context.resources.displayMetrics.densityDpi
        val pixelInDp = density / DisplayMetrics.DENSITY_DEFAULT
        return this * pixelInDp
    }
}
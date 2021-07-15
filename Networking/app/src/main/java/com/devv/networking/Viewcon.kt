package com.devv.networking

import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.View
import androidx.annotation.LayoutRes

fun ViewGroup.inflate(
    @LayoutRes layoutRes: Int,
    attachToRoot: Boolean = false,
): View {
    return LayoutInflater.from(context).inflate(layoutRes, this, attachToRoot)
}

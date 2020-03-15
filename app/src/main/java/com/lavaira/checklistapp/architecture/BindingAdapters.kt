package com.lavaira.checklistapp.architecture

import androidx.databinding.BindingAdapter
import android.widget.ImageView
import androidx.annotation.ColorRes
import com.bumptech.glide.Glide

/****
 * Keep all static binding adapters here
 * Author: Lajesh Dineshkumar
 * Created on: 15/03/20
 * Modified on: 15/03/20
 *****/
object BindingAdapters {
    @JvmStatic
    @BindingAdapter("bind:imageUrl")
    fun loadImage(view: ImageView, url: String) {
        Glide.with(view.context).load(url)
            .override(150, 150)
            .centerCrop()
            .into(view)
    }

    @JvmStatic
    @BindingAdapter("swipeListener", "colorScheme", "hideRefresh", requireAll = false)
    fun setSwipRefreshListener(
        view: androidx.swiperefreshlayout.widget.SwipeRefreshLayout,
        listener: androidx.swiperefreshlayout.widget.SwipeRefreshLayout.OnRefreshListener?, @ColorRes color: Int?, refresh: Boolean?
    ) {
        refresh?.let {
            if (it) {
                view.isRefreshing = !it
            }
        }
        listener?.let {
            view.setOnRefreshListener(listener)
        }
        color?.let {
            view.setColorSchemeColors(it)
        }
    }
}


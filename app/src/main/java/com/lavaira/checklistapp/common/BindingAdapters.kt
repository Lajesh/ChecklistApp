package com.lavaira.checklistapp.common

import android.content.res.Resources
import android.view.View
import androidx.databinding.BindingAdapter
import com.google.android.material.textfield.TextInputLayout
import timber.log.Timber

/****
 * File Description
 * Author: Lajesh Dineshkumar
 * Company: Farabi Technologies
 * Created on: 2020-03-15
 * Modified on: 2020-03-15
 *****/
object BindingAdapters {

    @JvmStatic
    @BindingAdapter("field_error")
    fun setError(editText: TextInputLayout, resourse: Int?) {
        resourse?.let {
            if (resourse != -1) {
                try {
                    editText.error = editText.context.getString(resourse)
                } catch (e: Resources.NotFoundException) {
                    Timber.e(e)
                }
            }
        }
    }


    @JvmStatic
    @BindingAdapter("progress_visibility")
    fun setProgressBarVisibility(view: View, visibility: Boolean?) {
        visibility?.let {
            view.visibility = if (it) View.VISIBLE else View.GONE
        } ?: run { view.visibility = View.GONE }
    }
}
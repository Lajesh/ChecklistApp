package com.lavaira.checklistapp.common

import android.content.res.Resources
import android.view.View
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.google.android.material.textfield.TextInputLayout
import com.lavaira.checklistapp.R
import com.lavaira.checklistapp.data.remote.model.response.tasks.Task
import timber.log.Timber
import java.text.SimpleDateFormat
import java.util.*

/****
 * Keep all common binding adapters here
 * Author: Lajesh Dineshkumar
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
    @BindingAdapter("field_bg")
    fun setBackground(view: View, task: Task){
        when (task.status) {
            Constants.TASK_STATUS.TASK_TODO -> {
                view.setBackgroundColor(view.context.resources.getColor(R.color.color_grey))
                return
            }
            Constants.TASK_STATUS.TASK_INPROGRESS -> {
                view.setBackgroundColor(view.context.resources.getColor(R.color.color_orange))
                return
            }
            Constants.TASK_STATUS.TASK_COMPLETED -> {
                view.setBackgroundColor(view.context.resources.getColor(R.color.color_green))
                return
            }
        }


        task.endDate?.let {
            try{
                if(SimpleDateFormat("dd/MM/YYYY").parse(it).before(Date())){
                    if(task.status != Constants.TASK_STATUS.TASK_TODO ||
                        task.status != Constants.TASK_STATUS.TASK_INPROGRESS || task.status != Constants.TASK_STATUS.TASK_COMPLETED)
                        view.setBackgroundColor(view.context.resources.getColor(R.color.color_red))
                }
            }catch (ex: Exception){
                Timber.log(1, ex.message)
            }

        }

    }

    @JvmStatic
    @BindingAdapter("error")
    fun setError(textView: TextView, resourse: Int?) {
        resourse?.let {
            if (resourse != -1) {
                try {
                    textView.text = textView.context.getString(resourse)
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
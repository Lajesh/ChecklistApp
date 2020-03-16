package com.lavaira.checklistapp.view.fragment.dashboard

import android.os.Bundle
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.Observer
import com.lavaira.checklistapp.BR
import com.lavaira.checklistapp.R
import com.lavaira.checklistapp.contract.SubscriptionContract
import com.lavaira.checklistapp.databinding.DialogFragmentAddTaskBinding
import com.lavaira.checklistapp.view.fragment.base.BaseBottomSheetDialogFragment

/****
 * Add Task Dialog fragment
 * Author: Lajesh Dineshkumar
 * Created on: 2020-03-16
 * Modified on: 2020-03-16
 *****/
class AddTaskDialogFragment : BaseBottomSheetDialogFragment<AddTaskViewModel, DialogFragmentAddTaskBinding>(),
SubscriptionContract{
    override val layoutRes: Int
        get() = R.layout.dialog_fragment_add_task
    override val bindingVariable: Int
        get() = BR.viewModel
    override val subscriptionContract: SubscriptionContract?
        get() = this
    override fun getViewModel(): Class<AddTaskViewModel> {
        return AddTaskViewModel::class.java
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(DialogFragment.STYLE_NORMAL, R.style.DialogStyle)
    }

    override fun subscribeNavigationEvent() {
        super.subscribeNavigationEvent()
        viewModel.addTaskResponse.observe(this, Observer {
            dismiss()
        })
    }
}
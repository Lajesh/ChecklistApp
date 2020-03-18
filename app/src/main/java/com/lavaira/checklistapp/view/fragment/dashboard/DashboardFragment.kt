package com.lavaira.checklistapp.view.fragment.dashboard

import android.os.Bundle
import androidx.lifecycle.Observer
import cafe.adriel.kbus.KBus
import com.lavaira.checklistapp.BR
import com.lavaira.checklistapp.R
import com.lavaira.checklistapp.contract.SubscriptionContract
import com.lavaira.checklistapp.data.remote.model.EventMessage
import com.lavaira.checklistapp.databinding.FragmentDashboardBinding
import com.lavaira.checklistapp.view.fragment.base.BaseFragment

/****
 * Dashboard Fragment
 * Author: Lajesh Dineshkumar
 * Company: Farabi Technologies
 * Created on: 2020-03-16
 * Modified on: 2020-03-16
 *****/
class DashboardFragment : BaseFragment<DashboardViewModel, FragmentDashboardBinding>(), SubscriptionContract{

    override val layoutRes: Int
        get() = R.layout.fragment_dashboard
    override val bindingVariable: Int
        get() = BR.viewModel
    override val subscriptionContract: SubscriptionContract?
        get() = this

    override fun getViewModel(): Class<DashboardViewModel> {
        return DashboardViewModel::class.java
    }

    override fun getTitle(): String {
       return getString(R.string.title_dashboard)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.retrieveTasks()
    }

    override fun subscribeNavigationEvent() {
        super.subscribeNavigationEvent()
        viewModel.addTaskEvent.observe(this, Observer{
            AddTaskDialogFragment().show(
                activity?.supportFragmentManager!!,
                AddTaskDialogFragment::class.java.canonicalName
            )
        })

        viewModel.retrieveTasksResponse.observe(this, Observer {

                viewModel.items.clear()

                if(null != it.data)
                    viewModel.items.addAll(it.data as ArrayList)

            when {
                it.status.isLoading() -> {
                    viewModel.loadingStatus.value = true
                }
                it.status.isSuccessful() -> {
                    viewModel.loadingStatus.value = false
                }
                it.status.isError() -> {
                    viewModel.loadingStatus.value = false
                }
            }
        })


        viewModel.taskSelectedEvent.observe(this, Observer {
            AddTaskDialogFragment().show(
                activity?.supportFragmentManager!!,
                AddTaskDialogFragment::class.java.canonicalName
            )
        })
    }

    override fun onStart() {
        super.onStart()
        KBus.subscribe<EventMessage>(this) {
            viewModel.retrieveTasks()
        }
    }

    override fun onStop() {
        super.onStop()
        KBus.unsubscribe(this)
    }
}
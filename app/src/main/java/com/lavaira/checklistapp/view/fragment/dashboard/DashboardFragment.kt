package com.lavaira.checklistapp.view.fragment.dashboard

import androidx.lifecycle.Observer
import com.lavaira.checklistapp.BR
import com.lavaira.checklistapp.R
import com.lavaira.checklistapp.contract.SubscriptionContract
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

    override fun subscribeNavigationEvent() {
        super.subscribeNavigationEvent()
        viewModel.addTaskEvent.observe(this, Observer{
            AddTaskDialogFragment().show(
                activity?.supportFragmentManager!!,
                AddTaskDialogFragment::class.java.canonicalName
            )
        })
    }
}
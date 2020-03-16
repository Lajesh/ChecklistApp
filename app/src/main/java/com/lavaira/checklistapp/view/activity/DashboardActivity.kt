package com.lavaira.checklistapp.view.activity

import com.lavaira.checklistapp.BR
import com.lavaira.checklistapp.R
import com.lavaira.checklistapp.databinding.ActivityDashboardBinding
import com.lavaira.checklistapp.view.activity.base.BaseActivity
import com.lavaira.checklistapp.viewmodel.EmptyViewModel

/****
 * Dashboard Activity
 * Author: Lajesh Dineshkumar
 * Created on: 2020-03-15
 * Modified on: 2020-03-15
 *****/
class DashboardActivity : BaseActivity<EmptyViewModel, ActivityDashboardBinding>(){
    override val layoutRes: Int
        get() = R.layout.activity_dashboard
    override val bindingVariable: Int
        get() = BR.viewModel

    override fun getViewModel(): Class<EmptyViewModel> {
        return EmptyViewModel::class.java
    }
}
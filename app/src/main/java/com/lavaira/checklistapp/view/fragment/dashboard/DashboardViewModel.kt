package com.lavaira.checklistapp.view.fragment.dashboard

import com.google.firebase.auth.FirebaseAuth
import com.lavaira.checklistapp.architecture.SingleLiveEvent
import com.lavaira.checklistapp.common.AppSession
import com.lavaira.checklistapp.viewmodel.BaseViewModel
import javax.inject.Inject

/****
 * Dashboard viewModel
 * Author: Lajesh Dineshkumar
 * Company: Farabi Technologies
 * Created on: 2020-03-16
 * Modified on: 2020-03-16
 *****/
class DashboardViewModel @Inject constructor() : BaseViewModel(){

    init {
        AppSession.user = FirebaseAuth.getInstance().currentUser
    }

    val addTaskEvent = SingleLiveEvent<Void>()

    fun addTask(){
        addTaskEvent.call()
    }
}
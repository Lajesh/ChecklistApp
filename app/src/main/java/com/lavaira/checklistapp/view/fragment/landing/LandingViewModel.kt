package com.lavaira.checklistapp.view.fragment.landing

import com.lavaira.checklistapp.architecture.SingleLiveEvent
import com.lavaira.checklistapp.viewmodel.BaseViewModel
import javax.inject.Inject

/****
 * Landing screen view model
 * Author: Lajesh Dineshkumar
 * Company: Farabi Technologies
 * Created on: 2020-03-15
 * Modified on: 2020-03-15
 *****/
class LandingViewModel @Inject constructor() : BaseViewModel() {

    val registrationEvent = SingleLiveEvent<Void>()


    fun doRegister(){
        registrationEvent.call()
    }
}
package com.lavaira.checklistapp.view.fragment.dashboard

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.google.firebase.auth.FirebaseAuth
import com.lavaira.checklistapp.architecture.AbsentLiveData
import com.lavaira.checklistapp.architecture.SingleLiveEvent
import com.lavaira.checklistapp.common.AppSession
import com.lavaira.checklistapp.data.remote.api.Resource
import com.lavaira.checklistapp.data.remote.model.response.tasks.Task
import com.lavaira.checklistapp.repository.UserRepository
import com.lavaira.checklistapp.viewmodel.BaseViewModel
import javax.inject.Inject

/****
 * Dashboard viewModel
 * Author: Lajesh Dineshkumar
 * Company: Farabi Technologies
 * Created on: 2020-03-16
 * Modified on: 2020-03-16
 *****/
class DashboardViewModel @Inject constructor(private val userRepository: UserRepository) : BaseViewModel(){


    val retreiveTaskRequest = MutableLiveData<String>()
    val addTaskEvent = SingleLiveEvent<Void>()

    init {
        AppSession.user = FirebaseAuth.getInstance().currentUser
    }

    val retrieveTasksResponse : LiveData<Resource<List<Task>>> = Transformations
        .switchMap(retreiveTaskRequest){ request->
            if(null == request)
                AbsentLiveData.create()
            else{
                userRepository.retrieveTasks(request)
            }
        }



    fun addTask(){
        addTaskEvent.call()
    }


    fun retrieveTasks() {
        retreiveTaskRequest.value = AppSession.user?.uid
    }

}
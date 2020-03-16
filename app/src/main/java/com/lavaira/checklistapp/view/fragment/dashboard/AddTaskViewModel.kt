package com.lavaira.checklistapp.view.fragment.dashboard

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.lavaira.checklistapp.architecture.AbsentLiveData
import com.lavaira.checklistapp.data.remote.api.Resource
import com.lavaira.checklistapp.data.remote.model.response.tasks.Task
import com.lavaira.checklistapp.repository.UserRepository
import com.lavaira.checklistapp.viewmodel.BaseViewModel
import javax.inject.Inject

/****
 * AddTaskViewModel
 * Author: Lajesh Dineshkumar
 * Created on: 2020-03-16
 * Modified on: 2020-03-16
 *****/
class AddTaskViewModel @Inject constructor(private val userRepository: UserRepository) : BaseViewModel() {

    val addTaskRequest = MutableLiveData<Task>()
    val taskTitle = MutableLiveData<String>()


    val addTaskResponse: LiveData<Resource<Task>> = Transformations
        .switchMap(addTaskRequest){ request->
            if(null == request)
                AbsentLiveData.create()
            else{
                userRepository.addTask(request)
            }
        }

    fun addTask(){
        addTaskRequest.value = Task(title = taskTitle.value.toString())
    }
}
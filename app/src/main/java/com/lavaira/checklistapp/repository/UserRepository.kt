package com.lavaira.checklistapp.repository

import androidx.lifecycle.LiveData
import com.lavaira.checklistapp.architecture.AbsentLiveData
import com.lavaira.checklistapp.common.AppSession
import com.lavaira.checklistapp.data.remote.api.*
import com.lavaira.checklistapp.data.remote.model.request.register.RegistrationRequest
import com.lavaira.checklistapp.data.remote.model.response.registration.RegistrationResponse
import com.lavaira.checklistapp.data.remote.model.response.tasks.Task
import com.lavaira.checklistapp.executors.AppExecutors
import com.lavaira.checklistapp.utils.SafeLet
import java.util.*
import javax.inject.Inject

/****
 *  User Repository which keeps all the service calls related to User entity
 * Author: Lajesh Dineshkumar
 * Created on: 2020-03-15
 * Modified on: 2020-03-15
 *****/
class UserRepository @Inject constructor(private val api: Api, private val appExecutors: AppExecutors = AppExecutors())
    :  SafeLet{

    fun saveUserProfile(registrationRequest: RegistrationRequest) : LiveData<Resource<RegistrationResponse>> {
            return object : NetworkResource<RegistrationResponse>() {
                override fun loadFromNetwork(): LiveData<ApiResponse<RegistrationResponse>> {
                    return api.register(AppSession.user?.uid.toString() + ".json",  registrationRequest.params())
                }
            }.asLiveData()
        }


    fun addTask(task: Task) : LiveData<Resource<Task>>{
        return object : NetwordAndDBBoundResource<Task, Task>(appExecutors){
            override fun saveCallResult(item: Task) {
            }

            override fun shouldFetch(data: Task?): Boolean {
                return true
            }


            override fun createCall(): LiveData<ApiResponse<Task>> {
               return api.addTask(AppSession.user?.uid.toString(), UUID.randomUUID().toString() + ".json", task.params())
            }

            override fun loadFromDb(): LiveData<Task> {
                return AbsentLiveData.create()
            }


        }.asLiveData()
    }


}
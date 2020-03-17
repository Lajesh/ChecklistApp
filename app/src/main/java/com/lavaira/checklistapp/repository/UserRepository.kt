package com.lavaira.checklistapp.repository

import androidx.lifecycle.LiveData
import com.google.gson.Gson
import com.google.gson.JsonElement
import com.google.gson.JsonObject
import com.lavaira.checklistapp.architecture.AbsentLiveData
import com.lavaira.checklistapp.common.AppSession
import com.lavaira.checklistapp.data.local.dao.TasksDao
import com.lavaira.checklistapp.data.remote.api.*
import com.lavaira.checklistapp.data.remote.model.request.register.RegistrationRequest
import com.lavaira.checklistapp.data.remote.model.response.registration.RegistrationResponse
import com.lavaira.checklistapp.data.remote.model.response.tasks.Task
import com.lavaira.checklistapp.data.remote.model.response.tasks.TasksResponse
import com.lavaira.checklistapp.executors.AppExecutors
import com.lavaira.checklistapp.utils.SafeLet
import java.util.*
import javax.inject.Inject
import kotlin.collections.ArrayList

/****
 *  User Repository which keeps all the service calls related to User entity
 * Author: Lajesh Dineshkumar
 * Created on: 2020-03-15
 * Modified on: 2020-03-15
 *****/
class UserRepository @Inject constructor(
    private val api: Api,
    private val tasksDao: TasksDao,
    private val appExecutors: AppExecutors = AppExecutors()
) : SafeLet {

    fun saveUserProfile(registrationRequest: RegistrationRequest): LiveData<Resource<RegistrationResponse>> {
        return object : NetworkResource<RegistrationResponse>() {
            override fun loadFromNetwork(): LiveData<ApiResponse<RegistrationResponse>> {
                return api.register(
                    AppSession.user?.uid.toString() + ".json",
                    registrationRequest.params()
                )
            }
        }.asLiveData()
    }


    fun addOrUpdateTask(task: Task, isUpdate: Boolean): LiveData<Resource<Task>> {
        return object : NetwordAndDBBoundResource<Task, Task>(appExecutors) {
            override fun saveCallResult(item: Task) {
                //tasksDao.inserTask(item)
            }

            override fun shouldFetch(data: Task?): Boolean {
                return true
            }

            override fun createCall(): LiveData<ApiResponse<Task>> {
                var taskNodeId: String? = if(isUpdate)
                    task.nodeId
                else
                    UUID.randomUUID().toString()
                return api.addOrUpdateTask(
                    AppSession.user?.uid.toString(),
                    "$taskNodeId.json",
                    task.params()
                )
            }

            override fun loadFromDb(): LiveData<Task> {
                return AbsentLiveData.create()
            }


        }.asLiveData()
    }


    fun retrieveTasks(userId: String?): LiveData<Resource<List<Task>>> {

        return object : NetwordAndDBBoundResource<List<Task>, JsonObject>(appExecutors) {
            override fun saveCallResult(item: JsonObject) {
                val taskResponse = TasksResponse()
                taskResponse.tasks = parseRetreieveTasksResponse(item.toString())
                if (taskResponse.tasks.isNotEmpty()) {
                    tasksDao.deleteAllTasks()
                    tasksDao.insertTasks(taskResponse.tasks)
                }

            }

            override fun shouldFetch(data: List<Task>?): Boolean {
                return true
            }

            override fun loadFromDb(): LiveData<List<Task>> {
                return tasksDao.getTasks()
            }

            override fun createCall(): LiveData<ApiResponse<JsonObject>> {
                return api.getTasks(userId.toString())
            }

        }.asLiveData()
    }


    fun parseRetreieveTasksResponse(jsonString: String): List<Task> {
        val tasksList = ArrayList<Task>()
        val jsonObject = Gson().fromJson(jsonString, JsonObject::class.java)
        jsonObject.entrySet().iterator().forEach { itm: Map.Entry<String, JsonElement> ->
            val key = itm.key as String
            val value = itm.value as JsonObject
            val task = Gson().fromJson(value.toString(), Task::class.java)
            task.nodeId = key
            tasksList.add(task)
        }
        return tasksList
    }


}
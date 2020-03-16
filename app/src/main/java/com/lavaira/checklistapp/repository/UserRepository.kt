package com.lavaira.checklistapp.repository

import com.lavaira.checklistapp.common.AppSession
import com.lavaira.checklistapp.data.remote.api.Api
import com.lavaira.checklistapp.data.remote.model.request.register.RegistrationRequest
import com.lavaira.checklistapp.data.remote.api.ResponseListener
import com.lavaira.checklistapp.data.remote.model.response.registration.RegistrationResponse
import com.lavaira.checklistapp.schedulers.SchedulerContract
import com.lavaira.checklistapp.utils.SafeLet
import javax.inject.Inject

/****
 *  User Repository which keeps all the service calls related to User entity
 * Author: Lajesh Dineshkumar
 * Company: Farabi Technologies
 * Created on: 2020-03-15
 * Modified on: 2020-03-15
 *****/
class UserRepository @Inject constructor(private val api: Api, scheduler: SchedulerContract)
    : BaseRepository(scheduler), SafeLet{



    fun saveUserProfile(registrationRequest: RegistrationRequest, responseListener: ResponseListener<RegistrationResponse>){

        safeLet(AppSession.user?.uid, AppSession.idToken){
            uid, token -> performRequest(api.register("$uid.json", token, registrationRequest.params()), responseListener)
        }

    }
}
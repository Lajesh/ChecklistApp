package com.lavaira.checklistapp.repository

import com.lavaira.checklistapp.common.Constants
import com.lavaira.checklistapp.data.remote.Api
import com.lavaira.checklistapp.data.remote.model.NewsListResponse
import com.lavaira.checklistapp.data.remote.model.request.register.RegistrationRequest
import com.lavaira.checklistapp.data.remote.model.response.ResponseListener
import com.lavaira.checklistapp.data.remote.model.response.registration.RegistrationResponse
import com.lavaira.checklistapp.schedulers.SchedulerContract
import javax.inject.Inject

/****
 *  User Repository which keeps all the service calls related to User entity
 * Author: Lajesh Dineshkumar
 * Company: Farabi Technologies
 * Created on: 2020-03-15
 * Modified on: 2020-03-15
 *****/
class UserRepository @Inject constructor(private val api: Api, scheduler: SchedulerContract)
    : BaseRepository(scheduler) {


    /**
     * This method calls the service for getting the list of news.
     * @param index - Index number of the news list
     * @param responseListener: Response Listener Callback
     */
    fun getNews(index: Int, responseListener: ResponseListener<NewsListResponse>) {
        performRequest(api.getPopularNews(index), responseListener)
    }


    fun registerUser(registrationRequest: RegistrationRequest, responseListener: ResponseListener<RegistrationResponse>){
        performRequest(api.register(Constants.AUTH_URLS.SIGNUP_URL, registrationRequest.params()), responseListener)
    }
}
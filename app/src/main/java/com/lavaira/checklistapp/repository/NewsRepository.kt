package com.lavaira.checklistapp.repository

import com.lavaira.checklistapp.data.remote.Api
import com.lavaira.checklistapp.data.remote.model.NewsListResponse
import com.lavaira.checklistapp.data.remote.response.ResponseListener
import com.lavaira.checklistapp.schedulers.SchedulerContract
import javax.inject.Inject

/****
 * User Repository which keeps all the service calls related to User entity
 * Author: Lajesh Dineshkumar
 * Created on: 15/03/20
 * Modified on: 15/03/20
 *****/
class NewsRepository@Inject constructor(private val api: Api, scheduler: SchedulerContract)
    : BaseRepository(scheduler){


    /**
     * This method calls the service for getting the list of news.
     * @param index - Index number of the news list
     * @param responseListener: Response Listener Callback
     */
    fun getNews(index : Int, responseListener: ResponseListener<NewsListResponse>){
        performRequest(api.getPopularNews(index), responseListener)
    }

}
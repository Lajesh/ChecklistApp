package com.lavaira.checklistapp.data.remote

import com.lavaira.checklistapp.data.remote.model.NewsListResponse
import com.lavaira.checklistapp.data.remote.model.response.registration.RegistrationResponse
import io.reactivex.Observable
import retrofit2.http.*


/****
 * Keep all the REST Apis here
 * Author: Lajesh Dineshkumar
 * Created on: 15/03/20
 * Modified on: 15/03/20
 *****/
interface Api {

    @GET("svc/mostpopular/v2/mostviewed/all-sections/{index}.json")
    fun getPopularNews(@Path("index") index: Int): Observable<NewsListResponse>


    @POST
    fun register(@Url url: String,  @Body map: HashMap<String, String>) : Observable<RegistrationResponse>

}
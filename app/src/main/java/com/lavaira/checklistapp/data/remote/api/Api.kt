package com.lavaira.checklistapp.data.remote.api

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

    @PUT("Users/{userid}")
    fun register(@Path("userid")userId: String, @Query("auth") idToken: String,  @Body map: HashMap<String, String>) : Observable<RegistrationResponse>

}
package com.lavaira.checklistapp.data.remote.model.response.error

import com.google.gson.annotations.SerializedName

/****
 * Data Model class which represents the error response
 * Author: Lajesh Dineshkumar
 * Created on: 15/03/20
 * Modified on: 15/03/20
 *****/
data class ErrorResponse (@SerializedName("error")
                           var errors: Error? = null)
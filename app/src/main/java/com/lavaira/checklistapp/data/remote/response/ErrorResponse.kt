package com.lavaira.checklistapp.data.remote.response

import com.google.gson.annotations.SerializedName

/****
 * Data Model class which represents the error response
 * Author: Lajesh Dineshkumar
 * Created on: 15/03/20
 * Modified on: 15/03/20
 *****/
data class ErrorResponse (@SerializedName("errorCode") var errorCode:  String = "",
                          @SerializedName("errorDescription") var errorDescription: String = "")
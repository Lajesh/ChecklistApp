package com.lavaira.checklistapp.data.remote.model

import com.google.gson.annotations.SerializedName

/****
 * Media Metadata class
 * Author: Lajesh Dineshkumar
 * Created on: 15/03/20
 * Modified on: 15/03/20
 *****/
data class MediaMetadata(@SerializedName("url") var imageUrl : String? = "",
                         @SerializedName("format") var format: String? = "")
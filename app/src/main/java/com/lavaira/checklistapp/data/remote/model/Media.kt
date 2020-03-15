package com.lavaira.checklistapp.data.remote.model

import com.google.gson.annotations.SerializedName

/****
 * Media Data Model
 * Author: Lajesh Dineshkumar
 * Created on: 15/03/20
 * Modified on: 15/03/20
 *****/
data class Media(@SerializedName("media-metadata") var mediaMetadata: ArrayList<MediaMetadata>? = ArrayList())

package com.lavaira.checklistapp.data.remote.model

import com.google.gson.annotations.SerializedName
import com.lavaira.checklistapp.data.remote.model.Media

/****
 * Data model representing a single news item
 * Author: Lajesh Dineshkumar
 * Created on: 15/03/20
 * Modified on: 15/03/20
 *****/
data class NewsItem (@SerializedName("id") var id: Long? = 0,
                     @SerializedName("title") var title: String? = "",
                     @SerializedName("byline") var authors: String? = "",
                     @SerializedName("published_date") var publishedDate: String? = "",
                     @SerializedName("url") var url: String? = "",
                     @SerializedName("media") var media: ArrayList<Media>? = ArrayList())
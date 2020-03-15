package com.lavaira.checklistapp.data.remote.model

import com.google.gson.annotations.SerializedName
import com.lavaira.checklistapp.data.remote.model.NewsItem

/****
 * Data Model class which represents the list of news
 * Author: Lajesh Dineshkumar
 * Created on: 15/03/20
 * Modified on: 15/03/20
 *****/
data class NewsListResponse(@SerializedName("results") var popularNewsList : ArrayList<NewsItem>? = ArrayList())
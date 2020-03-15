package com.lavaira.checklistapp.view.fragment.news

import androidx.lifecycle.MutableLiveData
import com.lavaira.checklistapp.viewmodel.BaseViewModel

/****
 * News Item ViewModel
 * Author: Lajesh Dineshkumar
 * Created on: 15/03/20
 * Modified on: 15/03/20
 *****/
class NewsItemViewModel() : BaseViewModel() {

    var newsTitle =  MutableLiveData<String?>()
    var newsAuthors = MutableLiveData<String?>()
    var publishedDate = MutableLiveData<String?>()
    var newsUrl = MutableLiveData<String?>()
    var newsThumbnailUrl = MutableLiveData<String?>()


    constructor(title: String?, authors: String?, date: String?, url: String?, urlImage: String?) : this() {
        newsTitle.value = title
        newsAuthors.value = authors
        publishedDate.value = date
        newsUrl.value = url
        newsThumbnailUrl.value = urlImage
    }

}
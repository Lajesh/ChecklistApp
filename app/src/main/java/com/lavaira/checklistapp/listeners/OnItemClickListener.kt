package com.lavaira.checklistapp.listeners

import com.lavaira.checklistapp.view.fragment.news.NewsItemViewModel

/****
 * News List item click listener
 * Author: Lajesh Dineshkumar
 * Created on: 15/03/20
 * Modified on: 15/03/20
 *****/
interface OnItemClickListener {
    fun onItemClick(item: NewsItemViewModel)
}
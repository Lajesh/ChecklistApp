package com.lavaira.checklistapp.data.local.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

/****
 * Abstracts access to Tasks database
 * Author: Lajesh Dineshkumar
 * Company: Farabi Technologies
 * Created on: 2020-03-16
 * Modified on: 2020-03-16
 *****/
@Dao
interface TasksDao {
//    /**
//     * Insert articles into the database
//     */
//    @Insert(onConflict = OnConflictStrategy.REPLACE)
//    fun insertArticles(articles: List<NewsArticles>): List<Long>
//
//    /**
//     * Get all the articles from database
//     */
//    @Query("SELECT * FROM news_table")
//    fun getNewsArticles(): LiveData<List<NewsArticles>>
//
//    @Query("DELETE FROM news_table")
//    abstract fun deleteAllArticles()
}
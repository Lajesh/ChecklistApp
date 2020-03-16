package com.lavaira.checklistapp.data.remote.model.response.tasks

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

/****
 * Model for representing a user task
 * Author: Lajesh Dineshkumar
 * Company: Farabi Technologies
 * Created on: 2020-03-16
 * Modified on: 2020-03-16
 *****/

@Entity(tableName = "tasks_table")
data class Task (
    @PrimaryKey(autoGenerate = true) var id: Int = 0,
    @SerializedName("title") var title: String? = null,
    @SerializedName("description") var description: String? = null,
    @SerializedName("startDate") var startDate: String? = null,
    @SerializedName("endDate") var endDate: String? = null
)
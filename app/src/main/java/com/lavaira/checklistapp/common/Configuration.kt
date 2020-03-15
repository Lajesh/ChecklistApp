package com.lavaira.checklistapp.common

import androidx.annotation.IntDef

/****
 * Keep all the Build/ deployment configurations here
 * Author: Lajesh Dineshkumar
 * Created on: 15/03/20
 * Modified on: 15/03/20
 *****/
object Configuration {

    private const val SIT = 0

    private const val DEV = 1

    private const val UAT = 2

    private const val PROD = 3

    @DeploymentType
    private val defaultEnvironment = DEV

    const val API_KEY = "5Z4MVs7Y1WNAYyA79RE9tpR6s3WQjLie"


    // HOST Urls
    private const val URL_SIT = "" //Put the SIT url here

    private const val URL_DEV = "https://api.nytimes.com/" // Put the development url here

    private const val URL_PROD = "" // Put the production url here

    private const val URL_UAT = "" // Put the UAT url here

    val baseURL: String
        get() {

            return when (defaultEnvironment) {

                SIT -> URL_SIT

                DEV -> URL_DEV

                UAT -> URL_UAT

                PROD -> URL_PROD

                else -> URL_DEV
            }
        }


    @IntDef(SIT, DEV, UAT, PROD)
    private annotation class DeploymentType
}
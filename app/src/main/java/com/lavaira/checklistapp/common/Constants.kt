package com.lavaira.checklistapp.common

import androidx.annotation.StringDef
import com.lavaira.checklistapp.common.Constants.AUTH_URLS.Companion.RESETPW_URL
import com.lavaira.checklistapp.common.Constants.AUTH_URLS.Companion.SIGNIN_URL
import com.lavaira.checklistapp.common.Constants.AUTH_URLS.Companion.SIGNUP_URL
import com.lavaira.checklistapp.common.Constants.REGISTRATION_ATTRIBUTES.Companion.ADDRESS
import com.lavaira.checklistapp.common.Constants.REGISTRATION_ATTRIBUTES.Companion.EMAIL
import com.lavaira.checklistapp.common.Constants.REGISTRATION_ATTRIBUTES.Companion.FIRST_NAME
import com.lavaira.checklistapp.common.Constants.REGISTRATION_ATTRIBUTES.Companion.LAST_NAME

/****
 * Keep all the common constants here
 * Author: Lajesh Dineshkumar
 * Created on: 15/03/20
 * Modified on: 15/03/20
 *****/
object Constants {
    const val QUERY_PARAM__APIKEY = "api-key"
    const val PW_LENGTH = 6
    const val BASE_AUTH_URL = "https://identitytoolkit.googleapis.com/v1/accounts:"


    @StringDef(EMAIL, FIRST_NAME, LAST_NAME, ADDRESS)
    @Retention(AnnotationRetention.SOURCE)
    annotation class REGISTRATION_ATTRIBUTES {
        companion object {
            const val EMAIL = "email"
            const val FIRST_NAME = "firstname"
            const val LAST_NAME = "lastname"
            const val ADDRESS = "address"
        }
    }


    @StringDef(SIGNUP_URL, SIGNIN_URL, RESETPW_URL)
    @Retention(AnnotationRetention.SOURCE)
    annotation class AUTH_URLS{
        companion object{
            const val SIGNUP_URL = "${BASE_AUTH_URL}signUp?key=${Configuration.API_KEY}"
            const val SIGNIN_URL = "${BASE_AUTH_URL}signInWithPassword?key=${Configuration.API_KEY}"
            const val RESETPW_URL = ""
        }
    }
}
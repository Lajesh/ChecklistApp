package com.lavaira.checklistapp.data.remote.model.request.register

import com.lavaira.checklistapp.common.Constants
import com.lavaira.checklistapp.data.remote.model.request.base.BaseRequest

/****
 * Registration Request Model
 * Author: Lajesh Dineshkumar
 * Company: Farabi Technologies
 * Created on: 2020-03-15
 * Modified on: 2020-03-15
 *****/
data class RegistrationRequest(
    val email: String,
    val password: String,
    val returnSecureToken: Boolean
) : BaseRequest() {

    override fun params(): HashMap<String, String> {
        val registrationParams = HashMap<String, String>()
        registrationParams[Constants.REGISTRATION_ATTRIBUTES.EMAIL] = email
        registrationParams[Constants.REGISTRATION_ATTRIBUTES.PASSWORD] = password
        registrationParams[Constants.REGISTRATION_ATTRIBUTES.SECURE_TOKEN] = returnSecureToken.toString()
        return registrationParams
    }
}
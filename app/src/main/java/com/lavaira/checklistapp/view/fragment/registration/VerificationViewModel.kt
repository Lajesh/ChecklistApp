package com.lavaira.checklistapp.view.fragment.registration

import androidx.lifecycle.MutableLiveData
import com.google.firebase.auth.FirebaseUser
import com.lavaira.checklistapp.R
import com.lavaira.checklistapp.architecture.SingleLiveEvent
import com.lavaira.checklistapp.common.AppSession
import com.lavaira.checklistapp.data.remote.model.response.ApiResponse
import com.lavaira.checklistapp.data.remote.model.response.ResponseListener
import com.lavaira.checklistapp.data.remote.model.response.ResponseStatus
import com.lavaira.checklistapp.data.remote.model.response.registration.Verification
import com.lavaira.checklistapp.repository.AuthRepository
import com.lavaira.checklistapp.viewmodel.BaseViewModel
import javax.inject.Inject

/****
 * Verficiation viewmodel
 * Author: Lajesh Dineshkumar
 * Company: Farabi Technologies
 * Created on: 2020-03-15
 * Modified on: 2020-03-15
 *****/
class VerificationViewModel @Inject constructor(private val authRepository: AuthRepository)
    :BaseViewModel() {

    val otp = MutableLiveData<String>()
    val otpFieldErrorValue = MutableLiveData<Int>()
    val verficiationSuccessEvent = SingleLiveEvent<Void>()
    val resendOtpEvent = SingleLiveEvent<Void>()


    fun validateOtp(){
        if(validateOtpField()){
            AppSession.verificationCode?.let {
                authRepository.validateOtp(it, otp.value!!, object : ResponseListener<FirebaseUser>{
                    override fun onStart() {
                        loadingStatus.value = true
                    }

                    override fun onFinish() {
                        loadingStatus.value = false
                    }

                    override fun onResponse(result: ApiResponse<FirebaseUser>) {
                        loadingStatus.value = false
                        if(result.status == ResponseStatus.SUCCESS){
                            AppSession.user = result.data
                            verficiationSuccessEvent.call()
                        }else{
                            serviceErrorEvent.value = result.error?.message
                        }
                    }

                })
            }

        }
    }


    fun resendOtp(){
        authRepository.resendVerificationCode(AppSession.phoneNumber, AppSession.resendToken!!, object : ResponseListener<Verification>{
            override fun onStart() {
                loadingStatus.value = true
            }

            override fun onFinish() {
                loadingStatus.value = false
            }

            override fun onResponse(result: ApiResponse<Verification>) {
                loadingStatus.value = false
                if(result.status == ResponseStatus.SUCCESS) {
                    AppSession.verificationCode = result.data?.verificationCode
                    AppSession.resendToken = result.data?.resendToken
                    resendOtpEvent.call()
                }else
                    serviceErrorEvent.value = result.error?.message

            }

        })
    }

    private fun validateOtpField() : Boolean{
        return if (otp.value.isNullOrEmpty() || otp.value?.length!! < 6) {
            otpFieldErrorValue.value = R.string.error_msg_invalid_otp
            false
        } else {
            otpFieldErrorValue.value = R.string.error_msg_empty
            true
        }
    }


}
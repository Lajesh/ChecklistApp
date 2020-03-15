package com.lavaira.checklistapp.repository

import com.google.android.gms.tasks.CancellationToken
import com.google.android.gms.tasks.TaskExecutors
import com.google.firebase.FirebaseException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthProvider
import com.lavaira.checklistapp.data.remote.model.response.ApiResponse
import com.lavaira.checklistapp.data.remote.model.response.ResponseListener
import com.lavaira.checklistapp.data.remote.model.response.ResponseStatus
import com.lavaira.checklistapp.data.remote.model.response.registration.Verification
import timber.log.Timber
import java.util.concurrent.TimeUnit
import javax.inject.Inject

/****
 * Authentication Repository
 * Author: Lajesh Dineshkumar
 * Company: Farabi Technologies
 * Created on: 2020-03-15
 * Modified on: 2020-03-15
 *****/
class AuthRepository @Inject constructor(private val firebaseAuth: FirebaseAuth,
                                         private val phoneAuthProvider: PhoneAuthProvider)
{


    /**
     * Method that sends the verification code to mobile number
     * @param phoneNumber : UAE Mobile Number
     * @param responseListener: Responselistener callback
     */
    fun sendVerificationCode(phoneNumber: String, responseListener: ResponseListener<Verification>){
        responseListener.onStart()
        phoneAuthProvider.verifyPhoneNumber(
            phoneNumber, 1,
            TimeUnit.MINUTES, TaskExecutors.MAIN_THREAD, object : PhoneAuthProvider.OnVerificationStateChangedCallbacks(){
                override fun onVerificationCompleted(p0: PhoneAuthCredential) {
                    Timber.i("Verification completed")
                }

                override fun onVerificationFailed(p0: FirebaseException) {
                    Timber.i("Verification failed${p0.message}")
                    responseListener.onResponse(ApiResponse(ResponseStatus.FAILURE, null, Throwable(p0.message)))
                }

                override fun onCodeSent(verificationCode: String, resendToken: PhoneAuthProvider.ForceResendingToken) {
                    super.onCodeSent(verificationCode, resendToken)
                    Timber.i("Code Sent successfully")
                    responseListener.onResponse(ApiResponse(ResponseStatus.SUCCESS, Verification(verificationCode, resendToken), null))
                }

            })
    }

    fun resendVerificationCode(phoneNumber: String, token: PhoneAuthProvider.ForceResendingToken, responseListener: ResponseListener<Verification>){
        responseListener.onStart()
        phoneAuthProvider.verifyPhoneNumber(
            phoneNumber, 1,
            TimeUnit.MINUTES, TaskExecutors.MAIN_THREAD, object : PhoneAuthProvider.OnVerificationStateChangedCallbacks(){
                override fun onVerificationCompleted(p0: PhoneAuthCredential) {
                    Timber.i("Verification completed")
                }

                override fun onVerificationFailed(p0: FirebaseException) {
                    Timber.i("Verification failed${p0.message}")
                    responseListener.onResponse(ApiResponse(ResponseStatus.FAILURE, null, Throwable(p0.message)))
                }

                override fun onCodeSent(verificationCode: String, resendToken: PhoneAuthProvider.ForceResendingToken) {
                    super.onCodeSent(verificationCode, resendToken)
                    Timber.i("Code Sent successfully")
                    responseListener.onResponse(ApiResponse(ResponseStatus.SUCCESS, Verification(verificationCode, resendToken), null))
                }

            }, token)
    }


    /**
     * Service call to validate the OTP
     * @param verificationId: Verification id
     * @param otp: OTP code recieved in mobile
     * @param responseListener: Responselistener callback
     */
    fun validateOtp(verificationId: String, otp: String, responseListener: ResponseListener<FirebaseUser>){
        responseListener.onStart()
        firebaseAuth.signInWithCredential(PhoneAuthProvider.getCredential(verificationId, otp))
            .addOnCompleteListener {
                if(it.isSuccessful){
                    responseListener.onResponse(ApiResponse(ResponseStatus.SUCCESS, it.result?.user, null))
                }else{
                    responseListener.onResponse(ApiResponse(ResponseStatus.FAILURE, null, Throwable(it.exception?.message)))
                }
            }
    }
}
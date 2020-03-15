package com.lavaira.checklistapp.view.fragment.registration

import androidx.lifecycle.MutableLiveData
import com.lavaira.checklistapp.R
import com.lavaira.checklistapp.common.Constants.PW_LENGTH
import com.lavaira.checklistapp.utils.Validator
import com.lavaira.checklistapp.viewmodel.BaseViewModel
import javax.inject.Inject

/****
 * Registration viewmodel
 * Author: Lajesh Dineshkumar
 * Company: Farabi Technologies
 * Created on: 2020-03-15
 * Modified on: 2020-03-15
 *****/
class RegistrationViewModel @Inject constructor(): BaseViewModel() {

    val firstNameField = MutableLiveData<String>()
    val emailField = MutableLiveData<String>()
    val passwordField = MutableLiveData<String>()
    val confirmPasswordField = MutableLiveData<String>()

    val firstNameFieldErrorValue = MutableLiveData<Int>()
    val emailFieldErrorValue = MutableLiveData<Int>()
    val passwordFieldErrorValue = MutableLiveData<Int>()
    val confirmPasswordFieldErrorValue = MutableLiveData<Int>()



    fun validateFirstName(): Boolean {
        return if(firstNameField.value.isNullOrEmpty()){
            firstNameFieldErrorValue.value = R.string.error_msg_invalid_firstname
            false
        }else{
            firstNameFieldErrorValue.value = R.string.error_msg_empty
            true
        }
    }


    /**
     * validating Email
     */
    fun validateEmail(): Boolean {

        return if (emailField.value.isNullOrEmpty() || !Validator.isValidEmail(emailField.value.toString())) {
            emailFieldErrorValue.value = R.string.error_msg_invalid_email
            false
        } else {
            emailFieldErrorValue.value = R.string.error_msg_empty
            true
        }
    }

    /**
     * validating password field value
     */
    fun validatePassword(): Boolean {
        return if (passwordField.value.isNullOrEmpty()) {
            passwordFieldErrorValue.value = R.string.error_msg_pw_cant_empty
            false
        } else if (passwordField.value!!.length < PW_LENGTH ) {
            passwordFieldErrorValue.value = R.string.error_msg_pw_length
            false
        } else {
            passwordFieldErrorValue.value = R.string.error_msg_empty
            true
        }

    }


    /**
     * validating the confirm password value
     */

    private fun validateConfirmPassword(): Boolean {
        return when {
            confirmPasswordField.value.isNullOrEmpty() -> {
                confirmPasswordFieldErrorValue.value = R.string.error_msg_pw_cant_empty
                false
            }
            confirmPasswordField.value!!.length < PW_LENGTH -> {
                confirmPasswordFieldErrorValue.value = R.string.error_msg_pw_length
                false
            }
            else -> {
                confirmPasswordFieldErrorValue.value = R.string.error_msg_empty
                true
            }
        }
    }

    /**
     * here we checking both the password are same
     */
    private fun checkBothPasswordAreSame(): Boolean {

        if (!passwordField.value.isNullOrEmpty()) {
            return if (confirmPasswordField.value.equals(passwordField.value)) {
                confirmPasswordFieldErrorValue.value = R.string.error_msg_empty
                passwordFieldErrorValue.value = R.string.error_msg_empty
                true
            } else {
                confirmPasswordFieldErrorValue.value = R.string.error_msg_pw_mismatch
                false
            }
        }
        return false

    }

    /**
     * All fields validation
     */
    fun isAllFieldsValid(): Boolean {

        var isMatchPassword = false
        val isValidEmail = validateEmail()
        val isValidPassword = validatePassword()
        val isValidName = validateFirstName()
        val isValidConfirmPassword = validateConfirmPassword()
        if (isValidPassword && isValidConfirmPassword) {
            isMatchPassword = checkBothPasswordAreSame()
        }

        if (isValidName && isValidEmail && isValidPassword && isValidConfirmPassword && isMatchPassword) {
            return true
        }
        return false
    }


    fun doSignup(){
        isAllFieldsValid()
    }


}
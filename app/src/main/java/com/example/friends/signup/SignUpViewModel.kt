package com.example.friends.signup

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.friends.domain.validation.CredentialsValidationResult
import com.example.friends.domain.validation.RegexCredentialsValidator
import com.example.friends.signup.state.SignUpState

class SignUpViewModel(
    private val credentialsValidator: RegexCredentialsValidator
) {

    private val _mutableSignUpState = MutableLiveData<SignUpState>()
    val signUpState: LiveData<SignUpState> = _mutableSignUpState
    fun createAccount(
        email: String,
        password: String,
        about: String
    ) {
        when (credentialsValidator.credentialsValidationResult(email, password)) {
            is CredentialsValidationResult.InvalidEmail -> {
                _mutableSignUpState.value = SignUpState.BadEmail
            }

            is CredentialsValidationResult.InvalidPassword -> {
                _mutableSignUpState.value = SignUpState.BadPassword
            }

            CredentialsValidationResult.Valid -> {
                _mutableSignUpState.value = SignUpState.Valid
            }
        }
    }

}

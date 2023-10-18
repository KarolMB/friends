package com.example.friends.signup

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.friends.signup.state.SignUpState
import java.util.regex.Pattern

class SignUpViewModel {

    private val _mutableSignUpState = MutableLiveData<SignUpState>()
    val signUpState: LiveData<SignUpState> = _mutableSignUpState
    fun createAccount(
        email: String,
        password: String,
        about: String
    ) {
        when (credentialsValidationResult(email, password)) {
            is CredentialsValidationResult.InvalidEmail -> {
                _mutableSignUpState.value = SignUpState.BadEmail
            }
            is CredentialsValidationResult.InvalidPassword -> {
                _mutableSignUpState.value = SignUpState.BadPassword
            }
        }
    }

    private fun credentialsValidationResult(
        email: String,
        password: String
    ): CredentialsValidationResult {
        val EMAIL_REGEX =
            """[a-zA-Z0-9+._%\-]{1,64}@[a-zA-Z0-9][a-zA-Z0-9\-]{1,64}(\.[a-zA-Z0-9][a-zA-Z0-9\-]{1,25})"""
        val PASSWORD_REGEX = """^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#$%^&*+=\-]).{8,}$"""

        val emailPattern = Pattern.compile(EMAIL_REGEX)
        val passwordPattern = Pattern.compile(PASSWORD_REGEX)

        val result = if (!emailPattern.matcher(email).matches()) {
            CredentialsValidationResult.InvalidEmail
        } else if (!passwordPattern.matcher(password).matches()) {
            CredentialsValidationResult.InvalidPassword
        } else TODO()
        return result
    }

    sealed class CredentialsValidationResult {
        object InvalidEmail : CredentialsValidationResult()
        object InvalidPassword : CredentialsValidationResult()
    }

}

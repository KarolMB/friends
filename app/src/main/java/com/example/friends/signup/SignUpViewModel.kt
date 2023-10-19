package com.example.friends.signup

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.friends.domain.user.User
import com.example.friends.domain.validation.CredentialsValidationResult
import com.example.friends.domain.validation.RegexCredentialsValidator
import com.example.friends.signup.state.SignUpState

class SignUpViewModel(
    private val credentialsValidator: RegexCredentialsValidator
) {

    private val _mutableSignUpState = MutableLiveData<SignUpState>()
    val signUpState: LiveData<SignUpState> = _mutableSignUpState

    private val userForPassword = mutableMapOf<String, MutableList<User>>()
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
                val isKnown = userForPassword.values
                    .flatten()
                    .any { it.email == email }
                if (isKnown) {
                    _mutableSignUpState.value = SignUpState.DuplicateAccount
                } else {
                    val user = createUser(email, about, password)
                    _mutableSignUpState.value = SignUpState.SignedUp(user)
                }
            }
        }
    }

    private fun createUser(
        email: String,
        about: String,
        password: String
    ): User {
        val userId = email.takeWhile { it != '@' } + "Id"
        val user = User(userId, email, about)
        userForPassword.getOrPut(password, ::mutableListOf).add(user)
        return user
    }
}

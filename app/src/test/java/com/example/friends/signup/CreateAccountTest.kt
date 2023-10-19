package com.example.friends.signup

import com.example.friends.InstantTaskExecutorExtension
import com.example.friends.domain.user.User
import com.example.friends.domain.validation.RegexCredentialsValidator
import com.example.friends.signup.state.SignUpState
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(InstantTaskExecutorExtension::class)
class CreateAccountTest {

    @Test
    fun accountCreated() {
        val user = User("userId", "user@test.com", "about User")
        val viewModel = SignUpViewModel(RegexCredentialsValidator())

        viewModel.createAccount(user.email, "User123!", user.about)

        assertEquals(SignUpState.SignedUp(user), viewModel.signUpState.value)
    }

    @Test
    fun anotherAccountCreated() {
        val userBob = User("bobId", "bob@test.com", "about Bob")
        val viewModel = SignUpViewModel(RegexCredentialsValidator())

        viewModel.createAccount(userBob.email, "Bobbob12!", userBob.about)

        assertEquals(SignUpState.SignedUp(userBob), viewModel.signUpState.value)
    }

    @Test
    fun createDuplicateAccount() {
        val userDuplicated = User("userDuplicatedId", "userDuplicated@test.com", "about userDuplicated")
        val viewModel = SignUpViewModel(RegexCredentialsValidator()).also {
            it.createAccount(userDuplicated.email, "userDuplicated12!", userDuplicated.about)
        }

        viewModel.createAccount(userDuplicated.email, "userDuplicated12!", userDuplicated.about)

        assertEquals(SignUpState.DuplicateAccount, viewModel.signUpState.value)
    }
}
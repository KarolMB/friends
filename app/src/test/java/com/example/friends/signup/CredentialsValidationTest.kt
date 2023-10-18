package com.example.friends.signup

import com.example.friends.InstantTaskExecutorExtension
import com.example.friends.signup.state.SignUpState
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

@ExtendWith(InstantTaskExecutorExtension::class)
class CredentialsValidationTest {

    @ParameterizedTest
    @CsvSource(
        "'email",
        "'a@b.c'",
        "'ab@b.c'",
        "'ab@bc.c'",
        "''",
    )
    fun invalidEmail(email: String) {
        val viewModel = SignUpViewModel()

        viewModel.createAccount(email, ":password:", ":about:")

        assertEquals(SignUpState.BadEmail, viewModel.signUpState.value)
    }

    @Test
    fun invalidPassword() {
        val viewModel = SignUpViewModel()

        viewModel.createAccount("test@test.com", "", ":about:")

        assertEquals(SignUpState.BadPassword, viewModel.signUpState.value)
    }
}
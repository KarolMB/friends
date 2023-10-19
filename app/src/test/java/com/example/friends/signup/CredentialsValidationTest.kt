package com.example.friends.signup

import com.example.friends.InstantTaskExecutorExtension
import com.example.friends.domain.validation.RegexCredentialsValidator
import com.example.friends.signup.state.SignUpState
import org.junit.jupiter.api.Assertions.*
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
        val viewModel = SignUpViewModel(RegexCredentialsValidator())

        viewModel.createAccount(email, ":password:", ":about:")

        assertEquals(SignUpState.BadEmail, viewModel.signUpState.value)
    }

    @ParameterizedTest
    @CsvSource(
        "''",
        "'      '",
        "'12'",
        "'1234789'",
        "'abdc4567'",
        "'abdced4567!$'",
        "'ABDCDEF4567!$'",
    )
    fun invalidPassword(password: String) {
        val viewModel = SignUpViewModel(RegexCredentialsValidator())

        viewModel.createAccount("test@test.com", password, ":about:")

        assertEquals(SignUpState.BadPassword, viewModel.signUpState.value)
    }
}
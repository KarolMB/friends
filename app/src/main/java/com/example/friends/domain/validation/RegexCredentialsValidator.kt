package com.example.friends.domain.validation

import java.util.regex.Pattern

class RegexCredentialsValidator {
    fun credentialsValidationResult(
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
        } else {
            CredentialsValidationResult.Valid
        }
        return result
    }
}
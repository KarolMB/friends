package com.example.friends.signup.state

sealed class SignUpState {
    object BadEmail : SignUpState()
    object BadPassword : SignUpState()
    object Valid : SignUpState()
}

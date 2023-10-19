package com.example.friends.signup.state

import com.example.friends.domain.user.User

sealed class SignUpState {
    object BadEmail : SignUpState()
    object BadPassword : SignUpState()
    object Valid : SignUpState()
    data class SignedUp(val user: User) : SignUpState()

    object DuplicateAccount: SignUpState()

}

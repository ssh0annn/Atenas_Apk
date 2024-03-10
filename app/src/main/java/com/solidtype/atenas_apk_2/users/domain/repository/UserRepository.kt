package com.solidtype.atenas_apk_2.users.domain.repository


import com.google.firebase.auth.FirebaseUser
import com.solidtype.atenas_apk_2.users.domain.model.UserModel

interface  UserRepository {
    fun signUp(user: UserModel): Boolean
    fun SignIn(user: UserModel): Boolean
    fun signout()
    fun getCurrentUser(): FirebaseUser?

}

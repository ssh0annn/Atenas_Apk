package com.solidtype.atenas_apk_2.users.domain.repository

interface  UserRepository {
    fun signUp(user: Any?)
    fun capturarDatos(user: Any?)
    fun SignIn(user: Any?): Boolean
    fun signout(): Boolean
    fun getCurrentUser(): String
}

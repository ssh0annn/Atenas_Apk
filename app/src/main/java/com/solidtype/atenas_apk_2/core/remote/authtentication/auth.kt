package com.solidtype.atenas_apk_2.core.remote.authtentication

import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

interface auth {

    suspend fun signup(email: String, clave: String): Boolean

    suspend fun signinCorru(email: String, clave: String) : Boolean

    fun getCurrentUser() :FirebaseUser?

    fun signOut()
}
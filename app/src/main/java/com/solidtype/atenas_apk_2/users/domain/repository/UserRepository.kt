package com.solidtype.atenas_apk_2.users.domain.repository


import com.google.firebase.auth.FirebaseUser
import com.solidtype.atenas_apk_2.users.domain.model.UserModel
import com.solidtype.atenas_apk_2.users.domain.userCase.ValidateResults

interface  UserRepository {
    suspend fun signUp(email:String, clave:String,name:String, sim:String,
                apellido:String, nnegocio:String,
                dnegocio:String, telefono:String): Boolean
    suspend fun SignIn(email:String, clave: String): Boolean
    suspend fun signout()
    suspend fun getCurrentUser(): FirebaseUser?

}

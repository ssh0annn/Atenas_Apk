package com.solidtype.atenas_apk_2.users.domain.repository


import com.google.firebase.auth.FirebaseUser

interface  UserRepository {
    suspend fun signUp(email:String, clave:String,name:String, sim:String,
                apellido:String, nnegocio:String,
                dnegocio:String, telefono:String): Boolean
    suspend fun SignIn(email:String, clave: String): Boolean
    suspend fun signout()
    suspend fun getCurrentUser(): FirebaseUser?
    suspend fun validaICCID(iccid:String): Boolean
    suspend fun capturaICCID(): String
    suspend fun estadoDeLicencia(iccid:String):Boolean
    suspend fun existeUsuario() : Boolean

    suspend fun estadoLicencia(): Boolean

}

package com.solidtype.atenas_apk_2.core.remote.authtentication

import com.solidtype.atenas_apk_2.authentication.actualizacion.data.modelo.CheckListAuth
import com.solidtype.atenas_apk_2.authentication.actualizacion.domain.model.Usuario

interface MetodoAutenticacion {
    suspend fun signing(email:String, pass:String, sistemID:String): CheckListAuth
    suspend fun signout()
    suspend fun registerNewUsers(email:String, pass:String)
    suspend fun getUsuarioActual(): Usuario

}
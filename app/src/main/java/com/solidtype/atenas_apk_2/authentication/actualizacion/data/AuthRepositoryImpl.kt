package com.solidtype.atenas_apk_2.authentication.actualizacion.data

import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.solidtype.atenas_apk_2.authentication.actualizacion.data.modelo.CheckListAuth
import com.solidtype.atenas_apk_2.authentication.actualizacion.domain.AuthRepository
import com.solidtype.atenas_apk_2.authentication.actualizacion.domain.TipoUser
import com.solidtype.atenas_apk_2.authentication.actualizacion.domain.model.Usuario
import com.solidtype.atenas_apk_2.core.remote.authtentication.MetodoAutenticacion
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(private val autenticacion: MetodoAutenticacion) : AuthRepository {
    override suspend fun signing(user: String, password: String, systemID: String, licencia:String): CheckListAuth {
        try {
            val caminoFeliz =  autenticacion.signing(user, password,systemID , licencia)
            println("Este es el systemID: $systemID")
            if(caminoFeliz.tipoUser != TipoUser.UNKNOWN && caminoFeliz.licensiaActiva && caminoFeliz.deviceRegistrado){
                UsuarioActual.emailUsuario = caminoFeliz.emailUsuario ?: ""
                UsuarioActual.tipoUser = caminoFeliz.tipoUser
            }else{
                UsuarioActual.emailUsuario = ""
                UsuarioActual.tipoUser = TipoUser.UNKNOWN
            }

            return caminoFeliz

        }catch (e: FirebaseAuthInvalidCredentialsException){
            println("Usuario desconocido o contrasenia invalida")
            return CheckListAuth()
        }
    }

    override suspend fun signout() {
        println("Sinout exitoso!! ")

        UsuarioActual.emailUsuario = ""
        UsuarioActual.tipoUser = TipoUser.UNKNOWN
        autenticacion.signout()
    }

    override suspend fun isAutenticated(): Usuario {

        return Usuario(
            correo = UsuarioActual.emailUsuario,
            tipoUser = UsuarioActual.tipoUser
        )
    }
    override suspend fun cambiarPassword(email: String, oldPassword: String, newPassword: String, callback:(
            Boolean, String?
            ) -> Unit) {
        autenticacion.cambiarPassword(email, oldPassword, newPassword) { success, reason ->
            if (success) {

                callback(success, null)
                println("Todo va bien por el Auth Repo: $success")

            } else {
                println("Funcion CabiarPassword Repositiro: $reason")
                callback(success, reason)
            }
        }

    }

    override suspend fun olvideMiPassword(email: String, respuesta:(Boolean, Boolean, String?)-> Unit): Boolean {
        return autenticacion.olvideMiPassword(email, respuesta)
    }

    override suspend fun nuevoDevice(id: String, licencia: String) {
           autenticacion.registrarNewDispositivo(id, licencia)
    }
}
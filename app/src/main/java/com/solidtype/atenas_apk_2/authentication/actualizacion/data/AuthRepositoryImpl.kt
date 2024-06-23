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
            if(caminoFeliz.tipoUser != TipoUser.UNKNOWN && caminoFeliz.licensiaActiva == true && caminoFeliz.deviceRegistrado ==true){
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
}
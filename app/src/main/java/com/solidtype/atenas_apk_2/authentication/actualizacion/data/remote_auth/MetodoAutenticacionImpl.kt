package com.solidtype.atenas_apk_2.authentication.actualizacion.data.remote_auth

import com.google.firebase.auth.FirebaseAuth
import com.solidtype.atenas_apk_2.authentication.actualizacion.data.UsuarioActual
import com.solidtype.atenas_apk_2.authentication.actualizacion.data.modelo.CheckListAuth
import com.solidtype.atenas_apk_2.authentication.actualizacion.domain.TipoUser
import com.solidtype.atenas_apk_2.authentication.actualizacion.domain.model.Usuario
import com.solidtype.atenas_apk_2.core.remote.authtentication.MetodoAutenticacion
import com.solidtype.atenas_apk_2.core.remote.dataCloud.DataCloud
import javax.inject.Inject

class MetodoAutenticacionImpl @Inject constructor(
    private val firebaseAuth: FirebaseAuth,
    private val dataCloud: DataCloud
):MetodoAutenticacion {
    override suspend fun signing(email: String, pass: String, sistemID: String): CheckListAuth {
        val tipoUsuario= when(email){
            "adderlis@yahoo.com" -> {
                TipoUser.ADMIN
            }
            "vendedor@solidtype.com" -> {
                TipoUser.VENDEDOR
            }
            else -> {
                TipoUser.UNKNOWN
            }
        }
        return CheckListAuth(
            autenticado = true,
            deviceRegistrado = true,
            licensiaActiva = true,
            tipoUser = tipoUsuario,
            emailUsuario = email
        )
    }

    override suspend fun signout() {
        UsuarioActual.emailUsuario = ""
        UsuarioActual.tipoUser = TipoUser.UNKNOWN
    }

    override suspend fun registerNewUsers(email: String, pass: String) {
       //ALgun metodo de registro
    }

    override suspend fun getUsuarioActual(): Usuario {
        return Usuario(
            UsuarioActual.emailUsuario,
            UsuarioActual.tipoUser
        )
    }
}
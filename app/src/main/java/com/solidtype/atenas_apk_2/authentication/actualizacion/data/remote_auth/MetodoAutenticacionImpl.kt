package com.solidtype.atenas_apk_2.authentication.actualizacion.data.remote_auth

import com.google.firebase.auth.FirebaseAuth
import com.solidtype.atenas_apk_2.authentication.actualizacion.data.UsuarioActual
import com.solidtype.atenas_apk_2.authentication.actualizacion.data.modelo.CheckListAuth
import com.solidtype.atenas_apk_2.authentication.actualizacion.domain.TipoUser
import com.solidtype.atenas_apk_2.authentication.actualizacion.domain.model.Usuario
import com.solidtype.atenas_apk_2.core.remote.authtentication.MetodoAutenticacion
import com.solidtype.atenas_apk_2.core.remote.dataCloud.DataCloud
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext
import javax.inject.Inject
class MetodoAutenticacionImpl @Inject constructor(
    private val firebaseAuth: FirebaseAuth,
    private val dataCloud: DataCloud
):MetodoAutenticacion {
    override suspend fun signing(email: String, pass: String, sistemID: String): CheckListAuth {
        val check = CheckListAuth()
        val tipoUsuario= withContext(Dispatchers.IO){
            val user =firebaseAuth.signInWithEmailAndPassword(email, pass).await()
            user.user?.email?.let {
                check.autenticado = true
                check.deviceRegistrado = dataCloud.validarDispositivo(sistemID)
                check.licensiaActiva = true
                check.emailUsuario = it
                check.tipoUser =dataCloud.autenticacionCloud(it, "papichulo",email)
            }
        }
        return check
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
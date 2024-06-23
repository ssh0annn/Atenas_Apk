package com.solidtype.atenas_apk_2.authentication.actualizacion.data.remote_auth

import com.google.firebase.FirebaseNetworkException
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
    override suspend fun signing(email: String, pass: String, sistemID: String, licencia:String): CheckListAuth {
      return  withContext(Dispatchers.IO){
            var check = CheckListAuth()
            try {
                val user =firebaseAuth.signInWithEmailAndPassword(email, pass).await()
                user.user?.email?.let {
                    check=  dataCloud.autenticacionCloud(it, licencia, sistemID)
                }
                return@withContext check
            }catch (e: Exception){
                return@withContext check

            }
        }

    }

    override suspend fun signout() {
        UsuarioActual.emailUsuario = ""
        UsuarioActual.tipoUser = TipoUser.UNKNOWN
        firebaseAuth.signOut()
    }

    override suspend fun registerNewUsers(email: String, pass: String) {
        try {
            firebaseAuth.createUserWithEmailAndPassword(email, pass)
        }catch (e: Exception){
            println("error de registro : $e")
        }

    }
    override suspend fun getUsuarioActual(): Usuario {
        return Usuario(
            UsuarioActual.emailUsuario,
            UsuarioActual.tipoUser
        )
    }
}
package com.solidtype.atenas_apk_2.users.data.repository

import android.widget.Toast
import com.google.firebase.auth.FirebaseUser
import com.solidtype.atenas_apk_2.users.data.remote.FireStore
import com.solidtype.atenas_apk_2.users.data.remote.FirestoreConnect
import com.solidtype.atenas_apk_2.users.data.remote.Modelo
import com.solidtype.atenas_apk_2.users.data.remote.RemoteFirebase
import com.solidtype.atenas_apk_2.users.domain.model.UserModel
import com.solidtype.atenas_apk_2.users.domain.repository.UserRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class RepositoryImpl (private val auth : RemoteFirebase =RemoteFirebase(),
                      private val store: FireStore =FireStore()): UserRepository {


    override suspend fun signUp(
        email: String, clave: String, name: String, sim: String,
        apellido: String, nnegocio: String,
        dnegocio: String, telefono: String
    ): Boolean {
        var funciona = false
        val mod = Modelo(
            name, apellido, sim, email, clave,
            nnegocio, dnegocio,
            telefono
        )
        actualizarOInsertarModelo(mod)
        if (!auth.signup(email, clave)) {//Esta consulta debuelve falso si = sale bien.

            funciona = true
        }

        return funciona
    }


    override suspend fun SignIn(user: UserModel): Boolean {
        var confirmacion = false
        var mensaje: String? = ""

        auth.signin(user.correo, user.clave)
        { success, errormesages ->
            confirmacion = success
            mensaje = errormesages
        }
        if (mensaje != null) {
            throw Exception("Error de registro $mensaje")
        }
        return confirmacion
    }

    override suspend fun signout() {
        auth.signOut()
    }

    override suspend fun getCurrentUser(): FirebaseUser? {
        return auth.getCurrentUser()
    }

    suspend fun actualizarOInsertarModelo(modelo: Modelo) {
        // Utiliza una coroutine para manejar la operación de manera asíncrona
        CoroutineScope(Dispatchers.IO).launch {
            val resultado = store.actualizarDocumento(modelo)

            withContext(Dispatchers.Main) {

                if (resultado) {

                    println("Exito desde la corrutina")
                } else {

                    println("La operación falló")
                }
            }
        }
    }
}
package com.solidtype.atenas_apk_2.users.data.repository

import com.google.firebase.auth.FirebaseUser
import com.solidtype.atenas_apk_2.users.data.remote.Modelo
import com.solidtype.atenas_apk_2.users.data.remote.RemoteFirebase
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

        if (auth.signup(email, clave)) {
            actualizarOInsertarModelo(mod)
        val mod=Modelo(name,apellido,sim, email,clave,
            nnegocio,dnegocio,
            telefono)


            funciona = true
        }

        return funciona
    }


    override suspend fun SignIn(email: String, clave: String) = auth.signinCorru(email, clave) // -> Boolean


    override suspend fun signout() =  auth.signOut() // -> Unit


    override suspend fun getCurrentUser(): FirebaseUser? {
        return auth.getCurrentUser()
    }


    private suspend fun actualizarOInsertarModelo(modelo: Modelo) {
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


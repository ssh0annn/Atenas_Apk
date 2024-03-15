package com.solidtype.atenas_apk_2.users.data.repository

import com.google.firebase.auth.FirebaseUser
import com.solidtype.atenas_apk_2.users.data.remote.FirestoreConnect
import com.solidtype.atenas_apk_2.users.data.remote.Modelo
import com.solidtype.atenas_apk_2.users.data.remote.RemoteFirebase
import com.solidtype.atenas_apk_2.users.domain.repository.UserRepository
import com.solidtype.atenas_apk_2.users.domain.userCase.ValidateResults
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class RepositoryImpl (private val auth : RemoteFirebase =RemoteFirebase(),
                      private val store: FirestoreConnect =FirestoreConnect()): UserRepository {


    override suspend fun signUp(
                email: String, clave: String, name: String, sim: String,
                apellido: String, nnegocio: String,
                dnegocio: String, telefono: String
            ): Boolean {

                val mod = Modelo(
                    name, apellido, email, sim, clave,
                    nnegocio, dnegocio,
                    telefono
                )
                var estado = false

                if (auth.signup(email, clave)) {
                    val resultado = store.newUser2(mod)
                    if (resultado) {
                        estado=true

                        println("se guardaron datos exitosos")
                    } else {

                        println("No se guardaron datos seggun signup")
                    }
                }
                return estado
    }


    override suspend fun SignIn(email: String, clave: String) = auth.signinCorru(email, clave) // -> Boolean


    override suspend fun signout() =  auth.signOut() // -> Unit


    override suspend fun getCurrentUser(): FirebaseUser? {
        return auth.getCurrentUser()
    }


    private suspend fun actualizarOInsertarModelo(modelo: Modelo) {
        // Utiliza una coroutine para manejar la operación de manera asíncrona

            }
        }



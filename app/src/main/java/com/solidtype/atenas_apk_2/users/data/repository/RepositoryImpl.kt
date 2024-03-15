package com.solidtype.atenas_apk_2.users.data.repository

import com.google.firebase.auth.FirebaseUser
import com.solidtype.atenas_apk_2.users.data.remote.FirestoreConnect
import com.solidtype.atenas_apk_2.users.data.remote.Modelo
import com.solidtype.atenas_apk_2.users.data.remote.RemoteFirebase
import com.solidtype.atenas_apk_2.users.domain.repository.UserRepository

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

                if (auth.signinCorru(email, clave) ) {//Debe ser un usuario existente en firebase.
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


    override  fun signout() =  auth.signOut() // -> Unit


    override suspend fun getCurrentUser(): FirebaseUser? {
        return auth.getCurrentUser()
    }

    override suspend fun validaICCID(iccid: String): Boolean {
       return store.validateIccid(iccid)
    }

    override suspend fun capturaICCID(): String {
        return getCurrentUser()!!.uid
    }


    override suspend fun estadoDeLicencia(iccid:String): Boolean {// a la espera de implementacon
      return true
        }///solo para probar


}



package com.solidtype.atenas_apk_2.Authentication.data.repository

import com.google.firebase.auth.FirebaseUser

import com.solidtype.atenas_apk_2.Authentication.data.remote.FirestoreConnect

import com.solidtype.atenas_apk_2.Authentication.data.remote.Modelo
import com.solidtype.atenas_apk_2.Authentication.data.remote.RemoteFirebase
import com.solidtype.atenas_apk_2.Authentication.domain.repository.UserRepository
import com.solidtype.atenas_apk_2.core.remote.authtentication.auth
import javax.inject.Inject


class RepositoryImpl @Inject constructor(private val auth : auth,
                                         private val store: FirestoreConnect): UserRepository {




    override suspend fun signUp(
                email: String, clave: String, name: String, sim: String,
                apellido: String, nnegocio: String,
                dnegocio: String, telefono: String
            ): Boolean {


                var estado = false

                if (auth.signinCorru(email, clave) ) {//Debe ser un usuario existente en firebase.
                    val licencia= auth.getCurrentUser()!!.uid
                    val mod = Modelo(
                        name, apellido, email, licencia, clave,
                        nnegocio, dnegocio,
                        telefono
                    )
                    val resultado = store.newUser2(mod)
                    if (resultado) {
                        estado=true

                        println("se guardaron datos exitosos")
                    } else {
                        auth.signOut()// si pasa un error en la base de datos, deslogueo al usuario

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

    override suspend fun validaICCID(iccid: String): Boolean {
       return store.validateIccid(iccid)
    }

    override suspend fun capturaICCID(): String {
        return getCurrentUser()!!.uid
    }


    override suspend fun estadoDeLicencia(iccid:String): Boolean {// a la espera de implementacon
      return !store.fechaExpirada(capturaICCID())
        }///solo para probar

    override suspend fun estadoLicencia(): Boolean {
        return !store.fechaExpirada(capturaICCID())


    }
    override suspend fun existeUsuario() : Boolean {
       return store.usuarioExiste(capturaICCID())
    }
}




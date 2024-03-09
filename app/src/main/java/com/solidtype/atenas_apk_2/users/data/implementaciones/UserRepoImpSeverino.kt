package com.solidtype.atenas_apk_2.users.data.implementaciones

import android.util.Log
import com.google.firebase.auth.FirebaseUser
import com.solidtype.atenas_apk_2.users.data.remote.remoteFirebase
import com.solidtype.atenas_apk_2.users.domain.model.UserModel
import com.solidtype.atenas_apk_2.users.domain.repository.UserRepository

class UserRepoImpSeverino :UserRepository {
    private val  autenticacion = remoteFirebase()
    override fun signUp(user: UserModel): Boolean {
        autenticacion.signOut()
        return true
    }

    override fun SignIn(user: UserModel): Boolean {
        var correo = user.correo ?: ""
        var clave = user.clave ?: ""
        var estado =false
        autenticacion.signin(correo, clave){ success, error ->
            estado = success
            error ?: Log.d("signin Error","Error de callback Sigin : $error")

        }
        return estado
    }

    override fun signout() {
        autenticacion.signOut()
    }

    override fun getCurrentUser(): FirebaseUser? {
        return autenticacion.getCurrentUser()
    }
}
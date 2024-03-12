package com.solidtype.atenas_apk_2.users.data.implementaciones

import android.util.Log
import com.google.firebase.auth.FirebaseUser
import com.solidtype.atenas_apk_2.users.data.remote.RemoteFirebase
import com.solidtype.atenas_apk_2.users.domain.model.UserModel

class UserRepoImpSeverino {
    private val  autenticacion = RemoteFirebase()
   suspend fun signUp(user: UserModel): Boolean {
        autenticacion.signOut()
        return true
    }

   suspend fun SignIn(user: UserModel): Boolean {
        var correo = user.correo ?: ""
        var clave = user.clave ?: ""
        var estado =false
        autenticacion.signin(correo, clave){ success, error ->
            estado = success
            error ?: Log.d("signin Error","Error de callback Sigin : $error")

        }
        return estado
    }

 suspend fun signout() {
        autenticacion.signOut()
    }

   suspend fun getCurrentUser(): FirebaseUser? {
        return autenticacion.getCurrentUser()
    }
}
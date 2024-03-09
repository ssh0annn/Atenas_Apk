package com.solidtype.atenas_apk_2.users.data.repository

import com.google.firebase.auth.FirebaseUser
import com.solidtype.atenas_apk_2.users.data.remote.remoteFirebase
import com.solidtype.atenas_apk_2.users.domain.model.UserModel
import com.solidtype.atenas_apk_2.users.domain.repository.UserRepository

class RepositoryImpl : UserRepository {
    val auth =remoteFirebase()
    override fun signUp(user: UserModel) : Boolean{
        var confirmacion=false
        var correo : String = user?.correo ?: ""
        var clave : String = user?.clave ?: ""
        auth.signup(correo, clave)
        {success, errormesages ->
            confirmacion=success
        }
        return confirmacion
    }



    override fun SignIn(user: UserModel): Boolean{
        var confirmacion=false
        var mensaje:String?=""
        var correo : String = user?.correo ?: ""
        var clave : String = user?.clave ?: ""
        auth.signin(correo, clave)
        {success, errormesages ->
            confirmacion=success
            mensaje=errormesages}
        if(mensaje != null){
            throw Exception("Error de registro $mensaje")
        }
        return confirmacion
    }

    override fun signout(){
        auth.signOut()
    }

    override fun getCurrentUser(): FirebaseUser? {
        return auth.getCurrentUser()
    }
}
package com.solidtype.atenas_apk_2.users.data.repository

import com.google.firebase.auth.FirebaseUser
import com.solidtype.atenas_apk_2.users.data.remote.RemoteFirebase
import com.solidtype.atenas_apk_2.users.domain.model.UserModel
import com.solidtype.atenas_apk_2.users.domain.repository.UserRepository

class RepositoryImpl : UserRepository {
    private val auth =RemoteFirebase()
    override  fun signUp(email:String, clave:String) : Boolean{

        return auth.signup(email, clave)
    }



    override fun SignIn(user: UserModel): Boolean{
        var confirmacion=false
        var mensaje:String?=""

        auth.signin(user.correo, user.clave)
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
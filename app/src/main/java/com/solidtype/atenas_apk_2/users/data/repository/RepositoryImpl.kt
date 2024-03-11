package com.solidtype.atenas_apk_2.users.data.repository

import com.google.firebase.auth.FirebaseUser
import com.solidtype.atenas_apk_2.users.data.remote.FirestoreConnect
import com.solidtype.atenas_apk_2.users.data.remote.Modelo
import com.solidtype.atenas_apk_2.users.data.remote.RemoteFirebase
import com.solidtype.atenas_apk_2.users.domain.model.UserModel
import com.solidtype.atenas_apk_2.users.domain.repository.UserRepository

class RepositoryImpl (private val auth : RemoteFirebase =RemoteFirebase(),
                      private val store: FirestoreConnect =FirestoreConnect()): UserRepository {


    override  fun signUp(email:String, clave:String,name:String, sim:String,
                         apellido:String, nnegocio:String,
                         dnegocio:String, telefono:String) : Boolean{
        var funciona=false
        val mod=Modelo(name,apellido,email,sim,clave,
            nnegocio,dnegocio,
            telefono)

        if(!auth.signup(email, clave)){
            store.newUser(mod)
            funciona=true
        }

        return funciona
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
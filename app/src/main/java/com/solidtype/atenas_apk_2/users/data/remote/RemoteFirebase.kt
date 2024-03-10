package com.solidtype.atenas_apk_2.users.data.remote

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

class RemoteFirebase{
    private val auth: FirebaseAuth = FirebaseAuth.getInstance()

    fun signup(email: String, clave: String):Boolean{

        return auth.createUserWithEmailAndPassword(email, clave).isSuccessful
    }

    fun signin(email: String, clave: String, callback: (Boolean, String?) -> Unit): FirebaseUser? {
        auth.signInWithEmailAndPassword(email, clave)
        .addOnCompleteListener { task ->
            if (task.isSuccessful) {
                callback(true, null)
            } else {
                callback(false, task.exception?.message)
            }
        }
        return getCurrentUser()
    }

    fun getCurrentUser(): FirebaseUser? {
        return auth.currentUser
    }

    fun signOut() {
        auth.signOut()
    }

}
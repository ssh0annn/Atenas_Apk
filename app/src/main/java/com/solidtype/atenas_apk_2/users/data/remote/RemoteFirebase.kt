package com.solidtype.atenas_apk_2.users.data.remote

import com.google.android.gms.tasks.Tasks
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthException
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.firestore
import com.google.firebase.firestore.firestoreSettings

class RemoteFirebase{
    private val auth: FirebaseAuth = FirebaseAuth.getInstance()
   suspend fun signup(email: String, clave: String):Boolean{
        var signup:Boolean=false

        val task =auth.createUserWithEmailAndPassword(email, clave).addOnCompleteListener {
            task->
            if (task.isSuccessful)
                signup=true
            else
                println("el verdadero resultado del task: ${task.result}")
        }
        val result = Tasks.await(task)

        println("Lo que pasa en signup: $signup, y el result: $result")
        return signup
    }

    suspend fun signin(email: String, clave: String, callback: (Boolean, String?) -> Unit) {
        try {
            auth.signInWithEmailAndPassword(email, clave)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        callback(true, "")
                        println("Repuesta del callback Successful: ${task.isSuccessful}")
                    } else {
                        println("Repuesta del callback error: ${task.exception}, <--, ${task.isSuccessful}, <--")
                        callback(task.isSuccessful, task.exception?.message)
                    }
                }
            println("Aqui debio salir todo bien, alemenos (RemoteFirebase)")

        }catch (e:Exception){
           println("EN RemoteFirebae Execption: $e, <--------")

        }

    }

    suspend fun getCurrentUser(): FirebaseUser? {
                return auth.currentUser
    }

    suspend fun signOut() {
        auth.signOut()
    }

}
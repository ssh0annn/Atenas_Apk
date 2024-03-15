package com.solidtype.atenas_apk_2.users.data.remote

import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext

class RemoteFirebase(private val auth: FirebaseAuth = FirebaseAuth.getInstance()){
   suspend fun signup(email: String, clave: String) = withContext(Dispatchers.IO) {
       try {
           val result = auth.createUserWithEmailAndPassword(email, clave).await()
           println("Resultado de withContext ${result} <---")
           auth.uid
           return@withContext true

       } catch (e: Exception) {
           println("La exception de signinCorru:$e, <---")
           return@withContext false
       }

   }  // -> Boolean


    suspend fun signinCorru(email: String, clave: String) = withContext(Dispatchers.IO){
                try{
                    val result = auth.signInWithEmailAndPassword(email, clave).await()
                    println("Resultado de withContext $result <---")
                    return@withContext  true

                }catch (e: Exception){
                    println("La exception de signinCorru:$e, <---")
                    return@withContext false
                }

            } // -> Boolean
    fun getCurrentUser()= auth.currentUser // -> FirebaseUser?
    fun signOut() = auth.signOut()  // -> Unit



}
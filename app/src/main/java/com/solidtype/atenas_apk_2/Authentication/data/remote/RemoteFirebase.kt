package com.solidtype.atenas_apk_2.Authentication.data.remote

import com.google.firebase.auth.FirebaseAuth
import com.solidtype.atenas_apk_2.core.remote.authtentication.auth
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext
import javax.inject.Inject

class RemoteFirebase @Inject constructor(
    private val auth: FirebaseAuth
): auth{

   override suspend fun signup(email: String, clave: String) = withContext(Dispatchers.IO) {
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


    override suspend fun signinCorru(email: String, clave: String) = withContext(Dispatchers.IO){
                try{
                    val result = auth.signInWithEmailAndPassword(email, clave).await()
                    println("Resultado de withContext $result <---")
                    return@withContext  true

                }catch (e: Exception){
                    println("La exception de signinCorru:$e, <---")
                    return@withContext false
                }

            } // -> Boolean
    override fun getCurrentUser()= auth.currentUser // -> FirebaseUser?
    override fun signOut() = auth.signOut()  // -> Unit



}
package com.solidtype.atenas_apk_2.authentication.actualizacion.data.remote_auth

import com.google.firebase.auth.AuthCredential
import com.google.firebase.auth.EmailAuthProvider
import com.google.firebase.auth.FirebaseAuth
import com.solidtype.atenas_apk_2.authentication.actualizacion.data.UsuarioActual
import com.solidtype.atenas_apk_2.authentication.actualizacion.data.modelo.CheckListAuth
import com.solidtype.atenas_apk_2.authentication.actualizacion.domain.TipoUser
import com.solidtype.atenas_apk_2.authentication.actualizacion.domain.model.Usuario
import com.solidtype.atenas_apk_2.core.remote.authtentication.MetodoAutenticacion
import com.solidtype.atenas_apk_2.core.remote.dataCloud.DataCloud
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext
import javax.inject.Inject

class MetodoAutenticacionImpl @Inject constructor(
    private val firebaseAuth: FirebaseAuth,
    private val dataCloud: DataCloud
) : MetodoAutenticacion {
    override suspend fun signing(
        email: String,
        pass: String,
        sistemID: String,
        licencia: String
    ): CheckListAuth {
        return withContext(Dispatchers.IO) {
            var check = CheckListAuth()
            try {
                val user = firebaseAuth.signInWithEmailAndPassword(email, pass).await()
                user.user?.email?.let {
                    check = dataCloud.autenticacionCloud(it, licencia, sistemID)
                }
                return@withContext check
            } catch (e: Exception) {
                return@withContext check

            }
        }

    }

    override suspend fun signout() {
        UsuarioActual.emailUsuario = ""
        UsuarioActual.tipoUser = TipoUser.UNKNOWN
        firebaseAuth.signOut()

    }

    override suspend fun eliminarUsuario(email: String, password: String) {
        reautenticarYEliminarUsuario(email, password)
    }

    override suspend fun cambiarPassword(
        email: String,
        oldPassword: String,
        newPassworld: String,
        callback: (success: Boolean, reason: String?) -> Unit
    ) {
        try {

            val credential = reutenticar(email, oldPassword)
            if (credential != null) {
                val user = firebaseAuth.currentUser
                user?.let {
                        user.updatePassword(newPassworld).addOnCompleteListener { updateTask ->
                            if (updateTask.isSuccessful) {
                                println("Contrasenia comabiada con exito!! ")
                                callback(true, "Contraseña cambiada exitosamente!!") // Contraseña cambiada exitosamente

                            } else {
                                println("Hay booboo!! ")
                                callback(false,
                                    updateTask.exception?.message  ?: "Error al cambiar la contraseña"
                                )
                            }
                        }
                        return  // Salir después de llamar al callback
                    }
                }

            callback(false, "Correo o contraseña incorrectos")
        } catch (e: Exception) {
            println("Error al cambiar la contraseña: $e")
            callback(false, "Error: ${e.message}")
        }
    }

    override suspend fun olvideMiPassword(email: String): Boolean {
           var success = false
           firebaseAuth.sendPasswordResetEmail(email).addOnCompleteListener{
               success = it.isSuccessful
           }
        return success
    }


    private fun reutenticar(email: String, password: String): AuthCredential? {
        val credntial: AuthCredential? = try {
            EmailAuthProvider.getCredential(email, password)

        } catch (e: Exception) {
            null

        }
        return credntial
    }

    private  fun reautenticarYEliminarUsuario(email: String, password: String) {
        try {
            val user = firebaseAuth.currentUser
            user?.let {
                val credential = EmailAuthProvider.getCredential(email, password)
                it.reauthenticate(credential)
                    .addOnCompleteListener { reauthTask ->
                        if (reauthTask.isSuccessful) {
                            it.delete()
                                .addOnCompleteListener { deleteTask ->
                                    if (deleteTask.isSuccessful) {
                                        println("Usuario eliminado exitosamente.")
                                    } else {
                                        println("Error al eliminar usuario: ${deleteTask.exception?.message}")
                                    }
                                }
                        } else {
                            println("Error al reautenticar usuario: ${reauthTask.exception?.message}")
                        }
                    }
            } ?: run {
                println("No hay un usuario autenticado.")
            }
        } catch (e: Exception) {
            println("Error para eliminar usuario")
        }

    }

    override suspend fun registerNewUsers(email: String, pass: String) {
        try {
            firebaseAuth.createUserWithEmailAndPassword(email, pass)
        } catch (e: Exception) {
            println("error de registro : $e")
        }
    }

    override suspend fun getUsuarioActual(): Usuario {
        return Usuario(
            UsuarioActual.emailUsuario,
            UsuarioActual.tipoUser
        )
    }
}
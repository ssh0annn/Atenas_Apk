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
    /**
     * @param: email: String,
     *         pass: String,
     *         sistemID: String,
     *         licencia: String
     * @return: CheckListAuth
     * Espera a que un usuario sea autenticado para recibir el email y verificar en dataCloud las
     * condiciones del objeto CheckListAuth()
     */
    override suspend fun signing(
        email: String,
        pass: String,
        sistemID: String,
        licencia: String
    ): CheckListAuth {
        return try {
            var check = CheckListAuth()
            val user = firebaseAuth.signInWithEmailAndPassword(email, pass).await()
            user.user?.email?.let {
                check = dataCloud.autenticacionCloud(it, licencia, sistemID)
            }
            check
        } catch (e: Exception) {
           println("Excption $e")
            CheckListAuth()
        }
    }




    override suspend fun signout() {
        UsuarioActual.emailUsuario = ""
        UsuarioActual.tipoUser = TipoUser.UNKNOWN
        firebaseAuth.signOut()

    }

    override suspend fun eliminarUsuario(email: String, password: String) {
       // reautenticarYEliminarUsuario(email, password)
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
                        when (updateTask.isSuccessful) {
                            true -> callback(
                                true,
                                "Contraseña cambiada exitosamente!!"
                            )
                            else ->  callback(
                                    false,
                                    updateTask.exception?.message
                                        ?: "Error al cambiar la contraseña")

                        }
                    }
                    return  // Salir después de llamar al callback
                }
            }
            callback(false, "Correo o contraseña incorrectos")
        } catch (e: Exception) {
            callback(false, "Error: ${e.message}")
        }
    }

    override suspend fun olvideMiPassword(email: String): Boolean {
        var success = false
        firebaseAuth.sendPasswordResetEmail(email).addOnCompleteListener {
            success = it.isSuccessful
        }
        return success
    }


    private fun reutenticar(email: String, password: String): AuthCredential? {
        val credntial: AuthCredential? = try {
            EmailAuthProvider.getCredential(email, password)
        } catch (e: Exception) {
            println("REautenticar: $e")
            null
        }
        return credntial
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
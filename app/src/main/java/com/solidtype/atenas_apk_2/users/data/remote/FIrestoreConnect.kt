package com.solidtype.atenas_apk_2.users.data.remote

import android.util.Log
import android.widget.Toast
import com.google.android.gms.tasks.Task
import com.google.android.gms.tasks.TaskCompletionSource

import com.google.firebase.Firebase

import com.google.firebase.firestore.firestore

class FirestoreConnect {



    private val db = Firebase.firestore

    //esta registrando el correo como ICCID arreglen eso, resulta que no funcionaba por que no estaba haciendo la operacion asincrona
    //y devolvia un valor antes de ejecutarse por completo la operacion
    //por eso devuelvo el task con el boolean en la funcion de iccidInvalited para que espere que haga la tarea y luego que finaliza devuelve la tarea con su estado en false o true
    //y luego verifica si el task fue succesfull en al funcion de NewUser para actualizar la informacion 

    fun newUser(user: Modelo) {
        iccidInvalited(user.correo)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val existeUsuario = task.result
                    if (existeUsuario) {
                        // SI el usuario existe, actualiza los datos
                        db.collection("users").document(user.correo).update(
                            "nombre", user.nombre,
                            "apellido", user.apellido,
                            "id_licensia", user.id_licensia,
                            "correo", user.correo,
                            "clave", user.clave,
                            "nombre_negocio", user.nombre_negocio,
                            "direccion_negocio", user.direccion_negocio,
                            "telefono", user.telefono
                        )
                            .addOnSuccessListener {
                                Log.d("USUARIO ACTUAL", "el ICCID existe Datos del usuario registrados: $user")
                            }
                            .addOnFailureListener { e ->
                                Log.e("USUARIO ACTUAL", "Error al registar los datos del usuario: $e")
                            }
                    } else {
                        Log.d("USUARIO ACTUAL", "el ICCID no existe, no se puede registrar el usuario: $user")
                    }
                } else {
                    Log.e("USUARIO ACTUAL", "Error al verificar el ICCID: ${task.exception}")
                }
            }
    }

    private fun iccidInvalited(iCCID: String): Task<Boolean> {
        val dbRefIccid = db.collection("users").document(iCCID)
        val task = TaskCompletionSource<Boolean>()

        dbRefIccid.get().addOnCompleteListener { taskResult ->
            if (taskResult.isSuccessful) {
                val usuarioExiste = taskResult.result?.exists() ?: true
                Log.e("USUARIO ACTUAL", "EL USUARIO CON EL ICCID $iCCID EXISTE: $usuarioExiste")
                task.setResult(usuarioExiste)
            } else {
                Log.e("USUARIO ACTUAL", "Error al obtener el documento: ${taskResult.exception}")
                task.setResult(false)
            }
        }

        return task.task
    }
}




data class Modelo(
    val nombre:String,
    val apellido:String,
    val id_licensia:String,
    val correo: String,
    val clave:String,
    val nombre_negocio: String,
    val direccion_negocio: String,
    val telefono:String

)

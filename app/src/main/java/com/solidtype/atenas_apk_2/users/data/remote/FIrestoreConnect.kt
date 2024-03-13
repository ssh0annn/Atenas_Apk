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
        iccidInvalited(user.id_licensia)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val existeUsuario = task.result
                    if (existeUsuario) {
                        // SI el usuario existe, actual los datos
                        db.collection("usuarios").document(user.id_licensia).update(
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

    fun iccidInvalited(iCCID: String): Task<Boolean> {
        val task = TaskCompletionSource<Boolean>()

        // Obtiene referencia a la colección "users"
        val coleccionRef = db.collection("usuarios")
        Log.d("USUARIO ACTUAL", "valor del ICCID en la funcion invalited : $iCCID")

        // Obtiene todos los documentos de la colección
        coleccionRef.get()
            .addOnCompleteListener { taskResult ->
                if (taskResult.isSuccessful) {
                    // Itera sobre los documentos
                    for (document in taskResult.result!!) {
                        // Compara el ICCID con el ID de cada documento
                        if (document.id == iCCID) {
                            // El ICCID existe en la colección
                            task.setResult(true)
                            return@addOnCompleteListener
                        }
                    }

                    // El ICCID no existe en la colección
                    task.setResult(false)
                } else {
                    // Maneja errores al obtener documentos
                    task.setException(taskResult.exception!!)
                }
            }

        return task.task
    }
}





data class Modelo(
    val nombre:String,
    val apellido:String,
    val correo: String,
    val id_licensia:String,
    val clave:String,
    val nombre_negocio: String,
    val direccion_negocio: String,
    val telefono:String

)

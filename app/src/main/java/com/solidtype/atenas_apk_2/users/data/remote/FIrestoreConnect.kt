package com.solidtype.atenas_apk_2.users.data.remote

import android.annotation.SuppressLint
import android.util.Log
import android.widget.Toast
import com.google.android.gms.tasks.Task
import com.google.android.gms.tasks.TaskCompletionSource

import com.google.firebase.Firebase
import com.google.firebase.firestore.DocumentSnapshot

import com.google.firebase.firestore.firestore
import kotlinx.coroutines.tasks.await
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.util.Date
import kotlin.system.exitProcess

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

 /*
    fun obtenerFechaActual(): String {
        val dateFormat = SimpleDateFormat("dd/MM/yyyy HH:mm:ss z")
        val fechaActual = Date()
        return dateFormat.format(fechaActual)
    }

    fun fechaExpirada(iCCID: String): Task<Boolean>{
        val task = TaskCompletionSource<Boolean>()
       val fechaActual = obtenerFechaActual()
       val  fechaFInal = db.collection("usuarios").document(iCCID).get().addOnSuccessListener { doc->
                doc.getString("fecha_final")
       }


        if (fechaFInal.isSuccessful){
            if (fechaFInal.result.toString() >= fechaActual){
                Log.d("USUARIO ACTUAL","LA FECHA DESDE FIREBASE ES ${fechaFInal.result}")
                Log.d("USUARIO ACTUAL","ESTA ES LA FECHA DESDE KOTLIN $fechaActual")
                db.collection("usuarios").document(iCCID).update(
                    "estado",false
                )
                task.setResult(true)
                Log.d("USUARIO ACTUAL","ESTADO ACTUALIAZADO CORRECTAMENTE ${fechaFInal.result}")

            }
            task.setResult(false)
        } else {
            Log.d("USUARIO ACTUAL","problema al encontrar el dato")
        }
       return task.task
    }
    */

    fun fechaExpirada(iCCID: String): Task<Boolean> {
        val task = TaskCompletionSource<Boolean>()
        val fechaActual = obtenerFechaActual()

        db.collection("usuarios").document(iCCID).get()
            .addOnSuccessListener { doc ->
                val fechaFinalString = doc.getString("fecha_final")

                if (fechaFinalString != null) {
                    val formatter = SimpleDateFormat("yyyy-MM-dd")

                    // Comparar las fechas directamente sin asignar a una variable adicional
                    if (formatter.parse(fechaFinalString) >= fechaActual) {
                        Log.d("USUARIO ACTUAL", "La fecha desde Firebase es $fechaFinalString")
                        Log.d("USUARIO ACTUAL", "Esta es la fecha desde Kotlin $fechaActual")

                        // Actualizar el estado a false en Firestore
                        db.collection("usuarios").document(iCCID).update("estado", false)
                            .addOnSuccessListener {
                                Log.d("USUARIO ACTUAL", "Estado actualizado correctamente")
                                task.setResult(true)
                            }
                            .addOnFailureListener {
                                Log.e("USUARIO ACTUAL", "Error al actualizar el estado", it)
                                task.setResult(false)
                            }
                    } else {
                        Log.d("USUARIO ACTUAL", "La fecha en Firestore no es mayor o igual a la fecha actual en Kotlin")
                        task.setResult(false)
                    }
                } else {
                    Log.d("USUARIO ACTUAL", "El documento no contiene la fecha final")
                    task.setResult(false)
                }
            }
            .addOnFailureListener {
                Log.e("USUARIO ACTUAL", "Problema al encontrar el dato", it)
                task.setResult(false)
            }

        return task.task
    }

    private fun obtenerFechaActual(): Date {
        return Date() // Obtener la fecha actual en formato Date
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




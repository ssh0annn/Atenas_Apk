package com.solidtype.atenas_apk_2.users.data.remote

import android.annotation.SuppressLint
import android.util.Log
import android.widget.Toast
import com.google.android.gms.tasks.Task
import com.google.android.gms.tasks.TaskCompletionSource

import com.google.firebase.Firebase
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore

import com.google.firebase.firestore.firestore

import kotlinx.coroutines.tasks.await
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.util.Date
import kotlin.system.exitProcess

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext


class FirestoreConnect {


    private val db = Firebase.firestore

    //esta registrando el correo como ICCID arreglen eso, resulta que no funcionaba por que no estaba haciendo la operacion asincrona
    //y devolvia un valor antes de ejecutarse por completo la operacion
    //por eso devuelvo el task con el boolean en la funcion de iccidInvalited para que espere que haga la tarea y luego que finaliza devuelve la tarea con su estado en false o true
    //y luego verifica si el task fue succesfull en al funcion de NewUser para actualizar la informacion 
    suspend fun newUser2(user: Modelo) = withContext(Dispatchers.IO) {
        if (validateIccid(user.id_licensia)) {
            println("ICCID Valida!! ")
            try {
                db.collection("usuarios").document(user.id_licensia).update(
                    "nombre", user.nombre,
                    "apellido", user.apellido,
                    "id_licensia", user.id_licensia,
                    "correo", user.correo,
                    "clave", user.clave,
                    "nombre_negocio", user.nombre_negocio,
                    "direccion_negocio", user.direccion_negocio,
                    "telefono", user.telefono

                ).await()
                println("Datos guardados correctamente")
                return@withContext true
            } catch (e: Exception) {
                println("No se pudo guardar datos en db ex: $e , <--")
                return@withContext false
            }
        } else {
            return@withContext false
        }
    }




    suspend fun fechaExpirada(iCCID: String): Boolean {
        val fechaActual = obtenerFechaActual()

        return try {
            val doc = db.collection("usuarios").document(iCCID).get().await()

            val fechaFinalString = doc.get("fecha_final")

            println("fechaFinal: $fechaFinalString")
            println("fechaActual $fechaActual")


            if (fechaFinalString != null) {
                val formatter = SimpleDateFormat("yyyy-MM-dd")
                val fechaFinal = formatter.parse(fechaFinalString)!!
                println("Comparar ${fechaActual >= fechaFinal}")
                if (fechaActual >= fechaFinal) {

                    // Actualizar el estado a false en Firestore
                    db.collection("usuarios").document(iCCID).update("estado", false).await()
                    true
                } else {
                    false
                }
            } else {
                false
            }
        } catch (e: Exception) {
            println("Una execption en fechaExpirada $e ")
            true
        }
    }

     fun obtenerFechaActual(): Date {
        return Date() // Esta función puede ser más compleja dependiendo de cómo quieras manejar la fecha actual
    }

    suspend fun documentoEstaVacio( iCCID: String): Boolean {
        val docRef = FirebaseFirestore.getInstance().collection("usuarios").document(iCCID)
        val document = docRef.get().await()

        // Verifica si el documento existe y si tiene algún campo
        return document.data?.isEmpty() ?: true

    }





        suspend fun validateIccid(iccid: String) = withContext(Dispatchers.IO) {

            try {
                val query = db.collection("usuarios").get().await()
                var n = 0
                for (document in query.documents) {
                    n++
                    println("EL iccid es : $iccid")
                    println("los documentos $n = ${document.id}")

                    if (iccid == document.id) {
                        println("Este es el documento encontrado: ${document.id}, <--")
                        println("este es el iCCID: $iccid")
                        return@withContext true

                    }
                }
                println("No se encontro iguales")
                return@withContext false

            } catch (e: Exception) {
                println("aqui es si no encuentra ningun documento")
                return@withContext false
            }

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




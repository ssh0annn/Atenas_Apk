package com.solidtype.atenas_apk_2.users.data.remote

import android.annotation.SuppressLint
import android.util.Log


import com.google.firebase.Firebase
import com.google.firebase.Timestamp
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore

import com.google.firebase.firestore.firestore
import kotlinx.coroutines.DelicateCoroutinesApi

import kotlinx.coroutines.tasks.await


import java.util.Date
import kotlin.system.exitProcess

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext
import java.util.Locale


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




    suspend fun fechaExpirada(iCCID: String): Boolean {// FUnciona
        val fechaActual = obtenerFechaActual()

        return try {
            val doc = db.collection("usuarios").document(iCCID).get().await()

            val fechaFinalString = doc.getTimestamp("fecha_final")?.toDate()
            Log.e("contenido fecha","este es el dato que viene de la fehca de firebase: $fechaFinalString")

            if (fechaFinalString != null) {
                if (fechaActual >= fechaFinalString) {

                    // Actualizar el estado a false en Firestore
                    db.collection("usuarios").document(iCCID).update("estado", false).await()
                    true
                } else {
                    db.collection("usuarios").document(iCCID).update("estado", true).await()
                    false
                }
            } else {
                true
            }
        } catch (e: Exception) {
            println("Una execption en fechaExpirada $e ")
            true
        }
    }

     fun obtenerFechaActual(): Date {
        return Date() // para obtener el dia/hora/fecha/ actual
    }


    suspend fun documentoEstaVacio( iCCID: String): Boolean {
        val docRef = db.collection("usuarios").document(iCCID).get().await()
        val document = docRef.getString("id_licencia")

        return try{
            // Verifica tiene algún campo
            document?.isNotBlank() ?: false

        }catch (e: Exception){
            true
        }

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




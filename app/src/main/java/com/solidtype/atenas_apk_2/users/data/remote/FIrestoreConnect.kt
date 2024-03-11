package com.solidtype.atenas_apk_2.users.data.remote

import android.annotation.SuppressLint
import android.media.AudioMetadata.Key
import android.util.Log

import com.google.firebase.Firebase
import com.google.firebase.firestore.auth.User

import com.google.firebase.firestore.firestore
import com.google.protobuf.Value
import java.util.HashMap

class FirestoreConnect {


    private val db = Firebase.firestore
    private val dbRef = db.collection("users")


    fun newUser(user: Modelo,ICCID:String){

            if (!iccidInvalited(ICCID)){
               db.collection("users").document(ICCID).set(user)
                Log.d("HOLAAAaaaa","REGISTROCOMPLETADO")
            } else{
                Log.d("HOLAAAaaaa","no se puede registrar")
            }
    }

    private fun iccidInvalited(ICCID:String):Boolean{

        val notIccid = dbRef.whereNotEqualTo(ICCID,false)

        return notIccid.equals(null)
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

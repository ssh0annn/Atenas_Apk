package com.solidtype.atenas_apk_2.users.data.remote

import android.annotation.SuppressLint
import android.media.AudioMetadata.Key
import android.util.Log

import com.google.firebase.Firebase
import com.google.firebase.firestore.auth.User

import com.google.firebase.firestore.firestore
import com.google.protobuf.Value
import java.util.HashMap

class FIrestoreConnect {


    private val db = Firebase.firestore
    private val dbRef = db.collection("users")


    fun newUser(user: Modelo,ICCID:String){

            if (iccidInvalited(ICCID)){
                db.collection("users").document(ICCID).set(user)
                Log.d("HOLAAAaaaa","REGISTROCOMPLETADO")
            } else{
                Log.d("HOLAAAaaaa","REGISTROCOMPLETADO")
            }

    }

    private fun iccidInvalited(ICCID:String):Boolean{

        val notIccid = dbRef.whereNotEqualTo(ICCID,false)

        if (notIccid.equals(null)){
            Log.e("HOLAAAaaaa","ESTE ICCID NO ESTA EN LA BASE DE DATOS")


        }
        return false
    }

}

data class Modelo(
    val username:String?,
    val password:String?,
    val correo:String?,
    val direccion:String?,
    val estado: String?
)

package com.solidtype.atenas_apk_2.products.data.remoteProFB

import android.annotation.SuppressLint
import android.util.Log
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.QuerySnapshot
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import java.lang.reflect.TypeVariable
import javax.inject.Inject

class QuerysFirstore @Inject constructor(
    private val fireStore: FirebaseFirestore,
    private val authUser: FirebaseAuth
) {
    val uidUser: String = "VUxGubuZ1AZy7hXBvP8E"

    suspend fun getAllDataFirebase(collectionName: String) =

        withContext(Dispatchers.IO) {
            try {

                val allData = fireStore.collection("usuarios")
                    .document(uidUser)
                    .collection(collectionName)
                    .get()
                    .await()
                return@withContext allData

            } catch (e: Exception) {
                Log.e("FirebaseError", "Error al obtener datos de Firebase", e)
                throw Exception("no se pudo obtener los datos desde firebase")
            }
        }


    //se convierte el snashopt a json por medio de la seralizacion
    private fun snapshotToJson(snapshot: QuerySnapshot): String {
        val queryJson = mutableListOf<Map<String, Any?>>()
        //se recorre el la lista con el documento para la conversicio
        for (document in snapshot.documents) {
            val data = document.data
            if (data != null) {

                queryJson.add(data)
            }
        }
        //luego se hace una convercion de json a string
        return Json.encodeToString(queryJson)
    }

    suspend fun insertToFirebase(collectionName: String, dataToInsert: List<List<String>>) {

        try {
            withContext(Dispatchers.Default) {
                val lote = fireStore.batch()
                for (data in dataToInsert) {
                    if (data.isNotEmpty() && data.size ==9) {

                      val ref=  fireStore.collection("usuarios")
                            .document(uidUser)
                            .collection(collectionName)
                            .document(data[0])
                        val dataMapa = mapOf(
                            "code_Product" to data[0],
                            "name_Product" to data[1],
                            "description_Product" to data[2],
                            "category_Product" to data[3],
                            "price_Product" to data[4],
                            "model_Product" to data[5],
                            "price_Vending_Product" to data[6],
                            "tracemark_Product" to data[7],
                            "count_Product" to data[8],

                            )
                        lote.set(ref, dataMapa)
                    }else{
                        println("Problemas con la lista: $data y sieze: ${data.size}")
                    }
                }
                lote.commit().await()

            }


        } catch (e: Exception) {
            Log.e("error firebase", "No se puedo insertar $e")
            throw Exception("no se pudo insertar los datos a firebase $e")
        }

    }

    suspend fun deleteDataFirebase(collectionName: String, dataToDelete: List<List<String>>) {
        try {

            withContext(Dispatchers.IO) {
                var b = 0
                for (i in dataToDelete) {


                    val allData = fireStore.collection("usuarios")
                        .document(uidUser)
                        .collection(collectionName)
                        .document(dataToDelete[b][0])
                        .id

                    fireStore.collection("usuarios").document(uidUser).collection(collectionName)
                        .document(allData).delete()






                    println("Probando: ${dataToDelete[b][0]}")
                    b += 1

                }
            }


        } catch (e: Exception) {
            println("Este es la puta excepcion: $e")
            throw Exception("No se pudo eliminar los datos de firebase $e")
        }
    }
}

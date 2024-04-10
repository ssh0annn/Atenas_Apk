package com.solidtype.atenas_apk_2.products.data.remoteProFB

import android.annotation.SuppressLint
import android.util.Log
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.QuerySnapshot
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import javax.inject.Inject

class QuerysFirstore @Inject constructor(
    private val fireStore: FirebaseFirestore,
    private val authUser : FirebaseAuth
) {

    val uidUser: String = "VUxGubuZ1AZy7hXBvP8E"

    suspend fun getAllDataFirebase(collectionName:String) : String {

        return withContext(Dispatchers.IO){
            try {
                val allData = fireStore.collection("usuarios")
                    .document(uidUser)
                    .collection(collectionName)
                    .get()
                    .await()
                return@withContext snapshotToJson(allData)



            }catch (e:Exception){
                Log.e("FirebaseError", "Error al obtener datos de Firebase", e)
                throw Exception ("no se pudo obtener los datos desde firebase")
            }
        }

    }

    private fun snapshotToJson(snapshot: QuerySnapshot):String{
        val products = mutableListOf<SerializableModelProducts>()

        for (document in snapshot.documents){
            val data = document.data
            if (data != null){
                val product = SerializableModelProducts(
                    data["category_Product"] as Int,
                    data["Description_Product "] as String,
                    data["Category_Product"] as String,
                    data["Price_Product"] as Double,
                    data["Model_Product"] as String,
                    data["Price_Vending_Product"] as Double,
                    data["Tracemark_Product"] as String,
                    data["Count_Product"] as Int

                )
                products.add(product)
            }

        }
        Log.e("snapshotToJson","los datos del query son ${products.forEach { data->  println(data.code_product) }}")
        return Json.encodeToString(products)
    }

    /*
       //se convierte el snashopt a json por medio de la seralizacion
    private fun snapshotToJson(snapshot: QuerySnapshot) : String{
        val queryJson =  mutableListOf<Map<String,Any?>>()
        //se recorre el la lista con el documento para la conversicio
        for (document in snapshot.documents){
            val  data = document.data
            if (data != null){
                queryJson.add(data)
            }
        }
           //luego se hace una convercion de json a string
        return Json.encodeToString(queryJson)
    }
*/
    suspend fun insertToFirebase (collectionName:String, dataToInsert: List<List<String>> ) {

        try {
            val batch = fireStore.batch()
            dataToInsert.forEach { data->
                if (data.isNotEmpty()){
                    batch.set(
                        fireStore.collection("usuarios")
                            .document(uidUser)
                            .collection(collectionName)
                            .document(data[0]),data)
                }
            }
            batch.commit().await()
        }catch (e:Exception){
            Log.e("error firebase","No se puedo insertar $e")
            throw Exception ("no se pudo insertar los datos a firebase")
        }

    }

    suspend fun deleteDataFirebase (collectionName:String, dataToDelete: List<List<String>>){
        try {
            val batch = fireStore.batch()
            dataToDelete.forEach { data->
                fireStore.collection("Usuarios")
                    .document(uidUser)
                    .collection(collectionName)
                    .document(data[0])
                    .delete()
                    .await()
            }
            batch.commit().await()
        }catch (e:Exception){
            throw Exception ("No se pudo eliminar los datos de firebase")
        }
    }
}
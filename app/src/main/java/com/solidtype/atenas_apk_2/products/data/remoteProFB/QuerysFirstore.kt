package com.solidtype.atenas_apk_2.products.data.remoteProFB

import android.util.Log
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.QuerySnapshot
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext
import javax.inject.Inject

/**
 * @constructor:private val fireStore: FirebaseFirestore,
 *     private val authUser: FirebaseAuth,
 *  Estos parametros los recibe por medio de la injection de DaggerHilt.
 *  @Funcionamiento:
 *  QuerysFirstore se encarga de toda la logica de consulta, insercion, eliminacion y actaulizaciones
 *  requeridas para firestore. Utiliza escrituras, actualizacion y eliminaciones por lotes. Solo se comunica atravez de listas de maps de string
 *  y devuelbe en las consulta un QuerySnapshot
 */
class QuerysFirstore @Inject constructor(
    private val fireStore: FirebaseFirestore
) {
    private val uidUser: String = "VUxGubuZ1AZy7hXBvP8E"

    /**
     * @param: String
     *@return: QuerySnapshot?
     *@getAllDataFirebase
     *Captura toda una colecion de fireStore espesificada en el parametro.
     *
     */

    suspend fun getAllDataFirebase(collectionName: String): QuerySnapshot? =

        withContext(Dispatchers.IO) {
            try {
                return@withContext fireStore.collection("usuarios")
                    .document(uidUser)
                    .collection(collectionName)
                    .get()
                    .await<QuerySnapshot?>()
            } catch (e: Exception) {
                Log.e("FirebaseError", "Error al obtener datos de Firebase", e)
                throw Exception("no se pudo obtener los datos desde firebase $e")
            }
        }



    //se convierte el snashopt a json por medio de la
    /**
     * @param: QuerySnapshot
     * @return: String, serializado en json.
     * @Nota: No esta en uso aun.
     */
    /*private fun snapshotToJson(snapshot: QuerySnapshot): String {
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
    }*/


    /**
    @param: String, List<Map<String, String>>, String
    @return: Unit
    @insertToFirebase
    @Pide: Nombre de la collecion, lista del diccionario de datos a insertar y el id del documento donde se insertaran
    @Funcion:

     */
    suspend fun insertToFirebase(
        collectionName: String,
        dataToInsert: List<Map<String, String>>,
        idDocumento: String
    ) {
        println("idDocument: $idDocumento")

        try {
            withContext(Dispatchers.Default) {
                val lote = fireStore.batch()
                for (data in dataToInsert) {
                    val ref = data[idDocumento]?.let {idMapa ->
                        fireStore.collection("usuarios")
                            .document(uidUser)
                            .collection(collectionName)
                            .document(idMapa)
                    }

                    if (ref != null) {
                        lote.set(ref, data)
                    }

                }
                lote.commit().await()
            }
        } catch (e: Exception) {
            Log.e("error firebase", "No se puedo insertar $e")
            throw Exception("no se pudo insertar los datos a firebase $e")
        }

    }


    /**
     * @param: String, List<Map<String, String>>, String
     * @return: Unit
     * @throws: Exception si no se pueden eliminar los datos en firebas
     * @Funcionamiento: Recibe nombre de la colecion, lista de objetos a eliminar y la id del documento en firestore.
     * EL formato de eliminar es por lote, y se requiere llamar desde una corrutina.
     */
    suspend fun deleteDataFirebase(
        collectionName: String,
        dataToDelete: List<Map<String, String>>,
        idDocumento: String
    ) {
        try {
            withContext(Dispatchers.IO) {
                val lote = fireStore.batch()
                for (i in dataToDelete) {
                    val allData = i[idDocumento]?.let {
                        fireStore.collection("usuarios")
                            .document(uidUser)
                            .collection(collectionName)
                            .document(it)
                    }
                    if (allData != null) {
                        lote.delete(allData)
                    }

                }
                lote.commit().await()
            }

        } catch (e: Exception) {
            println("Este es la puta excepcion: $e")
            throw Exception("No se pudo eliminar los datos de firebase $e")
        }
    }
}

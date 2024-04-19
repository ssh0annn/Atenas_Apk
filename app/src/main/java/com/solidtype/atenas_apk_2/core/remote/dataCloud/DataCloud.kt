package com.solidtype.atenas_apk_2.core.remote.dataCloud

import com.google.firebase.firestore.QuerySnapshot

//interfaces para anclarte al queryFirestore, para que cada feature no dependa de la implementacion si no de una interfaz.
interface DataCloud {

        suspend fun getallData(collection: String): QuerySnapshot?

        suspend fun insertAllToCloud (collection: String,dataToInsert:List<Map<String,String>>, idDocumento: String)

        suspend fun deleteDataInCloud(collectionName: String, dataToDelete: List<Map<String, String>>, idDocumento: String)

}
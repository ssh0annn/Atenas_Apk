package com.solidtype.atenas_apk_2.core.remote.dataCloud

import com.google.firebase.firestore.QuerySnapshot
import com.solidtype.atenas_apk_2.authentication.actualizacion.data.modelo.CheckListAuth
import com.solidtype.atenas_apk_2.authentication.actualizacion.domain.TipoUser

//interfaces para anclarte al queryFirestore, para que cada feature no dependa de la implementacion si no de una interfaz.
interface DataCloud {

        suspend fun getallData(collection: String): QuerySnapshot?

        suspend fun insertAllToCloud (collection: String,dataToInsert:List<Map<String,String>>, idDocumento: String)

        suspend fun deleteDataInCloud(collectionName: String, dataToDelete: List<Map<String, String>>, idDocumento: String)
        /**
         * Buscara en las tablas: Administrador y si no encuentra, pues ira a la tabla vendedor y tecnicos.
         * SI encuentra dato en administrador debuelve un tipouser.Admin, y si es vendedor tipouser.vendedor...
         * Si no encuentradada : tipouser.UNKNOWN. (desconocido)
         */
        suspend fun autenticacionCloud(email:String, licencia:String,dispositivo:String): CheckListAuth


        suspend fun insertAllToCloud2(
                collection: String,
                dataToInsert: MutableList<Map<String,Map<String,Any>>>,
                idDocumento: String
        )

}
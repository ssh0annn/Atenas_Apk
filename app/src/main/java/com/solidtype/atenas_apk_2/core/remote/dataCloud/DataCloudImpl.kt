package com.solidtype.atenas_apk_2.core.remote.dataCloud

import android.content.Context
import android.util.Log
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.QueryDocumentSnapshot
import com.google.firebase.firestore.QuerySnapshot
import com.solidtype.atenas_apk_2.authentication.actualizacion.data.modelo.CheckListAuth
import com.solidtype.atenas_apk_2.authentication.actualizacion.domain.TipoUser
import com.solidtype.atenas_apk_2.core.remote.authtentication.auth
import com.solidtype.atenas_apk_2.dispositivos.model.Dispositivo
import dagger.hilt.android.qualifiers.ApplicationContext
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

class DataCloudImpl @Inject constructor(
    @ApplicationContext private val context: Context,
    private val fireStore: FirebaseFirestore,
    autenticador: auth
) : DataCloud {


//    private val uidUser: String = autenticador.getCurrentUser()!!.uid

    private val user = Firebase.auth.currentUser
    private var licencia: String = "licensias"
    private var DDBB: String = "db"
    private var DbCollectionUsers = "usuarios"
    private val recuerdame = context.getSharedPreferences("Sincronizacion", Context.MODE_PRIVATE)
    private var uidUser: String = recuerdame.getString(DDBB,"").toString()

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
     * @param: String
     *@return: QuerySnapshot?
     *@getAllDataFirebase
     *Captura toda una colecion de fireStore espesificada en el parametro.
     *
     */

    override suspend fun getallData(collection: String): QuerySnapshot? =

        withContext(Dispatchers.Default) {
            println("Este es el uid actual DataCloudImpl $uidUser <----")
            try {
                return@withContext fireStore.collection("usuarios")
                    .document(uidUser)
                    .collection(collection)
                    .get()
                    .await<QuerySnapshot?>()
            } catch (e: Exception) {
                Log.e("FirebaseError", "Error al obtener datos de Firebase", e)
                throw Exception("no se pudo obtener los datos desde firebase $e")
            }
        }


    /**
    @param: String, List<Map<String, String>>, String
    @return: Unit
    @insertToFirebase
    @Pide: Nombre de la collecion, lista del diccionario de datos a insertar y el id del documento donde se insertaran
    @Funcion:

     */

    override suspend fun insertAllToCloud(
        collection: String,
        dataToInsert: List<Map<String, String>>,
        idDocumento: String
    ) {
        println("idDocument: $idDocumento")

        try {
            withContext(Dispatchers.Default) {
                val lote = fireStore.batch()
                for (data in dataToInsert) {
                    val ref = data[idDocumento]?.let { idMapa ->
                        fireStore.collection("usuarios")
                            .document(uidUser)
                            .collection(collection)
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


    override suspend fun insertAllToCloud2(
        collection: String,
        dataToInsert: MutableList<Map<String,Map<String,Any>>>,
        idDocumento: String
    ) {

        try {

            withContext(Dispatchers.Default) {
                val lote = fireStore.batch()
                println("idDocument: $idDocumento")
                dataToInsert.forEach{
                    val llaves =   it.keys
                    for (i in llaves) {

                        val coleccion = i
                        val ticketData = hashMapOf(
                            "ticket" to it[i]?.get("ticket"),
                            "usuario" to it[i]?.get("usuario"),
                            "servicio" to it[i]?.get("servicio"),
                            "persona" to it[i]?.get("persona"),
                            "tipo_venta" to it[i]?.get("tipo_venta"),
                            "tipo_dispositivo" to it[i]?.get("tipo_dispositivo")
                        )


                        val reference =  fireStore.collection("usuarios")
                            .document(uidUser)
                            .collection(collection)
                            .document(coleccion)

                        lote.set(reference, ticketData)

                        val ticket:Any? =  it[i]?.get("ticket")
                        val usuario:Any? =  it[i]?.get("usuario")
                        val servicio:Any? =  it[i]?.get("servicio")
                        val persona:Any? =  it[i]?.get("persona")
                        val tipo_venta:Any? =  it[i]?.get("tipo_venta")
                        val tipo_dispositivo:Any? =  it[i]?.get("tipo_dispositivo")
                        println("estos datos son el for dataInsert")
                        println(coleccion)
                        println(ticket)
                        println(usuario)
                        println(servicio)
                        println(persona)
                        println(tipo_venta)
                        println(tipo_dispositivo)
                        println("estos datos son el for dataInsert")

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
     * @param: String
     *@return: QuerySnapshot?
     *@getAllDataFirebase
     *Captura toda una colecion de fireStore espesificada en el parametro.
     *
     */

    override suspend fun deleteDataInCloud(
        collection: String,
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
                            .collection(collection)
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


    /**
     * Buscara en las tablas: Administrador y si no encuentra, pues ira a la tabla vendedor y tecnicos.
     * SI encuentra dato en administrador debuelve un tipouser.Admin, y si es vendedor tipouser.vendedor...
     * Si no encuentradada : tipouser.UNKNOWN. (desconocido)
     */


    suspend fun getAllLicencia(): QuerySnapshot? {

        try {
            return fireStore.collection(licencia)
                .get()
                .await<QuerySnapshot?>()

        } catch (e: Exception) {
            Log.e("error firebase", "No vinieron datos de licencia: Linea 189 DataCLoued $e")
            return null
        }

    }


    private fun isLincenciaValida(
        queryLicencias: QuerySnapshot,
        licencia: String,
        dispositivo: String
    ): String? {
        val checker = CheckListAuth()
        queryLicencias.forEach { documentData ->
            val documents = documentData.data
            if (documents["noLicencia"]?.equals(licencia) == true) {
                if (documents["estadoLicencia"] == true) {
                    checker.licensiaActiva = true
                    if (documents["idDevice"].toString() == dispositivo) {
                        checker.deviceRegistrado = true

                        return documents["direcionDB"].toString()
                    }
                }
            }
        }
        return null
    }

    private fun encuentraDocLicencia(querySnapshot: QuerySnapshot?, licensia:String):QueryDocumentSnapshot?{
        val list = querySnapshot?.find { it.data["noLicencia"].toString() == licensia }
        return list
    }

    suspend fun laQuellamo (email: String,
                            licencia: String,
                            dispositivo: String):CheckListAuth{
        val checker = CheckListAuth()
        val encontrada = encuentraDocLicencia(getAllLicencia(), licencia)
         if(encontrada?.get("idDevice") == dispositivo){
             checker.deviceRegistrado = true

         }
        if(encontrada?.get("estadoLicencia") == true){
            checker.licensiaActiva = true
            println("Estado de licencia ${encontrada.get("estadoLicencia")} ")

        }
        checker.tipoUser = tipoUsurio(encontrada?.get("direcionDB").toString(),email )
        recuerdame(encontrada?.get("direcionDB").toString())
        checker.emailUsuario = email
        checker.autenticado = true
        println("Asi esta el checker $checker")
        return checker
    }


suspend fun tipoUsurio(direcionDb:String, email: String): TipoUser{
    try {
        val doc = fireStore.collection(DbCollectionUsers).document(direcionDb).get()
                .await()
        val campos = doc.data
        val correo = campos?.get("correo")?.toString()
        if (correo == email) {
            println("email $correo == correo $email")
            return TipoUser.ADMIN
        } else {
            val colletionVendedores =
                fireStore.collection(DbCollectionUsers).document(direcionDb)
                    .collection("vendedor").get().await()
            colletionVendedores.forEach { documetos ->
                if (documetos["correo"] == email) {
                    println("email $email == correo $email")
                    return TipoUser.VENDEDOR
                }
            }
        }
    } catch (e: Exception) {
        Log.e("error firebase", "No Coincidio el Correo$e")
        return TipoUser.UNKNOWN
    }
    return TipoUser.UNKNOWN
}

        //Por implementar
        override suspend fun autenticacionCloud(
            email: String,
            licencia: String,
            dispositivo: String
        ): CheckListAuth {
            return  laQuellamo(email, licencia, dispositivo)


        }

        //Por implementar
        override suspend fun validarDispositivo(idDispositivo: String): Boolean {
            return true
        }

    private fun recuerdame(db: String?) {
        recuerdame
            .edit()
            .putString(DDBB, db)
            .apply()

    }

    }

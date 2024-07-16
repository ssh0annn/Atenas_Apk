package com.solidtype.atenas_apk_2.core.remote.dataCloud

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import com.google.firebase.Firebase
import com.google.firebase.Timestamp
import com.google.firebase.auth.auth
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ListenerRegistration
import com.google.firebase.firestore.QueryDocumentSnapshot
import com.google.firebase.firestore.QuerySnapshot
import com.google.firebase.firestore.SetOptions
import com.google.firebase.firestore.toObjects
import com.solidtype.atenas_apk_2.authentication.actualizacion.data.modelo.CheckListAuth
import com.solidtype.atenas_apk_2.authentication.actualizacion.domain.TipoUser
import com.solidtype.atenas_apk_2.core.transacciones.modeloTransacciones.InventarioModeloRelation
import com.solidtype.atenas_apk_2.core.transacciones.modeloTransacciones.UsuariosRelation
import com.solidtype.atenas_apk_2.gestion_proveedores.data.persona
import com.solidtype.atenas_apk_2.perfil_administrador.domain.modelo.administrador



import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.awaitCancellation
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.ZoneId
import java.util.Date
import javax.inject.Inject
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException

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
    private val fireStore: FirebaseFirestore,

    ) : DataCloud {


    //    private val uidUser: String = autenticador.getCurrentUser()!!.uid
    //donde esta la funcion que guarda la refereacia dur epila buscando el problema y era aqui palomo
    private var uidUser: String = "johan@labestia.com"
    private var licencia: String = "licensias"
    private var DDBB: String = "db"
    private var DbCollectionUsers = "usuarios"

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


    suspend fun getallDataDocumentsAdmin(): DocumentSnapshot? =
        withContext(Dispatchers.Default) {
            println("Este es el uid actual DataCloudImpl $uidUser <----")
            try {
                return@withContext fireStore.collection("usuarios")
                    .document(uidUser)
                    .get()
                    .await()
            } catch (e: Exception) {
                Log.e("FirebaseError", "Error al obtener datos de Firebase", e)
                throw Exception("no se pudo obtener los datos desde firebase $e")
            }
        }

   suspend fun insertAdministradorInFirestore(administrador: administrador) {
        val db = FirebaseFirestore.getInstance()
        val docRef = db.collection("administradores").document(uidUser)

        val fechaCompra = administrador.fecha_compra?.let {
            Timestamp(Date.from(it.atStartOfDay(ZoneId.systemDefault()).toInstant()))
        }
        val fechaCaduca = administrador.fecha_caduca?.let {
            Timestamp(Date.from(it.atStartOfDay(ZoneId.systemDefault()).toInstant()))
        }


        val adminMap = hashMapOf(
            "id_administrador" to administrador.id_administrador,
            "nombre" to administrador.nombre,
            "apellido" to administrador.apellido,
            "correo" to administrador.correo,
            "telefono" to administrador.telefono,
            "clave" to administrador.clave,
            "direccion_negocio" to administrador.direccion_negocio,
            "nombre_negocio" to administrador.nombre_negocio,
            "licencia" to administrador.licencia,
            "fecha_compra" to fechaCompra,
            "fecha_caduca" to fechaCaduca,
            "estado" to administrador.estado
        )

         val dataAdmin = getallDataDocumentsAdmin()



       if (!dataAdmin!!.exists()) {
           docRef.set(adminMap)
               .addOnSuccessListener {
                   println("Documento insertado con éxito")
                   // Documento insertado con éxito
               }
               .addOnFailureListener { e ->
                   // Error al insertar el documento
                   println("Error al insertar el documento: ")
               }
       }else if (dataAdmin.data!!.isEmpty()) {
           docRef.set(adminMap)
           docRef.set(adminMap)
               .addOnSuccessListener {
                   println("Documento insertado con éxito")
                   // Documento insertado con éxito
               }
               .addOnFailureListener { e ->
                   // Error al insertar el documento
                   println("Error al insertar el documento: ")
               }
       }else{
           println("tiene datos")
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
        dataToInsert: MutableList<Map<String, Map<String, Any>>>,
        idDocumento: String
    ) {

        try {

            withContext(Dispatchers.Default) {
                val lote = fireStore.batch()
                println("idDocument: $idDocumento")
                dataToInsert.forEach {
                    val llaves = it.keys
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


                        val reference = fireStore.collection("usuarios")
                            .document(uidUser)
                            .collection(collection)
                            .document(coleccion)

                        lote.set(reference, ticketData)

                        val ticket: Any? = it[i]?.get("ticket")
                        val usuario: Any? = it[i]?.get("usuario")
                        val servicio: Any? = it[i]?.get("servicio")
                        val persona: Any? = it[i]?.get("persona")
                        val tipo_venta: Any? = it[i]?.get("tipo_venta")
                        val tipo_dispositivo: Any? = it[i]?.get("tipo_dispositivo")
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


    override suspend fun insertAllToCloud3(
        collection: String,
        dataToInsert: MutableList<Map<String, Map<String, Any>>>,
        idDocumento: String
    ) {

        try {

            withContext(Dispatchers.Default) {
                val lote = fireStore.batch()
                println("idDocument: $idDocumento")
                dataToInsert.forEach {
                    val llaves = it.keys
                    for (i in llaves) {

                        val coleccion = i
                        val ticketData = hashMapOf(
                            "Inventario" to it[i]?.get("Inventario"),
                            "Categoria" to it[i]?.get("Categoria"),
                            "Persona" to it[i]?.get("Persona"),
                        )


                        val reference = fireStore.collection("usuarios")
                            .document(uidUser)
                            .collection(collection)
                            .document(coleccion)

                        lote.set(reference, ticketData)

                        val Inventario: Any? = it[i]?.get("Inventario")
                        val Categoria: Any? = it[i]?.get("Categoria")
                        val Persona: Any? = it[i]?.get("Persona")
                        println("estos datos son el for dataInsert")
                        println(coleccion)
                        println(Inventario)
                        println(Categoria)
                        println(Persona)
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

    override suspend fun insertAllToCloudDetalleVenta(
        collection: String,
        dataToInsert: MutableList<Map<String, Map<String, Any>>>,
        idDocumento: String
    ) {

        try {

            withContext(Dispatchers.Default) {
                val lote = fireStore.batch()
                println("idDocument: $idDocumento")
                dataToInsert.forEach {
                    val llaves = it.keys
                    for (i in llaves) {

                        val coleccion = i
                        val ticketData = hashMapOf(
                            "Detalle_venta" to it[i]?.get("Detalle_venta"),
                            "venta" to it[i]?.get("venta"),
                            "Inventario" to it[i]?.get("Inventario"),
                            "Tipo_venta" to it[i]?.get("Tipo_venta"),
                        )


                        val reference = fireStore.collection("usuarios")
                            .document(uidUser)
                            .collection(collection)
                            .document(coleccion)

                        lote.set(reference, ticketData)

                        val Detalle_venta: Any? = it[i]?.get("Detalle_venta")
                        val venta: Any? = it[i]?.get("venta")
                        val Inventario: Any? = it[i]?.get("Inventario")
                        val Tipo_venta: Any? = it[i]?.get("Tipo_venta")
                        println("estos datos son el for dataInsert")
                        println(coleccion)
                        println(Detalle_venta)
                        println(venta)
                        println(Inventario)
                        println(Tipo_venta)
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


    override suspend fun insertAllToCloudUsuarios(
        collection: String,
        dataToInsert: MutableList<Map<String, Map<String, Any>>>,
        idDocumento: String
    ) {

        try {

            withContext(Dispatchers.Default) {
                val lote = fireStore.batch()
                println("idDocument: $idDocumento")
                dataToInsert.forEach {
                    val llaves = it.keys
                    for (i in llaves) {

                        val coleccion = i
                        val ticketData = hashMapOf(
                            "usuario" to it[i]?.get("usuario"),
                            "roll_usuario" to it[i]?.get("roll_usuario"),
                        )


                        val reference = fireStore.collection("usuarios")
                            .document(uidUser)
                            .collection(collection)
                            .document(coleccion)

                        lote.set(reference, ticketData)

                        val usuario: Any? = it[i]?.get("usuario")
                        val roll_usuario: Any? = it[i]?.get("roll_usuario")
                        val Inventario: Any? = it[i]?.get("Inventario")
                        val Tipo_venta: Any? = it[i]?.get("Tipo_venta")
                        println("estos datos son el for dataInsert")
                        println(coleccion)
                        println(usuario)
                        println(roll_usuario)
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


    override suspend fun insertAllToCloudVentas(
        collection: String,
        dataToInsert: MutableList<Map<String, Map<String, Any>>>,
        idDocumento: String
    ) {

        try {

            withContext(Dispatchers.Default) {
                val lote = fireStore.batch()
                println("idDocument: $idDocumento")
                dataToInsert.forEach {
                    val llaves = it.keys
                    for (i in llaves) {

                        val coleccion = i
                        val ticketData = hashMapOf(
                            "venta" to it[i]?.get("venta"),
                            "usuario" to it[i]?.get("usuario"),
                            "persona" to it[i]?.get("persona"),
                            "tipo_venta" to it[i]?.get("tipo_venta"),
                        )


                        val reference = fireStore.collection("usuarios")
                            .document(uidUser)
                            .collection(collection)
                            .document(coleccion)

                        lote.set(reference, ticketData)

                        val venta: Any? = it[i]?.get("venta")
                        val usuario: Any? = it[i]?.get("usuario")
                        val persona: Any? = it[i]?.get("persona")
                        val tipo_venta: Any? = it[i]?.get("tipo_venta")
                        println("estos datos son el for dataInsert")
                        println(coleccion)
                        println(venta)
                        println(usuario)
                        println(persona)
                        println(tipo_venta)
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


    /**
     * Buscara en las tablas: Administrador y si no encuentra, pues ira a la tabla vendedor y tecnicos.
     * SI encuentra dato en administrador debuelve un tipouser.Admin, y si es vendedor tipouser.vendedor...
     * Si no encuentradada : tipouser.UNKNOWN. (desconocido)
     */

    private suspend fun getAllLicencia(): QuerySnapshot? {

        try {
            return fireStore.collection(licencia)
                .get()
                .await<QuerySnapshot?>()

        } catch (e: Exception) {
            Log.e("error firebase", "No vinieron datos de licencia: Linea 189 DataCLoued $e")
            return null
        }

    }

    private fun encuentraDocLicencia(
        querySnapshot: QuerySnapshot?,
        licensia: String
    ): QueryDocumentSnapshot? {
        val list = querySnapshot?.find {
            it.data["noLicencia"].toString() == licensia
        }
        for (i in querySnapshot!!.documents) {
            println("datos ${i.data}")

        }
        println("Lincencia pasasda $licensia")
        return list
    }

    private suspend fun laQuellamo(
        email: String,
        licencia: String,
        dispositivo: String
    ): CheckListAuth {
        val checker = CheckListAuth()
        val encontrada = encuentraDocLicencia(getAllLicencia(), licencia)
        if (encontrada?.get("idDevice") == dispositivo) {
            checker.deviceRegistrado = true
        }

        val fechaFinal = convertAnyToTimestamp(encontrada?.get("fechaFinal")!!)
        val fechaFinalFormatted = convertTimestampToLocalDate(fechaFinal!!)
        println("fecha final dataCLoud $fechaFinalFormatted")
        println("fecha actual ${LocalDate.now()}")


        if (encontrada?.get("estadoLicencia") == true) {
            if (fechaFinalFormatted >= LocalDate.now()) {
                encontrada.reference.update("estadoLicencia", false)
                checker.licensiaActiva = false
            } else {
                checker.licensiaActiva = true
            }
        }

        checker.tipoUser = tipoUsurio(encontrada?.get("direcionDB").toString(), email)
        checker.emailUsuario = email
        checker.autenticado = true
        println("Asi esta el checker $checker")
        return checker
    }

    @SuppressLint("SimpleDateFormat")
    fun convertAnyToTimestamp(value: Any): Timestamp? {
        return when (value) {
            is Timestamp -> value
            is Date -> Timestamp(value)
            is Long -> Timestamp(Date(value))
            else -> null
        }
    }

    fun LocalDateNowToTimestampWithNanoseconds(): Timestamp {
        // Obtener la fecha actual
        val localDate = LocalDate.now()

        // Convertir LocalDate a LocalDateTime al inicio del día
        val localDateTime = localDate.atStartOfDay()

        // Convertir LocalDateTime a Instant en la zona horaria del sistema
        val instant = localDateTime.atZone(ZoneId.systemDefault()).toInstant()

        // Obtener los segundos y nanosegundos desde la época (1970-01-01T00:00:00Z)
        val seconds = instant.epochSecond
        val nanoseconds = instant.nano

        // Crear y devolver un Timestamp con los segundos y nanosegundos
        return Timestamp(seconds, nanoseconds)
    }

    private val TIME_ZONE = ZoneId.systemDefault()

    fun convertTimestampToLocalDate(timestamp: Timestamp): LocalDate {
        // Convertir Timestamp a Date
        val date = timestamp.toDate()

        // Convertir Date a LocalDate usando la zona horaria del sistema
        return date.toInstant().atZone(TIME_ZONE).toLocalDate()
    }




    private suspend fun tipoUsurio(direcionDb: String, email: String): TipoUser {
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
        return laQuellamo(email, licencia, dispositivo)
    }

    //Por implementar

    // persona desde room a data cloud
    suspend fun getSubColletionPersona(subCollection: String): List<persona> =
        withContext(Dispatchers.Default) {
            suspendCancellableCoroutine { continuation ->
                fireStore.collection("usuarios").document(uidUser).collection(subCollection).get()
                    .addOnSuccessListener { snapshots ->
                        val personas = snapshots.toObjects(persona::class.java)
                        continuation.resume(personas)
                    }
                    .addOnFailureListener { exception ->
                        continuation.resumeWithException(exception)
                    }
            }
        }

    fun getSubCollectionPersonaRealtime(subCollection: String): Flow<List<persona>> = flow {
        val personasFlow = MutableStateFlow<List<persona>>(emptyList())
        var listenerRegistration: ListenerRegistration? = null

        try {
            listenerRegistration = fireStore.collection("usuarios").document(uidUser).collection(subCollection)
                .addSnapshotListener { snapshots, error ->
                    if (error != null) {
                        // Manejar error
                        return@addSnapshotListener
                    }

                    if (snapshots != null) {
                        val personas = mutableListOf<persona>()
                        for (doc in snapshots) {
                            val persona = doc.toObject(persona::class.java)
                            personas.add(persona)
                        }
                        personasFlow.value = personas
                    }
                }

            // Emitir flujo de datos inicialmente vacío (o con datos actuales si se desea)
            emit(personasFlow.value)

            // Esperar a que el flujo se cancele para eliminar el listener
            awaitCancellation()
        } finally {
            // Eliminar el listener al finalizar
            listenerRegistration?.remove()
        }
    }

    //insertar persona desde room a data cloud
    suspend fun insertarPersona(personas: List<persona>, subColletion: String) {
        println("insertar persona desde room a data cloud")
        println()
        println("estas son las personas a insertar $personas")
        withContext(Dispatchers.IO) {
            if (personas.isNotEmpty()) {
                val jobs = personas.map { persona ->
                    async {
                        try {
                            fireStore.collection("usuarios")
                                .document(uidUser)
                                .collection(subColletion)
                                .document(persona.id_persona.toString())
                                .set(persona)
                                .await()  // Utilizando await para esperar a que cada operación termine
                        } catch (e: Exception) {
                            Log.e(
                                "error firebase",
                                "Error al insertar persona: ${persona.id_persona}",
                                e
                            )
                        }
                    }
                }
                jobs.awaitAll() // Espera a que todas las operaciones terminen
            } else {
                Log.e("error firebase", "No hay personas para insertar")
            }
        }
    }

    //funciones personas
    suspend fun updatePersonaFirestorePersona(persona: persona,subColletion:String) {
        try {
            fireStore.collection("usuarios").document(uidUser).collection(subColletion)
                .document(persona.id_persona.toString())
                .set(persona)
                .await()
        } catch (e: Exception) {
            // Manejar errores de actualización en Firestore
        }
    }

    suspend fun deletePersonaFromFirestorePersona(persona: persona,subColletion:String) {
        try {
            fireStore.collection("usuarios").document(uidUser).collection(subColletion)
                .document(persona.id_persona.toString())
                .delete()
                .await()
        } catch (e: Exception) {
            // Manejar errores de eliminación en Firestore
        }

    }


    //funciones usuarios
    suspend fun getAllUsuariosDataCloud(subColletion: String): List<UsuariosRelation>{
       return withContext(Dispatchers.Default) {
            suspendCancellableCoroutine {continuation ->
                fireStore.collection("usuarios").document(uidUser).collection(subColletion).get()
                .addOnSuccessListener { snapshots ->
                    val usuariosRelation = snapshots.toObjects(UsuariosRelation::class.java)
                    continuation.resume(usuariosRelation)
                }
                .addOnFailureListener { exception ->
                    continuation.resumeWithException(exception)
                }
            }
       }
    }

    suspend fun insertUsuariosTodataCloud(usuarios: List<UsuariosRelation>, subColletion: String) {
        println("insertar UsuariosRelation desde room a data cloud")
        println()
        println("estas son las UsuariosRelation a insertar $usuarios")
        withContext(Dispatchers.IO) {
            if (usuarios.isNotEmpty()) {
                val jobs = usuarios.map { usuariosRelation ->
                    async {
                        try {
                            fireStore.collection("usuarios")
                                .document(uidUser)
                                .collection(subColletion)
                                .document(usuariosRelation.usuarios.id_usuario.toString())
                                .set(usuariosRelation)
                                .await()  // Utilizando await para esperar a que cada operación termine
                        } catch (e: Exception) {
                            Log.e(
                                "error firebase",
                                "Error al insertar UsuariosRelation: ${usuariosRelation.usuarios.id_usuario}",
                                e
                            )
                        }
                    }
                }
                jobs.awaitAll() // Espera a que todas las operaciones terminen
            } else {
                Log.e("error firebase", "No hay personas para UsuariosRelation")
            }
        }
    }

    //funciones inventarios
    suspend fun getAllInventarios( subCollection:String):List<InventarioModeloRelation> {
        return withContext(Dispatchers.Default) {
            suspendCancellableCoroutine {continuation ->
                fireStore.collection("usuarios").document(uidUser).collection(subCollection).get()
                    .addOnSuccessListener { snapshots ->
                        val InventarioRelation = snapshots.toObjects(InventarioModeloRelation::class.java)
                        continuation.resume(InventarioRelation)
                    }
                    .addOnFailureListener { exception ->
                        continuation.resumeWithException(exception)
                    }
            }
        }

    }

    suspend fun insertInventarioTodataCloud(Inventarios: List<InventarioModeloRelation>, subColletion: String) {
        println("insertar InventarioModeloRelation desde room a data cloud")
        println()
        println("estas son las InventarioModeloRelation a insertar $Inventarios")
        withContext(Dispatchers.IO) {
            if (Inventarios.isNotEmpty()) {
                val jobs = Inventarios.map { InventarioModeloRelation ->
                    async {
                        try {
                            fireStore.collection("usuarios")
                                .document(uidUser)
                                .collection(subColletion)
                                .document(InventarioModeloRelation.inventario.id_inventario.toString())
                                .set(InventarioModeloRelation)
                                .await()  // Utilizando await para esperar a que cada operación termine
                        } catch (e: Exception) {
                            Log.e(
                                "error firebase",
                                "Error al insertar InventarioModeloRelation: ${InventarioModeloRelation.inventario.id_inventario}",
                                e
                            )
                        }
                    }
                }
                jobs.awaitAll() // Espera a que todas las operaciones terminen
            } else {
                Log.e("error firebase", "No hay personas para InventarioModeloRelation")
            }
        }
    }

    //funciones administrador

    suspend fun uploadAdminDataToFirestore(admin: administrador) {

        // Convertir el objeto a un mapa de datos adecuado para Firestore
        val adminData = mapOf(
            "id_administrador" to admin.id_administrador,
            "nombre" to admin.nombre,
            "apellido" to admin.apellido,
            "correo" to admin.correo,
            "telefono" to admin.telefono,
            "clave" to admin.clave,
            "direccion_negocio" to admin.direccion_negocio,
            "nombre_negocio" to admin.nombre_negocio,
            "licencia" to admin.licencia,
            "fecha_compra" to admin.fecha_compra?.toString(),
            "fecha_caduca" to admin.fecha_caduca?.toString()
        )

        try {
            withContext(Dispatchers.IO) {
                fireStore.collection("usuarios").document(uidUser).set(adminData).await()
                println("Datos del administrador subidos a Firestore correctamente")
            }
        } catch (e: Exception) {
            e.printStackTrace()
            println("Error al subir los datos del administrador a Firestore: ${e.message}")
        }
    }

    suspend fun getDataAdminFromCloud(): administrador? {
        return withContext(Dispatchers.IO) {
            try {
                val documentSnapshot = fireStore.collection("usuarios").document(uidUser).get().await()
                if (documentSnapshot.exists()) {
                    documentSnapshot.toObject(administrador::class.java)
                } else {
                    null
                }
            } catch (e: Exception) {
                e.printStackTrace()
                null
            }!!
        }
    }

    override suspend fun registrarNuevoDevice(id: String, licencia:String) {
        try {
            val encontrada = encuentraDocLicencia(getAllLicencia(), licencia)
            if (encontrada?.get("estadoLicencia") == true) {
                val refe = encontrada.reference
                refe.update("idDevice", id).await()
            }
        }catch (e:Exception){
            println("No se pudo cambiar el dispositivo")
        }
    }



}




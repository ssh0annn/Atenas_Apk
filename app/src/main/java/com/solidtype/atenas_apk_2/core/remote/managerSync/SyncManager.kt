package com.solidtype.atenas_apk_2.core.remote.managerSync

import android.content.Context
import androidx.room.TypeConverter
import androidx.work.CoroutineWorker
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.google.firebase.Timestamp
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.dataObjects
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.firestore.toObject
import com.solidtype.atenas_apk_2.core.ddbb.ProductDataBase
import com.solidtype.atenas_apk_2.core.transacciones.daotransacciones.DaoTransacciones
import com.solidtype.atenas_apk_2.core.transacciones.modeloTransacciones.InventarioModeloRelation
import com.solidtype.atenas_apk_2.core.transacciones.modeloTransacciones.UsuariosRelation
import com.solidtype.atenas_apk_2.gestion_proveedores.data.remote.mediadorPersonaImpl
import com.solidtype.atenas_apk_2.gestion_usuarios.data.remote.mediadorUsuario
import com.solidtype.atenas_apk_2.gestion_usuarios.domain.modelo.usuario
import com.solidtype.atenas_apk_2.perfil_administrador.data.mediadorAdmin.mediadorAdmin
import com.solidtype.atenas_apk_2.perfil_administrador.domain.modelo.administrador
import com.solidtype.atenas_apk_2.products.data.remote.remoteProFB.mediator.mediatorInventario
import com.solidtype.atenas_apk_2.util.toDataListToMapList
import com.solidtype.atenas_apk_2.util.toTimestamp
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext
import java.time.LocalDate
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import javax.inject.Inject



class SyncWorker @Inject constructor (
    private val firestore: FirebaseFirestore,
    private val localDatabaseAtenas: ProductDataBase,
    @ApplicationContext private val context: Context,
    workerParams: WorkerParameters
) : CoroutineWorker(context, workerParams) {



    //configurar para que se reconozca el usuario actual
    private val id = "johan@labestia.com"

    override suspend fun doWork(): Result {
        return try {
            println("Entro a la funcion doWork")
            Result.success()
        } catch (e: Exception) {
            Result.retry()
        }
    }

    //sicronnizacion usuarios
    private suspend fun syncUserTrassaccions() = withContext(Dispatchers.Default) {
        val localUsuariosTransaciones = localDatabaseAtenas.DaoTicketsTransacciones.getAllUsuarios()
        val cloudUsuarios = firestore.collection("usuarios").document(id).collection("users").get().await()

        println("-- entre a la funcion")
        println()
        println("Esto son los datos de las transacciones locales de usuarios: $localUsuariosTransaciones <-- ")

        if (cloudUsuarios.documents.isNotEmpty()) {
            println("Estos son los usuarios que vienen de Firebase: ${cloudUsuarios.documents}")
        }


        if (cloudUsuarios.documents.isNotEmpty() && localUsuariosTransaciones.isEmpty()) {
            println("Entre a la primera condicion")
            cloudUsuarios.documents.forEach { document ->
                println("${document.toObject(UsuariosRelation::class.java)} <- Esto son los usuarios que van entrando en la base datos locales")
                val data = document.toObject(UsuariosRelation::class.java)
                println("$data <- Esto son los usuarios convertido a to object  que van entrando en la base datos locales")

                if (data != null) {
                    localDatabaseAtenas.DaoTicketsTransacciones.crearUsuario(data)
                } else {
                    println("Error al convertir el documento: ${document.id}")
                }
            }
        } else if (localUsuariosTransaciones.isNotEmpty() && cloudUsuarios.documents.isEmpty()) {
            println("Entre a la segunda condicion")
            localUsuariosTransaciones.forEach { usuarios ->
                firestore.collection("usuarios").document(id).collection("users")
                    .document(usuarios.usuarios.id_usuario.toString())
                    .set(usuarios)
                    .await()
            }
        } else {
            println("Ambos tienen datos, se procede a la sincronización detallada")
            println("salida de la funcion")
           // syncDetailed(cloudUsuarios.documents, localUsuariosTransaciones)
        }
    }

    //sicronnizacion inventario
    private suspend fun synInventarioTrassaccions() = withContext(Dispatchers.Default) {
        val localInventariosTransaciones = localDatabaseAtenas.DaoTicketsTransacciones.getAllInventario()
        val cloudInventarios = firestore.collection("usuarios").document(id).collection("inventario").get().await()

        println("-- entre a la funcion")
        println()
        println("Esto son los datos de las transacciones locales de inventarios: $localInventariosTransaciones <-- ")

        if (cloudInventarios.documents.isNotEmpty()) {
            println("Estos son los usuarios que vienen de Firebase: ${cloudInventarios.documents}")
        }


        if (cloudInventarios.documents.isNotEmpty() && localInventariosTransaciones.isEmpty()) {
            println("Entre a la primera condicion")
            cloudInventarios.documents.forEach { document ->
                println("Esto son los inventarios que van entrando en la base datos locales ${document} <- ")
                val data = document.toObject(InventarioModeloRelation::class.java)
                println(" Esto son los inventarios convertido a to object  que van entrando en la base datos locales $data <-")

                if (data != null) {
                    localDatabaseAtenas.DaoTicketsTransacciones.crearInventario(data)
                } else {
                    println("Error al convertir el documento: ${document.id}")
                }
            }
        } else if (localInventariosTransaciones.isNotEmpty() && cloudInventarios.documents.isEmpty()) {
            println("Entre a la segunda condicion")
            localInventariosTransaciones.forEach { inventario ->
                firestore.collection("usuarios").document(id).collection("inventario")
                    .document(inventario.inventario.id_inventario.toString())
                    .set(inventario)
                    .await()
            }
        } else {
            println("Ambos tienen datos, se procede a la sincronización detallada")
            println("salida de la funcion de inventario")
            //
        }
    }

//    private suspend fun syncAdmin() = withContext(Dispatchers.Default) {
//        println("Entré a la función syncAdmin")
//
//        val cloudAdminDoc = firestore.collection("usuarios").document(id)
//        val cloudAdminData = cloudAdminDoc.get().await()
//
//        val DBAdminData = localDatabaseAtenas.DaoTicketsTransacciones.getAdmins()
//
//        println("Estos son los datos locales de administradores: $DBAdminData")
//        println("Estos son los datos de administradores en Firestore: ${cloudAdminData.data}")
//
//        if (cloudAdminData.data.isNullOrEmpty() ) {
//            println("Entre a la primera condición")
//            DBAdminData.forEach {data->
//                val dataDbConverted = data.copy(
//                    fecha_caduca =  data.fecha_caduca.toTimestamp()
//                )
//
//            }
//        }
//        println("Salí de la función syncAdmin")
//    }




//    private suspend fun syncAdministrador() = withContext(Dispatchers.Default) {
//
//        val converter = Converter()  // Crear una instancia de Converter
//
//        println("Entré a la función syncAdmin")
//        val DBAdminData = localDatabaseAtenas.DaoTicketsTransacciones.getAdmins()
//        val cloudAdminDoc = firestore.collection("usuarios").document(id)
//        val cloudAdminData = cloudAdminDoc.get().await()
//
//        println("Estos son los datos locales de administradores: $DBAdminData")
//        println("Estos son los datos de administradores en Firestore: ${cloudAdminData.data}")
//
//        if (DBAdminData.isEmpty()) {
//            // Si no hay datos en la base de datos local, los traemos desde Firestore
//            val cloudData = cloudAdminData.toObject(administrador::class.java)?.let { admin ->
//                // Convertir las fechas de Timestamp a LocalDate utilizando el converter
//                admin.copy(
//                    fecha_compra = admin.fecha_compra?.let { converter.fromTimestamp(it as Timestamp) },
//                    fecha_caduca = admin.fecha_caduca?.let { converter.fromTimestamp(it as Timestamp) }
//                )
//            }
//
//            if (cloudData != null) {
//                localDatabaseAtenas.DaoTicketsTransacciones.addAdministradore(cloudData)
//                println("Datos de administrador añadidos localmente desde Firestore: $cloudData")
//            } else {
//                println("Error al convertir el documento de Firestore a objeto")
//            }
//        } else if (cloudAdminData.data.isNullOrEmpty()) {
//            // Si el documento está vacío, lo actualizamos con los datos locales
//            DBAdminData.forEach { data ->
//                // Convertir las fechas de LocalDate a Timestamp utilizando el converter
//                val dataToSend = data.copy(
//                    fecha_compra = data.fecha_compra?.let { converter.dateToTimestamp(it) } as? LocalDate,
//                    fecha_caduca = data.fecha_caduca?.let { converter.dateToTimestamp(it) } as? LocalDate
//                )
//
//                println("Insertando datos de administrador en Firestore: $dataToSend")
//                cloudAdminDoc.set(dataToSend).await()
//            }
//            println("Datos de administrador insertados en Firestore")
//        } else {
//            println("El documento en Firestore ya tiene datos definidos, no se realiza ninguna acción")
//        }
//
//        println("Salí de la función syncAdmin")
//    }


    class Converter {

        private val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")


        fun fromTimestamp(value: Timestamp?): LocalDate? {
            return value?.toDate()?.toInstant()?.atZone(ZoneId.systemDefault())?.toLocalDate()
        }


        fun dateToTimestamp(date: LocalDate?): Timestamp? {
            return date?.atStartOfDay(ZoneId.systemDefault())?.toInstant()?.let { Timestamp(it.epochSecond, 0) }
        }
    }
}

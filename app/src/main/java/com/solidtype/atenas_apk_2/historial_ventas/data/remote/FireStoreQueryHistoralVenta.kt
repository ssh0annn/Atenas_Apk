package com.solidtype.atenas_apk_2.historial_ventas.data.remote

import android.util.Log
import com.google.firebase.firestore.FirebaseFirestore
import com.solidtype.atenas_apk_2.historial_ventas.domain.model.HistorialVentaEntidad
import com.solidtype.atenas_apk_2.products.data.local.ProductDataBase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext
import javax.inject.Inject

class FireStoreQueryHistoralVenta @Inject constructor(
    private val firestore: FirebaseFirestore
) {

    suspend fun getDatatHistoralFB(uidUser:String): List<HistorialVentaEntidad>{
        return withContext(Dispatchers.IO){
            try {
                firestore.collection("usuarios")
                    .document(uidUser)
                    .collection("historial_venta")
                    .get()
                    .await()
                    .toObjects(HistorialVentaEntidad::class.java)

            } catch (e: Exception) {
                Log.e("Error sincronizacion", "Error al obtener los datos del historial de ventas en firebase: $e")
                return@withContext emptyList()
            }
        }
    }

    suspend fun insertFromFireBHistorialToLocal(historial: List<HistorialVentaEntidad>, dbLocal: ProductDataBase) {
        try {
            historial.forEach {
                dbLocal.HistorialDao.setHistorialVenta(it)
            }
        } catch (e: Exception) {
            Log.e("Error sincronizacion", "Error al insertar os datos del el historal a la base de datosa la base de datos local: $e")
        }
    }

    suspend fun syncLocalHistorialWithFirestore(localHistoral: List<HistorialVentaEntidad>, firestoreHistorial: List<HistorialVentaEntidad>,uidUser:String) {
        try {
            val batch = firestore.batch()
            localHistoral.forEach { localHistorial ->
                val remoteHistorial = firestoreHistorial.find { it.Codigo == localHistorial.Codigo }
                if (remoteHistorial == null || localHistorial != remoteHistorial) {
                    batch.set(
                        firestore.collection("usuarios")
                            .document(uidUser)
                            .collection("historial_venta")
                            .document(localHistorial.Codigo.toString()), localHistorial)
                }
            }
            batch.commit().await()
        } catch (e: Exception) {
            Log.e("Error sincronizacionr", "Error al sincronizar los datos del historial de venta de la base de datos local: $e")
        }
    }

    suspend fun deleteFirestoreHistoralNotInLocal(localHistoral: List<HistorialVentaEntidad>, firestoreHistorial: List<HistorialVentaEntidad>, uidUser:String) {
        try {
            val usersToDeleteInFirestore = firestoreHistorial.filterNot { firestoreUser ->
                localHistoral.any { it.Codigo == firestoreUser.Codigo }
            }
            usersToDeleteInFirestore.forEach { historial ->
                firestore.collection("usuarios")
                    .document(uidUser)
                    .collection("historial_venta")
                    .document(historial.Codigo.toString())
                    .delete().await()
            }
        } catch (e: Exception) {
            Log.e("Error sincronizacion", "Error al eliminar datos no existente en la base de datos local en firebase: $e")
        }
    }



}
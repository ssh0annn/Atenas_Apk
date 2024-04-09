package com.solidtype.atenas_apk_2.historial_ventas.data.remote.mediatorRemote

import android.util.Log
import com.google.firebase.firestore.auth.User
import com.solidtype.atenas_apk_2.historial_ventas.data.remote.FireStoreQueryHistoralVenta
import com.solidtype.atenas_apk_2.historial_ventas.domain.model.HistorialVentaEntidad
import com.solidtype.atenas_apk_2.products.data.local.ProductDataBase
import com.solidtype.atenas_apk_2.products.data.repositoryImpl.InventarioRepoImpl
import com.solidtype.atenas_apk_2.products.domain.model.ProductEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import javax.inject.Inject

class MediatorFBHistorialVentas @Inject constructor(
    private val dataBase: ProductDataBase,
    private val fireStoreQueryHistoralVenta: FireStoreQueryHistoralVenta
) {

    private val allHistorial: Flow<List<HistorialVentaEntidad>> = dataBase.HistorialDao.getHistorialVenta()
    private val uidUser: String = "VUxGubuZ1AZy7hXBvP8E"

    suspend fun backUpHistorialVentas(){
        try {
            allHistorial.collect{
                try {
                    // Verificar si la tabla de historial está vacía en la base de datos local
                    if (it.isEmpty()){
                        // Obtener todos el historail de Firestore
                        val fireStoreHistorial = fireStoreQueryHistoralVenta.getDatatHistoralFB(uidUser)
                        // Insertar todos el historial de Firestore en la tabla de historial en la base de datos local
                        fireStoreQueryHistoralVenta.insertFromFireBHistorialToLocal(fireStoreHistorial,dataBase)
                    }
                }catch (e:Exception){
                    // Manejar la excepción relacionada con la obtención de datos locales
                    Log.e("Sincronizacion", "Error al insertar los datos desde firebase a la base datos local: $e")
                }
            }
        }catch(e:Exception){
            // Manejar la excepción relacionada con la obtención de datos locales
            Log.e("Sincronizacion", "Error al obtener datos locales de historial: $e")
        }
    }

    suspend fun mirrorBDlocalHitorialVentas(){
        try {
            allHistorial.collect {localHistoral->
                val fireStoreHistorial = fireStoreQueryHistoralVenta.getDatatHistoralFB(uidUser)
                // Sincronizar los HISTORIAL locales con Firestore
                fireStoreQueryHistoralVenta.syncLocalHistorialWithFirestore(localHistoral,fireStoreHistorial,uidUser)
                fireStoreQueryHistoralVenta.deleteFirestoreHistoralNotInLocal(localHistoral, fireStoreHistorial,uidUser)
                Log.e("Sincronizacion", "Sincronización del historial exitosa con Firestore")

            }

        }catch (e:Exception){
            // Manejar la excepción relacionada con la conexión a Internet o la obtención de datos de Firestore
            Log.e("Sincronizacion", "Error durante la sincronización del historial de venta con Firestore: $e")
        }
    }

    suspend fun syncHistorial(){
        try {
            // Observar cambios db local
            allHistorial.collect{localHistoral->
               try {
                   // Verificar si la tabla de historial está vacía en la base de datos local
                   if (localHistoral.isEmpty()){
                       // Obtener todos el historail de Firestore
                       val fireStoreHistorial = fireStoreQueryHistoralVenta.getDatatHistoralFB(uidUser)
                       // Insertar todos el historail de Firestore en la tabla de historial en la base de datos local
                       fireStoreQueryHistoralVenta.insertFromFireBHistorialToLocal(fireStoreHistorial,dataBase)
                       Log.e("Sincronizacion", "Historial recuperado de Firebase")
                   }else{
                       // Obtener todos el historail de Firestore
                       val fireStoreHistorial = fireStoreQueryHistoralVenta.getDatatHistoralFB(uidUser)
                       // Sincronizar los HISTORIAL locales con Firestore
                       fireStoreQueryHistoralVenta.syncLocalHistorialWithFirestore(localHistoral,fireStoreHistorial,uidUser)
                       // Eliminar productos en Firestore que no existen en la base de datos local
                       fireStoreQueryHistoralVenta.deleteFirestoreHistoralNotInLocal(localHistoral, fireStoreHistorial,uidUser)
                       Log.e("Sincronizacion", "Sincronización del historial exitosa con Firestore")
                   }
               } catch (e: Exception) {
                   // Manejar la excepción relacionada con la conexión a Internet o la obtención de datos de Firestore
                   Log.e("Sincronizacion", "Error durante la sincronización del historial de venta con Firestore: $e")
               }
            }

        }catch (e: Exception) {
            // Manejar la excepción relacionada con la obtención de datos locales
            Log.e("Sincronizacion", "Error al obtener datos locales de historial: $e")
        }
    }
}
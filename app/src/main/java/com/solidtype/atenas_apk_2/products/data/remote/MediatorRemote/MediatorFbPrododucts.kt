package com.solidtype.atenas_apk_2.products.data.remote.MediatorRemote

import android.util.Log
import androidx.room.Insert
import com.google.firebase.firestore.FirebaseFirestore
import com.solidtype.atenas_apk_2.products.data.local.ProductDataBase
import com.solidtype.atenas_apk_2.products.data.remote.FireStoreQuerysProducts
import com.solidtype.atenas_apk_2.products.domain.model.ProductEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MediatorFbPrododucts @Inject constructor(
    private val dbLocal : ProductDataBase,
    private val FireQuery: FireStoreQuerysProducts
) {
    private val allProducts: Flow<List<ProductEntity>> = dbLocal.getProductDao.getProducts()

    //debes de replazar esto por el UID DEL USUARIO ACTUAL
    private val uidUser:String  = "0AVRrdADihSirvZNGBr2"

    suspend fun syncPro() {
        try {

            // Observar cambios db local
            allProducts.collect { localProducts ->
                try {
                    // Verificar si la tabla de usuarios está vacía en la base de datos local
                    if (localProducts.isEmpty()) {
                        // Obtener todos los usuarios de Firestore
                        val fireStoreProducts= FireQuery.getDatatProFB(uidUser)
                        // Insertar todos los usuarios de Firestore en la tabla de usuarios en la base de datos local
                        FireQuery.insertFromFireBUsersToLocal(fireStoreProducts,dbLocal)

                        Log.e("Sincronizacion", "Información recuperada de Firebase")
                    } else {
                        val fireStoreProducts= FireQuery.getDatatProFB(uidUser)
                        // Sincronizar los usuarios locales con Firestore
                        FireQuery.syncLocalUsersWithFirestore(localProducts, fireStoreProducts,uidUser)
                        // Eliminar usuarios en Firestore que no existen en la base de datos local
                        FireQuery.deleteFirestoreUsersNotInLocal(localProducts, fireStoreProducts,uidUser)
                        Log.e("Sincronizacion", "Sincronización exitosa con Firestore")
                    }
                } catch (e: Exception) {
                    // Manejar la excepción relacionada con la conexión a Internet o la obtención de datos de Firestore
                    Log.e("Sincronizacion", "Error durante la sincronización con Firestore: $e")
                }
            }
        } catch (e: Exception) {
            // Manejar la excepción relacionada con la obtención de datos locales
            Log.e("Sincronizacion", "Error al obtener datos locales: $e")
        }
    }

}
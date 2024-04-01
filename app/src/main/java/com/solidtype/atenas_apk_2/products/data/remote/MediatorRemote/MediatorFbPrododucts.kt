package com.solidtype.atenas_apk_2.products.data.remote.MediatorRemote

import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.solidtype.atenas_apk_2.products.data.local.ProductDataBase
import com.solidtype.atenas_apk_2.products.data.remote.FireStoreQuerysProducts
import com.solidtype.atenas_apk_2.products.domain.model.ProductEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
class MediatorFbPrododucts @Inject constructor(
    private val dbLocal : ProductDataBase,
    private val FireQuery: FireStoreQuerysProducts

) {
    private val allProducts: Flow<List<ProductEntity>> = dbLocal.ProductDao.getProducts()

    //debes de replazar esto por el UID DEL USUARIO ACTUAL
    private val uidpro: String = "VUxGubuZ1AZy7hXBvP8E"
    /*
    private val uidString:String  = authUid.currentUser!!.uid
    */

    suspend  fun sync() {
        try {
            // Observar cambios db local
            allProducts.collect { localProducts ->
                try {
                    // Verificar si la tabla de productos está vacía en la base de datos local
                    if (localProducts.isEmpty()) {
                        // Obtener todos los usuarios de Firestore
                        val fireStoreProducts= FireQuery.getDatatProFB(uidpro)
                        // Insertar todos los usuarios de Firestore en la tabla de usuarios en la base de datos local
                        FireQuery.insertFromFireBUsersToLocal(fireStoreProducts,dbLocal)
                        Log.e("Sincronizacion", "Información recuperada de Firebase")
                    } else {
                        val fireStoreProducts= FireQuery.getDatatProFB(uidpro)
                        Log.e("Sincronizacion","dato uid $uidpro")
                        // Sincronizar los usuarios locales con Firestore
                        FireQuery.syncLocalUsersWithFirestore(localProducts, fireStoreProducts,uidpro)
                        // Eliminar usuarios en Firestore que no existen en la base de datos local
                        FireQuery.deleteFirestoreUsersNotInLocal(localProducts, fireStoreProducts,uidpro)
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
/*
    suspend fun syncPro2() {
        try {
            // Observar cambios en la base de datos local
            allProducts.collect { allProducts ->
                try {
                    // Verificar si la tabla de usuarios está vacía en la base de datos local
                    if (allProducts.isEmpty()) {
                        // Obtener todos los usuarios de Firestore
                        val firestoreUsers =firestore.collection("usuarios").document(uidUser).collection("productos").get().await().toObjects(ProductEntity::class.java)
                        Log.e("Sincronizacion", "Error al obtener datos firestore: $firestore")
                        // Insertar todos los usuarios de Firestore en la tabla de usuarios en la base de datos local
                        firestoreUsers.forEach { user ->
                            dbLocal.getProductDao.insertPro(user)
                        }

                        Log.e("Sincronizacion", "Información recuperada de Firebase")
                    } else {
                        // Obtener todos los usuarios de Firestore
                        val firestoreUsers =firestore.collection("usuarios").document(uidUser).collection("productos").get().await().toObjects(ProductEntity::class.java)


                        // Sincronizar los usuarios locales con Firestore
                        val batch = firestore.batch()
                        allProducts.forEach { allProducts ->
                            val remoteUser = firestoreUsers.find { it.Code_Product == allProducts.Code_Product }
                            if (remoteUser == null || allProducts != remoteUser) {
                                // El usuario local no existe en Firestore o es diferente, agregarlo o actualizarlo
                                batch.set(
                                    firestore.collection("usuarios")
                                        .document(uidUser)
                                        .collection("productos")
                                        .document(allProducts.Code_Product.toString()), allProducts)
                            }
                        }

                        // Eliminar usuarios en Firestore que no existen en la base de datos local
                        val usersToDeleteInFirestore = firestoreUsers.filterNot { firestoreUser ->
                            allProducts.any { it.Code_Product == firestoreUser.Code_Product }
                        }
                        usersToDeleteInFirestore.forEach { pro ->
                            firestore.collection("usuarios")
                                .document(uidUser)
                                .collection("productos")
                                .document(pro.Code_Product.toString())
                                .delete().await()
                        }

                        // Aplicar los cambios en Firestore
                        batch.commit().await()
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
*/
}
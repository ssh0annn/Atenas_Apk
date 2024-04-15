package com.solidtype.atenas_apk_2.products.data.remote

import android.util.Log
import com.google.firebase.firestore.FirebaseFirestore
import com.solidtype.atenas_apk_2.core.ddbb.ProductDataBase
import com.solidtype.atenas_apk_2.products.domain.model.ProductEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext
import javax.inject.Inject
class FireStoreQuerysProducts @Inject constructor(
    private val firestore: FirebaseFirestore
) {

    suspend fun getDatatProFB(uidPro:String): List<ProductEntity>{
        return withContext(Dispatchers.IO){
            try {
                firestore.collection("usuarios")
                    .document(uidPro)
                    .collection("productos")
                    .get()
                    .await()
                    .toObjects(ProductEntity::class.java)

            } catch (e: Exception) {
                Log.e("Error sincronizacion", "Error al obtener usuarios de Firestore: $e")
                return@withContext emptyList()
            }
        }
    }
    suspend fun insertFromFireBUsersToLocal(users: List<ProductEntity>, dbLocal: ProductDataBase) {
        try {
            users.forEach {
                dbLocal.ProductDao.insertProduct(it)
            }
        } catch (e: Exception) {
            Log.e("Error sincronizacion", "Error al insertar usuarios en la base de datos local: $e")
        }
    }

    suspend fun syncLocalUsersWithFirestore(localUsers: List<ProductEntity>, firestoreUsers: List<ProductEntity>,uidPro:String) {
        try {
            val batch = firestore.batch()
            localUsers.forEach { localUser ->
                val remoteUser = firestoreUsers.find { it.Code_Product == localUser.Code_Product }
                if (remoteUser == null || localUser != remoteUser) {
                    batch.set(
                        firestore.collection("usuarios")
                            .document(uidPro)
                            .collection("productos")
                            .document(localUser.Code_Product.toString()), localUser)
                }
            }
            batch.commit().await()
        } catch (e: Exception) {
            Log.e("Error sincronizacionr", "Error al sincronizar usuarios locales con Firestore: $e")
        }
    }

    suspend fun deleteFirestoreUsersNotInLocal(localUsers: List<ProductEntity>, firestoreUsers: List<ProductEntity>,uidPro:String) {
        try {
            val usersToDeleteInFirestore = firestoreUsers.filterNot { firestoreUser ->
                localUsers.any { it.Code_Product == firestoreUser.Code_Product }
            }
            usersToDeleteInFirestore.forEach { user ->
                firestore.collection("usuarios")
                    .document(uidPro)
                    .collection("productos")
                    .document(user.Code_Product.toString())
                    .delete().await()
            }
        } catch (e: Exception) {
            Log.e("Error sincronizacion", "Error al eliminar usuarios de Firestore que no existen localmente: $e")
        }
    }

}
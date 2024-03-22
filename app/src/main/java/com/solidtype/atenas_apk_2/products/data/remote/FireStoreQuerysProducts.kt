package com.solidtype.atenas_apk_2.products.data.remote

import android.util.Log
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.auth.User
import com.solidtype.atenas_apk_2.products.data.local.ProductDataBase
import com.solidtype.atenas_apk_2.products.domain.model.ProductEntity
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class FireStoreQuerysProducts @Inject constructor() {

    //Instancia de firabase
    private val firestore = FirebaseFirestore.getInstance()

    suspend fun getDatatProFB(uidUser:String): List<ProductEntity>{
        return try {
            firestore.collection("usuarios")
                .document(uidUser)
                .collection("productos")
                .get()
                .await()
                .toObjects(ProductEntity::class.java)
        } catch (e: Exception) {
            Log.e("Error", "Error al obtener usuarios de Firestore: $e")
            emptyList()
        }
    }
    suspend fun insertFromFireBUsersToLocal(users: List<ProductEntity>, dbLocal:ProductDataBase) {
        try {
            users.forEach {
                dbLocal.getProductDao.insertProduct(it)
            }
        } catch (e: Exception) {
            Log.e("Error", "Error al insertar usuarios en la base de datos local: $e")
        }
    }

    suspend fun syncLocalUsersWithFirestore(localUsers: List<ProductEntity>, firestoreUsers: List<ProductEntity>,uidUser:String) {
        try {
            val batch = firestore.batch()
            localUsers.forEach { localUser ->
                val remoteUser = firestoreUsers.find { it.Code_Product == localUser.Code_Product }
                if (remoteUser == null || localUser != remoteUser) {
                    batch.set(
                        firestore.collection("usuarios")
                            .document(uidUser)
                            .collection("productos")
                            .document(localUser.Code_Product.toString()), localUser)
                }
            }
            batch.commit().await()
        } catch (e: Exception) {
            Log.e("Error", "Error al sincronizar usuarios locales con Firestore: $e")
        }
    }

    suspend fun deleteFirestoreUsersNotInLocal(localUsers: List<ProductEntity>, firestoreUsers: List<ProductEntity>,uidUser:String) {
        try {
            val usersToDeleteInFirestore = firestoreUsers.filterNot { firestoreUser ->
                localUsers.any { it.Code_Product == firestoreUser.Code_Product }
            }
            usersToDeleteInFirestore.forEach { user ->
                firestore.collection("usuarios")
                    .document(uidUser)
                    .collection("productos")
                    .document(user.Code_Product.toString())
                    .delete().await()
            }
        } catch (e: Exception) {
            Log.e("Error", "Error al eliminar usuarios de Firestore que no existen localmente: $e")
        }
    }

}
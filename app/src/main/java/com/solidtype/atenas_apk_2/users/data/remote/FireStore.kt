package com.solidtype.atenas_apk_2.users.data.remote

import com.google.android.gms.tasks.Task
import com.google.android.gms.tasks.Tasks
import com.google.firebase.Firebase
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.QuerySnapshot
import com.google.firebase.firestore.SetOptions
import com.google.firebase.firestore.firestore

class FireStore {

    private val db =  Firebase.firestore
    private val usuariosCollection = db.collection("usuarios")
     suspend fun actualizarDocumento(modelo: Modelo): Boolean {
        try {
            val idLicencia = "IJLPfiyOTIrrLjfPFZPO"

            // Verifica si el documento ya existe
            if (!existeDocumento(idLicencia)) {
                println("Resultado de  existeDocumento: ${existeDocumento(idLicencia)}")
                // El documento existe, actualiza el documento existente
                val updateTask: Task<Void> = usuariosCollection.document(idLicencia)
                    .set(modelo, SetOptions.merge())
                Tasks.await(updateTask)
                println("resultado del updateTask: $updateTask")
                return true // Operación exitosa
            } else {
                println("hay un else: no se por que")
                return false // El documento no existe, no se realiza ninguna operación
            }
        } catch (e: Exception) {
            println("hay un else: no se por que: depurando: ${e.message}")
            return false // Ocurrió un error
        }
    }
   private suspend  fun existeDocumento(id: String): Boolean {
        return try {
            val task: Task<QuerySnapshot> = usuariosCollection.whereEqualTo("id", "IJLPfiyOTIrrLjfPFZPO" ).get()
            val result = Tasks.await(task)
            result.documents.isNotEmpty()
        } catch (e: Exception) {
            println("Error en la consulta: ${e.message}")
            false
        }finally {
            println("Aqui todo bien en la funcion Documento")
        }
    }

}
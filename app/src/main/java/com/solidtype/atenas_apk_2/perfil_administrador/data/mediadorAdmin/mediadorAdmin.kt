package com.solidtype.atenas_apk_2.perfil_administrador.data.mediadorAdmin

import com.google.firebase.Timestamp
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.QuerySnapshot
import com.solidtype.atenas_apk_2.core.remote.dataCloud.DataCloudImpl
import com.solidtype.atenas_apk_2.perfil_administrador.data.ddLocalAdmin.AdminDataBaseImpl
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import javax.inject.Inject

class mediadorAdmin @Inject constructor(
    private val dataCloudImpl: DataCloudImpl,
    private val adminDB: AdminDataBaseImpl) {

    suspend fun syncDataAdmin(){

        val dataAdminCLoud = documentSnapshotToList(dataCloudImpl.getallDataDocumentsAdmin())
        println("datos de administradores que vienen de firebase $dataAdminCLoud <--------------------------------")
        if (adminDB.getAllAdministrador().isEmpty() ) {
            adminDB.insertAdmin(dataAdminCLoud)
        }else if(dataAdminCLoud.isEmpty()){
            adminDB.getAllAdministrador().forEach {
                dataCloudImpl.insertAdministradorInFirestore(it)
            }
        }else{
            //si se cumplen las dos procedemos hacer un sincroniacion mas detallas
        }



//        println("entre a la funcion admin syncs")
//        val dataAdminCloud = dataCloudImpl.getDataAdminFromCloud()
//        val dataAdminDbLocal = adminDB.getAllAdministrador()
//
//        println("datos de administradores que vienen de firebase $dataAdminCloud <--------------------------------")
//
//        if (dataAdminCloud != null) {
//            if (dataAdminCloud.correo == null) {
//                dataAdminDbLocal.forEach {
//                    dataCloudImpl.uploadAdminDataToFirestore(it)
//                }
//            }else if (dataAdminDbLocal.isEmpty()){
//                adminDB.insertOneAdministrador(dataAdminCloud)
//            }else{
//                //si nunga de las condiciones se cumplio significa que hay datos en ambas
//            }
//        }
    }



    private fun documentSnapshotToList(administrador: DocumentSnapshot?): List<String> {
        val admin = mutableListOf<String>()

            //oreden como debe recibir la funcion de conversion
        val fieldOrder = listOf(
            "id_administrador",
            "nombre",
            "apellido",
            "correo",
            "telefono",
            "clave",
            "direccion_negocio",
            "nombre_negocio",
            "licencia",
            "fecha_compra",
            "fecha_caduca",
            "estado"

        )

        if (administrador != null) {
            fieldOrder.forEach { field ->
                val value = administrador.get(field)
                when (value) {
                    is String -> admin.add(value)
                    is Number -> admin.add(value.toString())
                    is Boolean -> admin.add(value.toString())
                    is Timestamp -> {
                        val localDate = value.toDate().toInstant()
                            .atZone(ZoneId.systemDefault())
                            .toLocalDate()
                        val formattedDate = localDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))
                        admin.add(formattedDate)
                    }
                    is List<*> -> value.forEach { element ->
                        if (element is String) {
                            admin.add(element)
                        } else if (element is Number) {
                            admin.add(element.toString())
                        } else if (element is Boolean) {
                            admin.add(element.toString())
                        } else if (element is Timestamp) {
                            val localDate = element.toDate().toInstant()
                                .atZone(ZoneId.systemDefault())
                                .toLocalDate()
                            val formattedDate = localDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))
                            admin.add(formattedDate)
                        }
                    }
                    is Map<*, *> -> value.values.forEach { element ->
                        if (element is String) {
                            admin.add(element)
                        } else if (element is Number) {
                            admin.add(element.toString())
                        } else if (element is Boolean) {
                            admin.add(element.toString())
                        } else if (element is Timestamp) {
                            val localDate = element.toDate().toInstant()
                                .atZone(ZoneId.systemDefault())
                                .toLocalDate()
                            val formattedDate = localDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))
                            admin.add(formattedDate)
                        }
                    }
                    // Agrega otros casos según necesites (por ejemplo, para otros tipos de datos)
                }
            }
        }
        return admin
    }
}
package com.solidtype.atenas_apk_2.gestion_usuarios.data.remote

import com.solidtype.atenas_apk_2.core.remote.dataCloud.DataCloud
import com.solidtype.atenas_apk_2.core.remote.dataCloud.DataCloudImpl
import com.solidtype.atenas_apk_2.core.transacciones.modeloTransacciones.UsuariosRelation
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class mediadorUsuario @Inject constructor(
    private val dbLocalUsuario: dbLocalUsuarioImpl,
    private val dataCloud: DataCloudImpl
) {
    private val subCollection = "users"

    suspend fun syncUsuario(){
        println("-- entre a al invoke de usuarios")
        val localUsuariosTransaciones = dbLocalUsuario.getAllUsuarios()
        val dataCloudUsuariosDataCloud = dataCloud.getAllUsuariosDataCloud(subCollection)
        println("Esto son los datos de las transacciones locales de usuarios: $localUsuariosTransaciones <-- ")
        println("Esto son los datos de la db cloud de usuarios: $dataCloudUsuariosDataCloud <-- ")
        if (localUsuariosTransaciones.isEmpty()) {
            println("Entre a la primera condicion")
            dbLocalUsuario.insertAllUsuarios(dataCloudUsuariosDataCloud)
        } else if (dataCloudUsuariosDataCloud.isEmpty()) {
            println("Entre a la segunda condicion")
            dataCloud.insertUsuariosTodataCloud(localUsuariosTransaciones,subCollection)
        } else {
            println("Ambos tienen datos, se procede a la sincronización detallada")
            println("salida de la funcion")
        }
    }
}
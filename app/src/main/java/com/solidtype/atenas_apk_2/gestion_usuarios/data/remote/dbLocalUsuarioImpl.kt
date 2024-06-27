package com.solidtype.atenas_apk_2.gestion_usuarios.data.remote

import com.solidtype.atenas_apk_2.core.transacciones.daoTransacciones.DaoTransacciones
import com.solidtype.atenas_apk_2.core.transacciones.modeloTransacciones.UsuariosRelation
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import javax.inject.Inject

class dbLocalUsuarioImpl @Inject constructor(
    private val dao: DaoTransacciones
) {

    fun entityToString(dato:List<UsuariosRelation>):MutableList<Map<String,Map<String,Any>>>{
        val collecionTicket:MutableList<Map<String,Map<String,Any>>> = mutableListOf()
        if (dato.isNotEmpty()){
            dato.forEach {
                var oneUsuario = mutableMapOf<String,Map<String,Any>>()
                var mapUsuario = mutableMapOf<String,Any>()
                var mapRollUsuario = mutableMapOf<String,Any>()


                // optimizar los entities to map con funciones de exteciones
                mapUsuario = mutableMapOf(
                    "id_usuario" to it.usuarios.id_usuario,
                    "id_roll_usuario" to it.usuarios.id_roll_usuario,
                    "nombre" to it.usuarios.nombre,
                    "apellido" to it.usuarios.apellido,
                    "email" to it.usuarios.email,
                    "clave" to it.usuarios.clave,
                    "telefono" to it.usuarios.telefono,
                    "estado" to it.usuarios.estado,

                )

                mapRollUsuario = mutableMapOf(
                    "id_venta" to it.roll_usuario.id_roll_usuario,
                    "nombre" to it.roll_usuario.nombre,
                    "descripcion" to it.roll_usuario.descripcion,
                    "estado" to it.roll_usuario.estado,

                    )


                // optimizar los entities to map con funciones de exteciones

                oneUsuario["usuario"] = mapUsuario
                oneUsuario["roll_usuario"] = mapRollUsuario
                collecionTicket.add(mapOf(it.usuarios.id_usuario.toString() to oneUsuario))
            }
        }
        return  collecionTicket
    }







    private suspend fun getAllUsuarios(): List<UsuariosRelation>{
            return coroutineScope {
                val listUsuarios = async { dao.getAllUsuarios() }
                return@coroutineScope listUsuarios.await()
            }
    }

}
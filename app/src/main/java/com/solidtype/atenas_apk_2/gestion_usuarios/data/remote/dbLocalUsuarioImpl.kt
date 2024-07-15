package com.solidtype.atenas_apk_2.gestion_usuarios.data.remote

import com.solidtype.atenas_apk_2.core.transacciones.daotransacciones.DaoTransacciones
import com.solidtype.atenas_apk_2.core.transacciones.modeloTransacciones.UsuariosRelation
import com.solidtype.atenas_apk_2.gestion_proveedores.data.persona
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
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


     suspend fun getAllUsuarios(): List<UsuariosRelation>{
         return withContext(Dispatchers.Default){
             dao.getAllUsuarios()
         }
    }

//    suspend fun insertAllUsuarios(usuarios: List<UsuariosRelation>){
//        if (usuarios.isNotEmpty()){
//            withContext(Dispatchers.Default){
//                usuarios.forEach {
//                    dao.crearUsuario(it)
//                }
//            }
//        }
//    }


    suspend fun insertAllUsuarios(usuarios: List<UsuariosRelation>){
        if (usuarios.isNotEmpty()){
            val listaRoles= usuarios.map {
                it.roll_usuario
            }
            val listaUsuarios = usuarios.map {
                it.usuarios
            }

            val supervisorJob = SupervisorJob()
            withContext(supervisorJob){
                val jobRollUsuarios = launch(Dispatchers.Default){
                    dao.addRollUsuarios(listaRoles)
                }

                jobRollUsuarios.join()

                val jobUsuarios = launch(Dispatchers.Default){
                    dao.addUsuarios(listaUsuarios)
                }

                jobUsuarios.join()
            }

        }
    }
}
package com.solidtype.atenas_apk_2.perfil_administrador.data.ddLocalAdmin

import com.solidtype.atenas_apk_2.core.transacciones.daotransacciones.DaoTransacciones
import com.solidtype.atenas_apk_2.perfil_administrador.data.administradorDao
import com.solidtype.atenas_apk_2.perfil_administrador.domain.modelo.administrador
import com.solidtype.atenas_apk_2.products.domain.model.ProductEntity
import com.solidtype.atenas_apk_2.util.toLocalDate
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class AdminDataBaseImpl @Inject constructor(
    private val daoAdmin: DaoTransacciones

 ){

        suspend fun insertOneAdministrador(admin:administrador){
            daoAdmin.addOneAdmin(admin)
        }

        suspend fun getAllAdministrador():List<administrador>{
            return withContext(Dispatchers.Default){
                daoAdmin.getAdmins()
            }
        }

       suspend fun insertAllAdmins(admin:List<administrador>){
            withContext(Dispatchers.Default){
                admin.forEach {
                    daoAdmin.addAdministradore(it)
                }
            }
       }

    private fun entityConvert(it: List<String>): administrador {
        if (it.size == 12) {
            try {

                return administrador(
                    id_administrador = it[0].toLong(),
                    nombre = it[1],
                    apellido = it[2],
                    correo = it[3],
                    telefono = it[4],
                    clave = it[5],
                    direccion_negocio = it[6],
                    nombre_negocio = it[7],
                    licencia = it[8],
                    fecha_compra = it[9].toLocalDate("yyyy-MM-dd"),
                    fecha_caduca = it[10].toLocalDate("yyyy-MM-dd"),
                    estado = it[11].toBoolean()
                )
            } catch (e: Exception) {
                println("Este es la razon lista: $it, size ${it.size}")
                throw Exception("El tipo de la lista no es compatible con la Entity administrador $e")

            }
        }
        throw Exception("El tamaño de la lista entregada no es compatible")
    }

     suspend fun insertAdmin(dataToinsert: List<String>){
        try {
            insertOneAdministrador(entityConvert(dataToinsert))
        }catch (e:Exception ){
            throw Exception("No se puedo insertar el admin linea 67")
        }
    }
 }

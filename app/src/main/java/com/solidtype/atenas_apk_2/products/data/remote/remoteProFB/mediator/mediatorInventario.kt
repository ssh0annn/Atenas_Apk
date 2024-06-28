package com.solidtype.atenas_apk_2.products.data.remote.remoteProFB.mediator

import com.google.firebase.firestore.QuerySnapshot
import com.solidtype.atenas_apk_2.core.transacciones.daoTransacciones.DaoTransacciones
import com.solidtype.atenas_apk_2.core.remote.dataCloud.DataCloud
import com.solidtype.atenas_apk_2.facturacion.data.local.DetalleDBImpl
import com.solidtype.atenas_apk_2.gestion_tickets.data.remote.DBtickets.DbTicketsImpl
import com.solidtype.atenas_apk_2.gestion_usuarios.data.remote.dbLocalUsuarioImpl
import com.solidtype.atenas_apk_2.historial_ventas.data.remoteHistoVentaFB.QueryDBHistorial.DBlocalImpl
import com.solidtype.atenas_apk_2.products.data.remote.remoteProFB.dataDb.DataDbProducts.QueryDBLocalInventarioImpl
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.withContext
import javax.inject.Inject

class mediatorInventario @Inject constructor(
    private val dbInventario: QueryDBLocalInventarioImpl,
    private val dataFirebase: DataCloud,
    private val  daoTransacciones: DaoTransacciones,
    private val dbTickets: DbTicketsImpl,
    private val dbVentas: DBlocalImpl,
    private val detalleVenta: DetalleDBImpl,
    private val usuariDB: dbLocalUsuarioImpl,
){
   suspend fun getDataBase(){
        withContext(Dispatchers.IO){/*
            val data = dbInventario.entityToString(daoTransacciones.getAllInventario())
            println("adentro del mediator")
            println(data)
            data.forEachIndexed { index, ticketRelation ->
                println("InventarioModeloRelation ${index + 1}:")
                println(ticketRelation)
                println()
            }
            dataFirebase.insertAllToCloud3("inventario",data,"inventario")
            val dataCon = dbTickets.entityToString(daoTransacciones.getAllTickets())
            println(dataCon)
            dataCon.forEachIndexed { index, ticketRelation ->
                println("InventarioModeloRelation ${index + 1}:")
                println(ticketRelation)
                println()
            }

            dataFirebase.insertAllToCloud2("ticket",dataCon,"transationTicket")

             val dataVenta = dbVentas.entityToString(daoTransacciones.getAllventas())
            println(dataVenta)
            dataVenta.forEachIndexed { index, ticketRelation ->
                println("ventasModeloRelation ${index + 1}:")
                println(ticketRelation)
                println()
            }

            dataFirebase.insertAllToCloudVentas("ventas",dataVenta,"transationTicket")


            val dataVenta = detalleVenta.entityToString(daoTransacciones.getAllDetalleVenta())
            println(dataVenta)
            dataVenta.forEachIndexed { index, ticketRelation ->
                println("ventasModeloRelation ${index + 1}:")
                println(ticketRelation)
                println()
            }

            dataFirebase.insertAllToCloudDetalleVenta("detalle_venta",dataVenta,"transationTicket")



        */


//            val dataUsuario = usuariDB.entityToString(daoTransacciones.getAllUsuarios())
//            println(dataUsuario)
//            dataUsuario.forEachIndexed { index, ticketRelation ->
//                println("ventasModeloRelation ${index + 1}:")
//                println(ticketRelation)
//                println()
//            }
//
//            dataFirebase.insertAllToCloudUsuarios("usuarios",dataUsuario,"transationTicket")

//            val dataFirebaseCloud = dataFirebase.getallData("inventario")
//            println(dataFirebase)
//            println("verificando datos")
//            println()
//            println()
//            println("Antes de viendo")
//            snapshottoMap(dataFirebaseCloud)
//            println("despues de viendo")
//            println()
//            println()
//            println()

                val dataFirebaseCloud = dataFirebase.getallData("inventario")
                val re =snapshottoMap(dataFirebaseCloud)
                dbInventario.InsertTrassaccionInventario(re)

                println("entre al asyc")
                println(dataFirebaseCloud)
                println(re)
        }
   }

    private fun viendo(data:QuerySnapshot?){
        if(data != null){
            println("datos firebase antes la conversio")
            println(data.documents)
            data.documents.forEach {
              it.data
                println("datos , esto es it.data ${it.data}")
                println("datos firebase despues la conversio")
                println(it.data?.let { map -> println("este es el mapa con la key persona :  ${map["Persona"]}") })
            }
        }
    }


    private fun snapshottoMap(firebaseData:QuerySnapshot?):MutableList<Map<String,Map<String,Any>>>{
        val listadoMapa:MutableList<Map<String,Map<String,Any>>> = mutableListOf()

        if (firebaseData!!.isEmpty){
            println("$ -- >>>firebaseData --> no contiene datos")
        }else{
            firebaseData.documents.forEach {//recoriendo la collection de inventario por cada documento
                var mapOneInventory = mutableMapOf<String,Map<String,Any>>()
                var mapPersona = mutableMapOf<String,Any>()
                var mapInventario = mutableMapOf<String,Any>()
                var mapCategoria = mutableMapOf<String,Any>()

                it.data.let{map->
                    val persona =  map?.get("Persona")
                    val categorias  =  map?.get("Categoria")
                    val inventario =  map?.get("Inventario")

                    when(persona){
                        is String -> mapPersona["id_persona"] = persona
                        is Map<*,*> -> {
                            mapPersona["id_persona"] = (persona)["id_persona"] as Long
                            mapPersona["direccion"] = (persona)["direccion"] as String
                            mapPersona["documento"] = (persona)["documento"] as String
                            mapPersona["email"] = (persona)["email"] as String
                            mapPersona["estado"] = (persona)["estado"] as Boolean
                            mapPersona["nombre"] = (persona)["nombre"] as String
                            mapPersona["telefono"] = (persona)["telefono"] as String
                            mapPersona["tipo_documento"] = (persona)["tipo_documento"] as String
                            mapPersona["tipo_persona"] = (persona)["tipo_persona"] as String
                        }
                    }

                    when(categorias){
                        is String -> mapCategoria["id_categoria"] = categorias
                        is Map<*,*> -> {
                            mapCategoria["descripcion"] = (categorias)["descripcion"] as String
                            mapCategoria["estado"] = (categorias)["estado"] as Boolean
                            mapCategoria["id_categoria"] = (categorias)["id_categoria"] as Long
                            mapCategoria["nombre"] = (categorias)["nombre"] as String
                        }
                    }

                    when(inventario){
                        is String -> mapInventario["id_inventario"] = inventario
                        is Map<*,*> -> {
                            mapInventario["cantidad"] = (inventario)["cantidad"] as Any
                            mapInventario["descripcion"] = (inventario)["descripcion"] as String
                            mapInventario["estado"] = (inventario)["estado"] as Boolean
                            mapInventario["id_categoria"] = (inventario)["id_categoria"] as Long
                            mapInventario["id_inventario"] = (inventario)["id_inventario"] as Long
                            mapInventario["id_proveedor"] = (inventario)["id_proveedor"] as Long
                            mapInventario["nombre"] = (inventario)["nombre"] as String
                            mapInventario["impuestos"] = (inventario)["impuestos"] as Double
                            mapInventario["marca"] = (inventario)["marca"] as String
                            mapInventario["modelo"] = (inventario)["modelo"] as String
                            mapInventario["precio_compra"] = (inventario)["precio_compra"] as Double
                            mapInventario["precio_venta"] = (inventario)["precio_venta"] as Double
                        }
                    }


                }

                listadoMapa.add(mapOf("Persona" to mapPersona))
                listadoMapa.add(mapOf("Categoria" to mapCategoria))
                listadoMapa.add(mapOf("Inventario" to mapInventario))

            }
            println("$ -- >>>firebaseData --> contiene datos")
            println(listadoMapa)
            println("$ -- >>>datamap --> contiene datos")
        }

        return listadoMapa

    }

}
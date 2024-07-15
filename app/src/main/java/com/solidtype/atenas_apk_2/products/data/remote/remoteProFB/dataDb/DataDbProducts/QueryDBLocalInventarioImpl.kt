package com.solidtype.atenas_apk_2.products.data.remote.remoteProFB.dataDb.DataDbProducts

import com.solidtype.atenas_apk_2.core.transacciones.daotransacciones.DaoTransacciones
import com.solidtype.atenas_apk_2.core.transacciones.modeloTransacciones.InventarioModeloRelation
import com.solidtype.atenas_apk_2.gestion_proveedores.data.persona
import com.solidtype.atenas_apk_2.products.domain.model.actualizacion.categoria
import com.solidtype.atenas_apk_2.products.domain.model.actualizacion.inventario
import com.solidtype.atenas_apk_2.util.toDataClassToMap
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class QueryDBLocalInventarioImpl @Inject constructor(
    private val Dao: DaoTransacciones
) {
    /**
     * @return  List<MutableList<Map<String,Map<String,Any>>>>
     * @funcion: captura todos los datos de la tabla inventario y los debuelve en una lista de mapas con con mapas para la conversion a MutableList<Map<String,Map<String,Any>>>.
     */

    fun entityToString(dato:List<InventarioModeloRelation>):MutableList<Map<String,Map<String,Any>>>{
        val collecionTicket:MutableList<Map<String,Map<String,Any>>> = mutableListOf()
        if (dato.isNotEmpty()){
            dato.forEach {
                collecionTicket.add(mapOf(it.inventario.id_inventario.toString() to mutableMapOf("Inventario" to it.inventario.toDataClassToMap(),
                    "Categoria" to it.categoria.toDataClassToMap(), "Persona" to it.provedor.toDataClassToMap(),)))

            }
        }
        return collecionTicket
    }


    /**
     * @return  List<InventarioModeloRelation>
     * @funcion: captura todos los datos de la tabla inventario y los debuelve en una lista de objetos InventarioModeloRelation.
     */
     suspend fun datosInventoryRelations(): List<InventarioModeloRelation> {
        return coroutineScope {
            val listInventario = async { Dao.getAllInventario() }
            return@coroutineScope listInventario.await()
        }
    }

//    suspend fun insertInventoryRelations( inventarios: List<InventarioModeloRelation> ){
//        println("datos inventarios en la funcio de inserccion -> $inventarios")
//        if (inventarios.isNotEmpty()){
//
//                val listaCategorias = inventarios.map { it.categoria }
//                val listaPersonas = inventarios.map { it.provedor }
//               val listInventario = inventarios.map { it.inventario }
//                Dao.addCategorias(listaCategorias)
//                Dao.addPersonas(listaPersonas)
//                Dao.addInventarios(listInventario)
//
//        }
//    }

    suspend fun insertInventoryRelations(inventarios: List<InventarioModeloRelation>) {
        println("Datos de inventarios en la función de inserción -> $inventarios")

        if (inventarios.isNotEmpty()) {
            val listaCategorias = inventarios.map { it.categoria }
            val listaPersonas = inventarios.map { it.provedor }
            val listInventario = inventarios.map { it.inventario }

            val supervisorJob = SupervisorJob()

            // Utilizamos el contexto del supervisorJob
            withContext(supervisorJob) {
                // Lanzamos un sub-job para agregar categorías
                val jobCategorias = launch(Dispatchers.Default) {
                    Dao.addCategorias(listaCategorias)
                }

                // Esperamos a que termine el sub-job de categorías
                jobCategorias.join()

                // Lanzamos un sub-job para agregar personas
                val jobPersonas = launch(Dispatchers.Default) {
                    Dao.addPersonas(listaPersonas)
                }

                // Esperamos a que termine el sub-job de personas
                jobPersonas.join()

                // Lanzamos un sub-job para agregar inventarios
                val jobInventarios = launch(Dispatchers.Default) {
                    Dao.addInventarios(listInventario)
                }

                // Esperamos a que termine el sub-job de inventarios
                jobInventarios.join()
            }
        }
    }


    private fun listasMaptoTrasnsaccion(dataOfFirebase: MutableList<Map<String, Map<String, Any>>>): List<InventarioModeloRelation?> {
        var listDatosRelacionados: MutableList<InventarioModeloRelation?> = mutableListOf()

        val list: List<String> = listOf("Inventario", "Categoria", "Persona")


        val listaInventarios: MutableList<inventario> = mutableListOf()
        val listaPersona: MutableList<persona> = mutableListOf()
        val listaCategorias: MutableList<categoria> = mutableListOf()

        //primero ropo la lista
        var contador = 0
        while (dataOfFirebase.size > contador) {
            // agarro un mapa
            val mapa = dataOfFirebase[contador]
            //agarro una clave
            for (llaves in mapa.keys) {

                //agarro el mapa de la clave

                //identidico a que data class pertenece esa clave y ese mapa
                if (llaves == list[0]) {
                    println("este es un inventario ${llaves} ${mapa[llaves] as Map<String, Any>}")
                    val inventario = mapa[llaves] as Map<String, Any>
                    val pendejo = inventario["cantidad"] as? Long


                    if (pendejo != null) {
                        listaInventarios.add(
                            inventario(
                                id_inventario = inventario["id_inventario"] as? Long ?: 0L,
                                id_categoria = inventario["id_categoria"] as? Long ?: 0L,
                                id_proveedor = inventario["id_proveedor"] as? Long ?: 0L,
                                nombre = inventario["nombre"] as String,
                                marca = inventario["marca"] as String,
                                modelo = inventario["modelo"] as String,
                                cantidad = pendejo.toInt(),
                                precio_compra = inventario["precio_compra"] as Double,
                                precio_venta = inventario["precio_venta"] as Double,
                                impuesto = inventario["impuestos"] as Double,
                                descripcion = inventario["descripcion"] as? String?,
                                estado = inventario["estado"] as Boolean
                            )
                        )
                    }


                } else if (llaves == list[1]) {
                    println("este es una categoria ${llaves} ${mapa[llaves] as Map<String, Any>}")
                    val catego = mapa[llaves] as Map<String, Any>
                    listaCategorias.add(
                        categoria(
                            id_categoria = catego["id_categoria"] as? Long ?: 0L,
                            nombre = catego["nombre"] as String,
                            descripcion = catego["descripcion"] as String,
                            estado = catego["estado"] as Boolean
                        )
                    )


                } else if (llaves == list[2]) {
                    println("este es una persona ${llaves} ${mapa[llaves] as Map<String, Any>}")
                    val personas = mapa[llaves] as Map<String, Any>
                    listaPersona.add(
                        persona(
                            id_persona = personas["id_persona"] as Long,
                            tipo_persona = personas["tipo_persona"] as? String?,
                            nombre = personas["nombre"] as String?,
                            tipo_documento = personas["tipo_documento"] as? String?,
                            documento = personas["documento"] as? String?,
                            direccion = personas["direccion"] as? String?,
                            telefono = personas["telefono"] as? String?,
                            email = personas["email"] as? String?,
                            estado = personas["estado"] as Boolean

                        )
                    )
                }
            }
            contador++

        }

        //una vez identificado lo tenemos que agregar en su respecrivo tipo de dato
        println("Fuera del bucle yo impremo tres lineas ... pendejo ")
        println("*********************************************************")
        println("INventarios: $listaInventarios")
        println("listaPersona: $listaPersona")
        println("listaCategorias: $listaCategorias")
        println("*********************************************************")

        return listaInventarios.map{ inventario ->
            listaCategorias.find {it.id_categoria == inventario.id_categoria}?.let {
                listaPersona.find{ it.id_persona == inventario.id_proveedor}?.let { it1 ->
                    InventarioModeloRelation(
                        inventario =inventario,
                        provedor = it1,
                        categoria = it
                    )
                }
            }
        }.toMutableList()
    }


//     private fun listasMaptoTrasnsaccion(dataOfFirebase: MutableList<Map<String,Map<String,Any>>>):List<InventarioModeloRelation?>{
//        val listInventario : MutableList<InventarioModeloRelation?> = mutableListOf()
//
//
//         println("dataOfFirebase antes de la conversion ->> $dataOfFirebase")
//
//        dataOfFirebase.forEach {
//            var mapPersona : persona? = null
//            var mapInventario : inventario? = null
//            var mapCategoria :categoria? = null
//
//           for (i in it.keys){
//              when(i){
//                  "Persona" -> {
//                     mapPersona = persona(
//                          id_persona =  it[i]?.get("id_persona") as Long,
//                          tipo_persona =  it[i]?.get("tipo_persona") as String,
//                          nombre =  it[i]?.get("nombre") as String,
//                          tipo_documento =  it[i]?.get("tipo_documento") as String,
//                          documento =  it[i]?.get("documento") as String,
//                          direccion =  it[i]?.get("direccion") as String,
//                          telefono =  it[i]?.get("telefono") as String,
//                          email =  it[i]?.get("email") as String,
//                          estado =  it[i]?.get("estado") as Boolean,
//                      )
//                      println("mapPersona ->> $mapPersona")
//                  }
//
//                  "Inventario" -> {
//                      mapInventario =  inventario(
//                          id_inventario = it[i]?.get("id_inventario") as Long,
//                          id_categoria = it[i]?.get("id_categoria") as Long,
//                          id_proveedor = it[i]?.get("id_proveedor") as Long,
//                          nombre = it[i]?.get("nombre") as String,
//                          marca = it[i]?.get("marca") as String,
//                          modelo = it[i]?.get("modelo") as String,
//                          cantidad = it[i]?.get("cantidad") as Int,
//                          precio_compra = it[i]?.get("precio_compra") as Double,
//                          precio_venta = it[i]?.get("precio_venta") as Double,
//                          impuesto = it[i]?.get("impuestos") as Double,
//                          descripcion = it[i]?.get("descripcion") as String,
//                          estado = it[i]?.get("estado") as Boolean,
//                      )
//                      println("mapInventario ->> $mapInventario")
//                  }
//
//                  "Categoria" -> {
//                      mapCategoria =   categoria(
//                          id_categoria = it[i]?.get("id_categoria") as Long,
//                          nombre = it[i]?.get("nombre") as String,
//                          descripcion = it[i]?.get("descripcion") as String,
//                          estado = it[i]?.get("estado") as Boolean,
//                      )
//                      println("mapCategoria ->> $mapCategoria")
//                  }
//
//                  else -> {
//                      println(it[i])
//                  }
//              }
//           }
//                if (mapCategoria != null && mapInventario != null && mapPersona != null ) {
//                    listInventario.add(
//
//                        InventarioModeloRelation(
//                            provedor = mapPersona,
//                            inventario = mapInventario,
//                            categoria = mapCategoria
//                        )
//                    )
//                    println("lista listInventario dentro del if -> $listInventario")
//                }
//
//            }
//
//         println("Lista luego de la conversion ->> $listInventario")
//        return listInventario
//    }

    suspend fun InsertTrassaccionInventario(dataOfFirebase: MutableList<Map<String, Map<String, Any>>>) {

        val listInventario = listasMaptoTrasnsaccion(dataOfFirebase)
        listInventario.forEach {
            println("estoy imprimiendo it en la lista de trasaccion antes del if $it")
            if (it != null) {

                Dao.crearInventario(it)
                println("estoy imprimiendo it en inserccion de la trasaccion $it")
            }
        }

    }


}


fun Map<String, Any>.toPersona(): persona {
    return persona(
        id_persona = this["id_persona"] as Long,
        direccion = this["direccion"] as String,
        documento = this["documento"] as String,
        email = this["email"] as String,
        estado = this["estado"] as Boolean,
        nombre = this["nombre"] as String,
        telefono = this["telefono"] as String?,
        tipo_documento = this["tipo_documento"] as String,
        tipo_persona = this["tipo_persona"] as String,
    )
}

fun Map<String, Any>.toCategoria(): categoria {
    return categoria(
        id_categoria = this["id_categoria"] as Long,
        nombre = this["nombre"] as String,
        descripcion = this["descripcion"] as String,
        estado = this["estado"] as Boolean,
    )
}

fun Map<String, Any>.toInventario(): inventario? {
    val cantidad = this["cantidad"]
    when (cantidad) {
        is Int -> return inventario(
            id_inventario = this["id_inventario"] as Long,
            cantidad = cantidad,
            descripcion = this["descripcion"] as String,
            estado = this["estado"] as Boolean,
            id_categoria = this["id_categoria"] as Long,
            id_proveedor = this["id_proveedor"] as Long,
            impuesto = this["impuestos"] as Double,
            marca = this["marca"] as String,
            modelo = this["modelo"] as String,
            precio_compra = this["precio_compra"] as Double,
            precio_venta = this["precio_venta"] as Double,
            nombre = this["nombre"] as String,
        )

        else -> return null

    }


}
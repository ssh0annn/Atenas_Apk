package com.solidtype.atenas_apk_2.products.data.remote.remoteProFB.dataDb.DataDbProducts

import com.solidtype.atenas_apk_2.core.Transacciones.DaoTransacciones.DaoTransacciones
import com.solidtype.atenas_apk_2.core.Transacciones.ModeloTransacciones.InventarioModeloRelation
import com.solidtype.atenas_apk_2.core.Transacciones.ModeloTransacciones.TicketModeloRelation
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
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
                var oneInvetory = mutableMapOf<String,Map<String,Any>>()
                var mapInventario = mutableMapOf<String,Any>()
                var mapCategoria = mutableMapOf<String,Any>()
                var mapPersona = mutableMapOf<String,Any>()


                // optimizar los entities to map con funciones de exteciones
                mapInventario = mutableMapOf(
                    "id_inventario" to it.inventario.id_inventario,
                    "id_categoria" to it.inventario.id_categoria,
                    "id_proveedor" to (it.inventario.id_proveedor?:""),
                    "marca" to (it.inventario.marca?:""),
                    "modelo" to (it.inventario.modelo?:""),
                    "cantidad" to it.inventario.cantidad,
                    "precio_compra" to it.inventario.precio_compra,
                    "precio_venta" to it.inventario.precio_venta,
                    "impuestos" to it.inventario.impuesto,
                    "descripcion" to (it.inventario.descripcion?:""),
                    "estado" to it.inventario.estado,
                )
                mapCategoria = mutableMapOf(
                    "id_categoria" to it.categoria.id_categoria,
                    "nombre" to it.categoria.id_categoria,
                    "descripcion" to it.categoria.id_categoria,
                    "estado" to it.categoria.estado,
                    )

                mapPersona = mutableMapOf(
                    "id_persona" to it.provedor.id_persona,
                    "tipo_persona" to (it.provedor.tipo_persona?:""),
                    "nombre" to (it.provedor.nombre?:""),
                    "tipo_documento" to (it.provedor.tipo_documento?:""),
                    "documento" to (it.provedor.documento?:""),
                    "direccion" to (it.provedor.direccion?:""),
                    "telefono" to (it.provedor.telefono?:""),
                    "email" to (it.provedor.email?:""),
                    "estado" to it.provedor.estado,
                )


                // optimizar los entities to map con funciones de exteciones

                oneInvetory["Inventario"] = mapInventario
                oneInvetory["Categoria"] = mapCategoria
                oneInvetory["Persona"] = mapPersona
                collecionTicket.add(mapOf(it.inventario.id_inventario.toString() to oneInvetory))
            }
        }
        return  collecionTicket
    }



    /**
     * @return  List<InventarioModeloRelation>
     * @funcion: captura todos los datos de la tabla inventario y los debuelve en una lista de objetos InventarioModeloRelation.
     */
    private suspend fun datosInventoryRelations():List<InventarioModeloRelation> {
        return coroutineScope {
            val listInventario = async { Dao.getAllInventario()  }
            return@coroutineScope listInventario.await()
        }
    }
}
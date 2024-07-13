package com.solidtype.atenas_apk_2.facturacion.data.local

import com.solidtype.atenas_apk_2.core.transacciones.daotransacciones.DaoTransacciones
import com.solidtype.atenas_apk_2.core.transacciones.modeloTransacciones.DetalleVentaRelation
import com.solidtype.atenas_apk_2.core.transacciones.modeloTransacciones.InventarioModeloRelation
import com.solidtype.atenas_apk_2.util.toDataClassToMap
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import javax.inject.Inject

class DetalleDBImpl @Inject constructor (
   private val daoTransacciones: DaoTransacciones
){

   fun entityToString(dato:List<InventarioModeloRelation>):MutableList<Map<String,Map<String,Any>>>{
        val collecionTicket:MutableList<Map<String,Map<String,Any>>> = mutableListOf()
        if (dato.isNotEmpty()){
            dato.forEach {
                collecionTicket.add(mapOf(it.inventario.id_inventario.toString() to mutableMapOf("Inventario" to it.inventario.toDataClassToMap(),
                    "Categoria" to it.categoria.toDataClassToMap(), "Persona" to it.provedor.toDataClassToMap(),)))
            }
        }
        return  collecionTicket
    }


    /**
     * @return  List<InventarioModeloRelation>
     * @funcion: captura todos los datos de la tabla inventario y los debuelve en una lista de objetos InventarioModeloRelation.
     */
    private suspend fun dataDetalleVentaRelations():List<DetalleVentaRelation> {
        return coroutineScope {
            val listInventario = async { daoTransacciones.getAllDetalleVenta() }
            return@coroutineScope listInventario.await()
        }
    }


}
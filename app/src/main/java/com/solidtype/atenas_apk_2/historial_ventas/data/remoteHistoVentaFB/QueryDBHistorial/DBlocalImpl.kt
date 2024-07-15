package com.solidtype.atenas_apk_2.historial_ventas.data.remoteHistoVentaFB.QueryDBHistorial

import com.solidtype.atenas_apk_2.core.transacciones.daotransacciones.DaoTransacciones
import com.solidtype.atenas_apk_2.core.transacciones.modeloTransacciones.VentaRelationTransacciones
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import javax.inject.Inject

class DBlocalImpl  @Inject constructor (
    private val dao: DaoTransacciones
) {
    /**
     * @return  List<MutableList<Map<String,Map<String,Any>>>>
     * @funcion: captura todos los datos de la tabla Tickets y los debuelve en una lista de mapas con con mapas para la conversion a MutableList<Map<String,Map<String,Any>>>.
     */

//    fun entityToString(dato:List<VentaRelationTransacciones>):MutableList<Map<String,Map<String,Any>>>{
//        val collecionTicket:MutableList<Map<String,Map<String,Any>>> = mutableListOf()
//        if (dato.isNotEmpty()){
//            dato.forEach {
//                var OneVenta = mutableMapOf<String,Map<String,Any>>()
//                var mapTicket = mutableMapOf<String,Any>()
//                var mapUsuario = mutableMapOf<String,Any>()
//                var mapPersona = mutableMapOf<String,Any>()
//                var mapTipoVenta = mutableMapOf<String,Any>()
//
//
//                // optimizar los entities to map con funciones de exteciones
//                mapTicket = mutableMapOf(
//                    "id_venta" to it.venta.id_venta,
//                    "id_vendedor" to it.venta.id_vendedor,
//                    "id_cliente" to it.venta.id_cliente,
//                    "id_tipo_venta" to it.venta.id_tipo_venta,
//                    "subtotal" to it.venta.id_tipo_venta,
//                    "impuesto" to it.venta.subtotal,
//                    "total" to it.venta.impuesto,
//                    "cantidad" to it.venta.total,
//                    "fecha" to it.venta.cantidad,
//                    "estado" to it.venta.fecha,
//
//                )
//                mapUsuario = mutableMapOf(
//                    "id_usuario" to it.vendedor.id_usuario,
//                    "id_roll_usuario" to it.vendedor.id_roll_usuario,
//                    "nombre" to it.vendedor.nombre,
//                    "apellido" to it.vendedor.apellido,
//                    "email" to it.vendedor.email,
//                    "clave" to it.vendedor.clave,
//                    "telefono" to it.vendedor.telefono,
//                    "estado" to it.vendedor.estado,
//                )
//                mapPersona = mutableMapOf(
//                    "id_persona" to it.cliente.id_persona,
//                    "tipo_persona" to (it.cliente.tipo_persona?:""),
//                    "nombre" to (it.cliente.nombre?:""),
//                    "tipo_documento" to ( it.cliente.tipo_documento ?: ""),
//                    "documento" to  (it.cliente.documento ?: "") ,
//                    "direccion" to (it.cliente.direccion ?: ""),
//                    "telefono" to  (it.cliente.telefono ?: ""),
//                    "email" to (it.cliente.email?:""),
//                    "estado" to it.cliente.estado,
//                )
//                mapTipoVenta = mutableMapOf(
//                    "id_tipo_venta" to it.tipo_venta.id_tipo_venta.toString(),
//                    "tipo_pago" to it.tipo_venta.tipo_pago,
//                    "numeroTransaccion" to it.tipo_venta.numeroTransaccion,
//                    "tipo_comprobante" to (it.tipo_venta.tipo_comprobante?:""),
//                    "serie_comprobante" to (it.tipo_venta.serie_comprobante?:""),
//                    "num_comprobate" to (it.tipo_venta.num_comprobate?:""),
//                    "abono" to it.tipo_venta.abono,
//                    "presupuesto" to it.tipo_venta.presupuesto,
//                    "descuento" to (it.tipo_venta.descuento?:""),
//                    "subtotal" to it.tipo_venta.subtotal,
//                    "impuesto" to it.tipo_venta.impuesto,
//                    "total" to it.tipo_venta.total,
//                    "estado" to it.tipo_venta.estado,
//                )
//                // optimizar los entities to map con funciones de exteciones
//
//                OneVenta["venta"] = mapTicket
//                OneVenta["usuario"] = mapUsuario
//                OneVenta["persona"] = mapPersona
//                OneVenta["tipo_venta"] = mapTipoVenta
//                collecionTicket.add(mapOf(it.venta.id_venta.toString() to OneVenta))
//            }
//        }
//        return  collecionTicket
//    }



    /**
     * @return  List<TicketModeloRelation>
     * @funcion: captura todos los datos de la tabla Tickets y los debuelve en una lista de objetos TicketModeloRelation.
     */
    private suspend fun getAllventaLocal():List<VentaRelationTransacciones>{
        return coroutineScope {
            val listTickets = async { dao.getAllventas() }
            return@coroutineScope listTickets.await()
        }
    }
}

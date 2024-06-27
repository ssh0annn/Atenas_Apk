package com.solidtype.atenas_apk_2.facturacion.data.local

import com.solidtype.atenas_apk_2.core.transacciones.daoTransacciones.DaoTransacciones
import com.solidtype.atenas_apk_2.core.transacciones.modeloTransacciones.DetalleVentaRelation
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import javax.inject.Inject

class DetalleDBImpl @Inject constructor (
   private val daoTransacciones: DaoTransacciones
){

    fun entityToString(dato:List<DetalleVentaRelation>):MutableList<Map<String,Map<String,Any>>>{
        val collecionTicket:MutableList<Map<String,Map<String,Any>>> = mutableListOf()
        if (dato.isNotEmpty()){
            dato.forEach {
                var oneDetalle = mutableMapOf<String,Map<String,Any>>()
                var mapDetalle = mutableMapOf<String,Any>()
                var mapVenta = mutableMapOf<String,Any>()
                var mapInventario = mutableMapOf<String,Any>()
                var mapTipoVenta = mutableMapOf<String,Any>()


                // optimizar los entities to map con funciones de exteciones
                mapDetalle = mutableMapOf(
                    "id_detalle_venta" to it.detalleVenta.id_detalle_venta,
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

                mapVenta = mutableMapOf(
                    "id_venta" to it.venta.id_venta,
                    "id_vendedor" to it.venta.id_vendedor,
                    "id_cliente" to it.venta.id_cliente,
                    "id_tipo_venta" to it.venta.id_tipo_venta,
                    "subtotal" to it.venta.subtotal,
                    "impuesto" to it.venta.impuesto,
                    "total" to it.venta.total,
                    "cantidad" to it.venta.cantidad,
                    "fecha" to it.venta.fecha,
                    "estado" to it.venta.estado,

                )

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

                mapTipoVenta = mutableMapOf(
                    "id_tipo_venta" to it.tipo_venta.id_tipo_venta.toString(),
                    "tipo_pago" to it.tipo_venta.tipo_pago,
                    "numeroTransaccion" to it.tipo_venta.numeroTransaccion,
                    "tipo_comprobante" to (it.tipo_venta.tipo_comprobante?:""),
                    "serie_comprobante" to (it.tipo_venta.serie_comprobante?:""),
                    "num_comprobate" to (it.tipo_venta.num_comprobate?:""),
                    "abono" to it.tipo_venta.abono,
                    "presupuesto" to it.tipo_venta.presupuesto,
                    "descuento" to (it.tipo_venta.descuento?:""),
                    "subtotal" to it.tipo_venta.subtotal,
                    "impuesto" to it.tipo_venta.impuesto,
                    "total" to it.tipo_venta.total,
                    "estado" to it.tipo_venta.estado,
                )

                // optimizar los entities to map con funciones de exteciones

                oneDetalle["Detalle_venta"] = mapDetalle
                oneDetalle["venta"] = mapVenta
                oneDetalle["Inventario"] = mapInventario
                oneDetalle["Tipo_venta"] = mapTipoVenta
                collecionTicket.add(mapOf(it.inventario.id_inventario.toString() to oneDetalle))
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
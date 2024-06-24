package com.solidtype.atenas_apk_2.gestion_tickets.data.remote.DBtickets

import com.solidtype.atenas_apk_2.core.Transacciones.DaoTransacciones.DaoTransacciones
import com.solidtype.atenas_apk_2.core.Transacciones.ModeloTransacciones.TicketModeloRelation
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import javax.inject.Inject

class DbTicketsImpl @Inject constructor (
    private val dao: DaoTransacciones
) {

     fun entityToString(dato:List<TicketModeloRelation>):MutableList<Map<String,Map<String,Any>>>{
        val collecionTicket:MutableList<Map<String,Map<String,Any>>> = mutableListOf()
        if (dato.isNotEmpty()){
            dato.forEach {
                var estoEsunTicket = mutableMapOf<String,Map<String,Any>>()
                var mapTicket = mutableMapOf<String,Any>()
                var mapUsuario = mutableMapOf<String,Any>()
                var mapServicio = mutableMapOf<String,Any>()
                var mapPersona = mutableMapOf<String,Any>()
                var mapTipoVenta = mutableMapOf<String,Any>()
                var mapTipoDispositivo = mutableMapOf<String,Any>()

                // optimizar los entities to map con funciones de exteciones
                mapTicket = mutableMapOf(

                    "id_ticket" to it.ticket.id_ticket,
                    "id_vendedor" to it.ticket.id_vendedor,
                    "id_cliente" to it.ticket.id_cliente,
                    "id_tipo_venta" to it.ticket.id_tipo_venta,
                    "id_dispositivo" to it.ticket.id_dispositivo,
                    "id_servicio" to it.ticket.id_servicio,
                    "imei" to it.ticket.imei,
                    "falla" to it.ticket.falla,
                    "descripcion" to it.ticket.descripcion,
                    "nota" to it.ticket.nota,
                    "assesorios" to it.ticket.assesorios,
                    "fecha_inicio" to it.ticket.fecha_inicio,
                    "fecha_final" to it.ticket.fecha_final,
                    "estado" to it.ticket.estado,
                )
                mapUsuario = mutableMapOf(
                    "id_usuario" to it.vendedor.id_usuario,
                    "id_roll_usuario" to it.vendedor.id_roll_usuario,
                    "nombre" to it.vendedor.nombre,
                    "apellido" to it.vendedor.apellido,
                    "email" to it.vendedor.email,
                    "clave" to it.vendedor.clave,
                    "telefono" to it.vendedor.telefono,
                    "estado" to it.vendedor.estado,
                )
                mapServicio = mutableMapOf(
                    "id_servicio" to it.servicio.id_servicio,
                    "nombre" to it.servicio.nombre,
                    "descripcion" to (it.servicio.descripcion?:""),
                    "estado" to it.servicio.estado,

                )
                mapPersona = mutableMapOf(
                        "id_persona" to it.cliente.id_persona,
                        "tipo_persona" to (it.cliente.tipo_persona?:""),
                        "nombre" to (it.cliente.nombre?:""),
                        "tipo_documento" to ( it.cliente.tipo_documento ?: ""),
                        "documento" to  (it.cliente.documento ?: "") ,
                        "direccion" to (it.cliente.direccion ?: ""),
                        "telefono" to  (it.cliente.telefono ?: ""),
                        "email" to (it.cliente.email?:""),
                        "estado" to it.cliente.estado,
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
                mapTipoDispositivo = mutableMapOf(
                    "id_dispositivo" to it.dispositivo.id_dispositivo,
                    "nombre_comercial" to it.dispositivo.nombre_comercial,
                    "modelo" to it.dispositivo.modelo,
                    "marca" to it.dispositivo.marca,
                    "estado" to it.dispositivo.estado,

                )
                // optimizar los entities to map con funciones de exteciones

                estoEsunTicket["ticket"] = mapTicket
                estoEsunTicket["usuario"] = mapUsuario
                estoEsunTicket["servicio"] = mapServicio
                estoEsunTicket["persona"] = mapPersona
                estoEsunTicket["tipo_venta"] = mapTipoVenta
                estoEsunTicket["tipo_dispositivo"] = mapTipoDispositivo
                collecionTicket.add(mapOf(it.ticket.id_ticket.toString() to estoEsunTicket))
            }
        }
        return  collecionTicket
    }






    /**
     * @return  List<TicketModeloRelation>
     * @funcion: captura todos los datos de la tabla Tickets y los debuelve en una lista de objetos TicketModeloRelation.
     */
    private suspend fun getTicketsLocal():List<TicketModeloRelation>{
        return coroutineScope {
            val listTickets = async { dao.getAllTickets() }
            return@coroutineScope listTickets.await()
        }
    }
}
package com.solidtype.atenas_apk_2.gestion_tickets.data.remote.DBtickets

import com.google.gson.Gson
import com.solidtype.atenas_apk_2.authentication.actualizacion.domain.model.Usuario
import com.solidtype.atenas_apk_2.core.entidades.tipo_venta
import com.solidtype.atenas_apk_2.core.pantallas.Screens
import com.solidtype.atenas_apk_2.core.transacciones.daotransacciones.DaoTransacciones
import com.solidtype.atenas_apk_2.core.transacciones.modeloTransacciones.TicketModeloRelation
import com.solidtype.atenas_apk_2.dispositivos.model.Dispositivo
import com.solidtype.atenas_apk_2.gestion_proveedores.data.persona
import com.solidtype.atenas_apk_2.gestion_tickets.domain.model.ticket
import com.solidtype.atenas_apk_2.gestion_usuarios.domain.modelo.usuario
import com.solidtype.atenas_apk_2.servicios.modelo.servicio

import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import javax.inject.Inject


class DbTicketsImpl @Inject constructor (
    private val dao: DaoTransacciones
) {

//    fun StringtoEntity(data :List<Map<String,Map<String,Any>>>): List<TicketModeloRelation>{
//
//
//    }



//    /**
//     * @return  List<MutableList<Map<String,Map<String,Any>>>>
//     * @funcion: captura todos los datos de la tabla Tickets y los debuelve en una lista de mapas con con mapas para la conversion a MutableList<Map<String,Map<String,Any>>>.
//     */
//     fun entityToString(dato:List<TicketModeloRelation>):MutableList<Map<String,Map<String,Any>>>{
//        val collecion:MutableList<Map<String,Map<String,Any>>> = mutableListOf()
//        if (dato.isNotEmpty()){
//            dato.forEach {
//                collecion.add(
//                    mapOf(it.ticket.id_ticket.toString() to mutableMapOf(
//                    "ticket" to it.ticket.toDataClassToMap(),
//                    "usuario" to it.vendedor.toDataClassToMap(),
//                    "servicio" to it.servicio.toDataClassToMap(),
//                    "persona" to it.cliente.toDataClassToMap(),
//                    "tipo_venta" to it.tipo_venta.toDataClassToMap(),
//                    "tipo_dispositivo" to it.dispositivo.toDataClassToMap()
//                )))
//            }
//        }
//        return  collecion
//    }

//
//    fun stringToEntity(collection: MutableList<Map<String, Map<String, Any>>>): List<TicketModeloRelation> {
//        val result = mutableListOf<TicketModeloRelation>()
//
//        collection.forEach { item ->
//            item.forEach { (id, data) ->
//                val ticket = data["ticket"]?.let { mapToDataClass<ticket>(it as Map<String, Any>) }
//                val vendedor = data["usuario"]?.let { mapToDataClass<usuario>(it as Map<String, Any>) }
//                val servicio = data["servicio"]?.let { mapToDataClass<servicio>(it as Map<String, Any>) }
//                val cliente = data["persona"]?.let { mapToDataClass<persona>(it as Map<String, Any>) }
//                val tipoVenta = data["tipo_venta"]?.let { mapToDataClass<tipo_venta>(it as Map<String, Any>) }
//                val dispositivo = data["tipo_dispositivo"]?.let { mapToDataClass<Dispositivo>(it as Map<String, Any>) }
//
//                if (ticket != null && vendedor != null && servicio != null && cliente != null && tipoVenta != null && dispositivo != null) {
//                    val ticketModeloRelation = TicketModeloRelation(ticket, vendedor, servicio, cliente, tipoVenta, dispositivo)
//                    result.add(ticketModeloRelation)
//                }
//            }
//        }
//
//        return result
//    }
//
//    inline fun <reified T> mapToDataClass(map: Map<String, Any>): T {
//        val json = Gson().toJson(map)
//        return Gson().fromJson(json, T::class.java)
//    }


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
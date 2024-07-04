package com.solidtype.atenas_apk_2.gestion_tickets.data.remote.DBtickets

import com.solidtype.atenas_apk_2.core.transacciones.daoTransacciones.DaoTransacciones
import com.solidtype.atenas_apk_2.core.transacciones.modeloTransacciones.TicketModeloRelation
import com.solidtype.atenas_apk_2.util.toDataClassToMap
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



    /**
     * @return  List<MutableList<Map<String,Map<String,Any>>>>
     * @funcion: captura todos los datos de la tabla Tickets y los debuelve en una lista de mapas con con mapas para la conversion a MutableList<Map<String,Map<String,Any>>>.
     */
     fun entityToString(dato:List<TicketModeloRelation>):MutableList<Map<String,Map<String,Any>>>{
        val collecion:MutableList<Map<String,Map<String,Any>>> = mutableListOf()
        if (dato.isNotEmpty()){
            dato.forEach {
                collecion.add(
                    mapOf(it.ticket.id_ticket.toString() to mutableMapOf(
                    "ticket" to it.ticket.toDataClassToMap(),
                    "usuario" to it.vendedor.toDataClassToMap(),
                    "servicio" to it.servicio.toDataClassToMap(),
                    "persona" to it.cliente.toDataClassToMap(),
                    "tipo_venta" to it.tipo_venta.toDataClassToMap(),
                    "tipo_dispositivo" to it.dispositivo.toDataClassToMap()
                )))
            }
        }
        return  collecion
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
package com.solidtype.atenas_apk_2.servicios.modelo.casos_usos.manage_tickets

import com.solidtype.atenas_apk_2.core.entidades.tipo_venta
import com.solidtype.atenas_apk_2.gestion_proveedores.domain.casos_usos.util.client_builder.PersonaBuilder
import com.solidtype.atenas_apk_2.gestion_proveedores.domain.casos_usos.util.client_builder.PersonaDirector

import com.solidtype.atenas_apk_2.gestion_tickets.domain.casos_tickets.CasosTicket
import com.solidtype.atenas_apk_2.gestion_tickets.domain.model.TicketwithRelation
import com.solidtype.atenas_apk_2.gestion_tickets.domain.model.ticket
import com.solidtype.atenas_apk_2.servicios.presentation.servicios.ServicioTicket
import kotlinx.coroutines.flow.Flow
import java.time.LocalDate
import java.time.temporal.TemporalField
import javax.inject.Inject
class TicketsManeger @Inject constructor(private val casosTicket: CasosTicket) {

    suspend fun crearTicket(ticket: ServicioTicket) {

        val newTicket = ticket(
            id_ticket = System.currentTimeMillis()+1,
            id_vendedor = ticket.vendedor!!.id_usuario,
            id_cliente = ticket.cliente!!.id_cliente,
            id_tipo_venta = ticket.datosFinance!!.id_tipo_venta,
            id_dispositivo = ticket.dispositivo!!.id_dispositivo,
            id_servicio = ticket.servicio!!.id_servicio,
            imei = ticket.detalles.imei,
            falla =ticket.detalles.falla,
            descripcion=ticket.detalles.descripcion,
            nota=ticket.detalles.nota ,
            assesorios=ticket.detalles.assesorios,
            fecha_inicio=LocalDate.now(),
            fecha_final=ticket.fecha_final,
            estado = true
        )
        val transaction = TicketwithRelation(
            ticket = newTicket,
            vendedor = ticket.vendedor,
            servicio = ticket.servicio,
            cliente = PersonaDirector.createPersona(ticket.cliente),
            tipo_venta = ticket.datosFinance,
            dispositivo = ticket.dispositivo
        )


        casosTicket.crearTicket(transaction)
    }
    suspend fun completarPago(ticket: tipo_venta) {
        casosTicket.completarPago(ticket)
    }
    fun getDetalleTicket(switch:Boolean): Flow<List<TicketwithRelation>> {
        return casosTicket.getTickets(switch)
    }
    fun buscarTickets(any: String, switch:Boolean) =
        casosTicket.buscarTickets(any, switch)
}


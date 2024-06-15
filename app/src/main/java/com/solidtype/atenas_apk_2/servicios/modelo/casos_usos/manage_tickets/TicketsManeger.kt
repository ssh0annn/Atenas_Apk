package com.solidtype.atenas_apk_2.servicios.modelo.casos_usos.manage_tickets

import com.solidtype.atenas_apk_2.gestion_tickets.domain.casos_tickets.CasosTicket
import com.solidtype.atenas_apk_2.gestion_tickets.domain.model.ticket
import com.solidtype.atenas_apk_2.servicios.presentation.servicios.ServicioTicket
import java.time.LocalDate
import javax.inject.Inject

class TicketsManeger @Inject constructor(private val casosTicket: CasosTicket){

    suspend fun crearTicket(ticket: ServicioTicket){
            val id: Long = System.currentTimeMillis()
            ticket.vendedor?.let {
                //Pendiente de actualizacion
            

            }
           // casosTicket.crearTicket()
    }

    suspend fun completarPago(ticket: ticket) {
        casosTicket.completarPago(ticket)
    }


    fun getDetalleTicket() = casosTicket.getTickets()

    fun buscarTickets(any:String) =
         casosTicket.buscarTickets(any)


}
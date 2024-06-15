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
                ticket(
                    id_ticket= id,
                    id_vendedor=ticket.vendedor,
                    id_cliente=ticket.cliente!!.id_cliente,
                    id_tipo_venta=1,
                    id_dispositivo=ticket.dispositivo!!.id_dispositivo,
                    imei=ticket.detalles!!.imei,
                    falla=ticket.detalles.falla,
                    descripcion=ticket.detalles.descripcion,
                    nota=ticket.detalles.nota,
                    assesorios=ticket.detalles.assesorios ?: "",
                    total=ticket.datosFinance!!.total,
                    abono=ticket.datosFinance.abono,
                    presupuesto=ticket.datosFinance.presupuesto,
                    subtotal=ticket.datosFinance.subtotal,
                    impuesto=ticket.datosFinance.impuesto,
                    fecha_inicio= LocalDate.now(),
                    fecha_final=ticket.fecha_final,
                    estado=true
                    )
            

            }
           // casosTicket.crearTicket()
    }

    suspend fun completarPago(ticket: ticket) {
        casosTicket.completarPago(ticket)
    }
    fun getTickets()= casosTicket.getDetallesTicket()

    fun getDetalleTicket() = casosTicket.getTickets()

    fun buscarTickets(any:String) =
         casosTicket.buscarTickets(any)


}
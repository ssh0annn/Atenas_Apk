package com.solidtype.atenas_apk_2.gestion_tickets.data.repositoryImpl

import com.solidtype.atenas_apk_2.core.daos.tipo_ventaDao
import com.solidtype.atenas_apk_2.core.entidades.tipo_venta
import com.solidtype.atenas_apk_2.gestion_tickets.data.ticketDao
import com.solidtype.atenas_apk_2.gestion_tickets.domain.TicketRepository
import com.solidtype.atenas_apk_2.gestion_tickets.domain.model.TicketwithRelation
import com.solidtype.atenas_apk_2.gestion_tickets.domain.model.ticket
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class TicketRepositoryImpl @Inject constructor(
    private val miticket:ticketDao,
    private val tipoVenta:tipo_ventaDao
):TicketRepository {

    override fun getTickets(): Flow<List<TicketwithRelation>> {
        return miticket.getTicketswithRelation()
    }

    override fun buscarTickets(any: String): Flow<List<TicketwithRelation>> {
       return miticket.buscarTicketsWithRelation(any)
    }

    override suspend fun crearTicket(ticket: TicketwithRelation) {
        miticket.crearTicket(ticket)
    }


    override suspend fun closeTicket(ticket: ticket) {
        miticket.updateTicket(ticket)
    }

    override suspend fun completarPagoPendiente(pagoInfo: tipo_venta) {
        tipoVenta.updateTipoVenta(pagoInfo)
    }
    override suspend fun getPaymentInfo(tick: ticket): tipo_venta {
      return tipoVenta.buscarTipoVentaPorId(tick.id_tipo_venta)
    }
}
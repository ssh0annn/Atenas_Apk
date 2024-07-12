package com.solidtype.atenas_apk_2.historial_ventas.domain.casosusos

import com.solidtype.atenas_apk_2.gestion_tickets.domain.model.ticket
import com.solidtype.atenas_apk_2.historial_ventas.domain.model.actualizacion.TipoVentaTicket
import com.solidtype.atenas_apk_2.historial_ventas.domain.repositories.HistorialRepository
import javax.inject.Inject

class ExportarTicketsHistorial @Inject constructor(private val repo:HistorialRepository) {
    operator suspend fun invoke(tikects:List<TipoVentaTicket>)=repo.exportarHistorialTickets(tikects)
}
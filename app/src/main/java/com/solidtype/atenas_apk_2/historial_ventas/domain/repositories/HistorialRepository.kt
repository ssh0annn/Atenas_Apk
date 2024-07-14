package com.solidtype.atenas_apk_2.historial_ventas.domain.repositories

import android.net.Uri
import com.solidtype.atenas_apk_2.core.entidades.tipo_venta
import com.solidtype.atenas_apk_2.gestion_tickets.domain.model.ticket
import com.solidtype.atenas_apk_2.historial_ventas.domain.model.actualizacion.TipoVentaTicket
import com.solidtype.atenas_apk_2.historial_ventas.domain.model.actualizacion.TipoVentaVenta
import com.solidtype.atenas_apk_2.historial_ventas.domain.model.actualizacion.venta
import kotlinx.coroutines.flow.Flow
import java.time.LocalDate

interface HistorialRepository {
    fun mostrarTodasVentas(): Flow<List<TipoVentaVenta>>
    suspend fun exportarVentas(listaProductos: List<TipoVentaVenta>): Uri
    suspend fun exportarHistorialTickets(listaProductos: List<TipoVentaTicket>): Uri
    fun buscarPorFechasCategoriasVentas(
        desde: LocalDate,
        hasta: LocalDate
    ): Flow<List<TipoVentaVenta>>

    fun mostrarTickets(): Flow<List<TipoVentaTicket>>
    fun mostrarTicketsPorFecha(
        desde: LocalDate,
        hasta: LocalDate,
    ): Flow<List<TipoVentaTicket>>

    suspend fun sync()

}
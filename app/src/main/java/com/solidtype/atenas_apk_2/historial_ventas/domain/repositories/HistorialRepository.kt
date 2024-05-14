package com.solidtype.atenas_apk_2.historial_ventas.domain.repositories

import android.net.Uri
import com.solidtype.atenas_apk_2.historial_ventas.domain.model.actualizacion.ticket
import com.solidtype.atenas_apk_2.historial_ventas.domain.model.actualizacion.venta
import kotlinx.coroutines.flow.Flow

interface HistorialRepository {
    fun mostrarTodasVentas() :Flow <List<venta>>
    suspend fun exportarVentas(listaProductos:List<venta>):Uri
    suspend fun exportarHistorialTickets(listaProductos:List<ticket>):Uri
    fun buscarPorFechasCategoriasVentas(Fecha_inicio:String, fecha_final:String, categoria:String): Flow<List<venta>>

    fun mostrarTickets(): Flow<List<ticket>>
    fun mostrarTicketsPorFecha(  fechaIni: String,
                                 fechaFinal:String,
                                 catego: String): Flow<List<ticket>>
    suspend fun sync()

}
package com.solidtype.atenas_apk_2.historial_ventas.domain.repositories

import android.net.Uri
import com.solidtype.atenas_apk_2.historial_ventas.domain.model.HistorialVentaEntidad
import kotlinx.coroutines.flow.Flow

interface HistorialRepository {



    fun mostrarTodasVentas() :Flow <List<HistorialVentaEntidad>>
    suspend fun exportarVentas(listaProductos:List<HistorialVentaEntidad>):Uri
    fun buscarPorFechasCategoriasVentas(Fecha_inicio:String, fecha_final:String, categoria:String): Flow<List<HistorialVentaEntidad>>

}
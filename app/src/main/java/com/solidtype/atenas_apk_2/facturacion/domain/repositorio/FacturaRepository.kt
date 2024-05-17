package com.solidtype.atenas_apk_2.facturacion.domain.repositorio

import com.solidtype.atenas_apk_2.facturacion.domain.model.detalle_venta
import com.solidtype.atenas_apk_2.historial_ventas.domain.model.actualizacion.venta
import kotlinx.coroutines.flow.Flow
import java.time.LocalDate

interface FacturaRepository{

    fun busqueda(fechaini:LocalDate, fechafinal:LocalDate, datoSemejante:String):Flow<List<venta>>

    fun MostrarTodos():Flow<List<venta>>

    suspend fun DetalleFactura(numeroFactura:Long): detalle_venta


}
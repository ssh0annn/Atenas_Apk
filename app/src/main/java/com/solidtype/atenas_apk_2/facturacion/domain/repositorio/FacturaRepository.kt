package com.solidtype.atenas_apk_2.facturacion.domain.repositorio

import com.solidtype.atenas_apk_2.facturacion.domain.model.VentasRelacionadas
import com.solidtype.atenas_apk_2.facturacion.domain.model.detalle_venta
import com.solidtype.atenas_apk_2.historial_ventas.domain.model.actualizacion.venta
import kotlinx.coroutines.flow.Flow
import java.time.LocalDate

interface FacturaRepository{
    fun busqueda(desde: LocalDate,hasta: LocalDate, datoSemejante:String):Flow<List<VentasRelacionadas>>

    fun MostrarTodos():Flow<List<VentasRelacionadas>>


}
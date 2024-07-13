package com.solidtype.atenas_apk_2.facturacion.data

import com.solidtype.atenas_apk_2.facturacion.data.local.dao.detalle_ventaDao
import com.solidtype.atenas_apk_2.facturacion.domain.model.VentasRelacionadas
import com.solidtype.atenas_apk_2.facturacion.domain.model.detalle_venta
import com.solidtype.atenas_apk_2.facturacion.domain.repositorio.FacturaRepository
import com.solidtype.atenas_apk_2.historial_ventas.data.local.dao.actualizacion.ventaDao
import com.solidtype.atenas_apk_2.historial_ventas.domain.model.actualizacion.venta
import kotlinx.coroutines.flow.Flow
import java.time.LocalDate
import javax.inject.Inject

class FacturaRepositoryImpl @Inject constructor(
    private val detalleVenta: detalle_ventaDao,
    private val venta: ventaDao
    ) : FacturaRepository {
    override fun busqueda(
        desde: LocalDate,
        hasta: LocalDate,
        datoSemejante: String
    ): Flow<List<VentasRelacionadas>> {
            return venta.buscarVentaWithRelation(datoSemejante, desde, hasta)
    }
    override fun MostrarTodos(): Flow<List<VentasRelacionadas>> {
        return venta.getVentaWithRelation()
    }



}
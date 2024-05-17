package com.solidtype.atenas_apk_2.facturacion.data

import com.solidtype.atenas_apk_2.facturacion.data.local.dao.detalle_ticketDao
import com.solidtype.atenas_apk_2.facturacion.data.local.dao.detalle_ventaDao
import com.solidtype.atenas_apk_2.facturacion.domain.model.detalle_ticket
import com.solidtype.atenas_apk_2.facturacion.domain.model.detalle_venta
import com.solidtype.atenas_apk_2.facturacion.domain.repositorio.FacturaRepository
import com.solidtype.atenas_apk_2.historial_ventas.data.local.dao.actualizacion.ventaDao
import com.solidtype.atenas_apk_2.historial_ventas.domain.model.actualizacion.venta
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import java.time.LocalDate
import javax.inject.Inject

class FacturaRepositoryImpl @Inject constructor(
    private val detalleVenta: detalle_ventaDao,
    private val venta: ventaDao

    ) : FacturaRepository {
    override fun busqueda(
        fechaini: LocalDate,
        fechafinal: LocalDate,
        datoSemejante: String
    ): Flow<List<venta>> {
            return venta.buscarporRangoFecha(datoSemejante, fechaini, fechafinal)
    }
    override fun MostrarTodos(): Flow<List<venta>> {
        return venta.getVentas()
    }

    override suspend fun DetalleFactura(numeroFactura: Long): detalle_venta {
       return  detalleVenta.getDetalleVentaByIdVenta(numeroFactura)
    }

}
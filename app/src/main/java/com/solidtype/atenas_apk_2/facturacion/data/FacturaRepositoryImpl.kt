package com.solidtype.atenas_apk_2.facturacion.data

import com.solidtype.atenas_apk_2.facturacion.data.local.dao.detalle_ticketDao
import com.solidtype.atenas_apk_2.facturacion.data.local.dao.detalle_ventaDao
import com.solidtype.atenas_apk_2.facturacion.domain.model.detalle_ticket
import com.solidtype.atenas_apk_2.facturacion.domain.model.detalle_venta
import com.solidtype.atenas_apk_2.facturacion.domain.repositorio.FacturaRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import java.time.LocalDate
import javax.inject.Inject

class FacturaRepositoryImpl @Inject constructor(private val detalleTicket: detalle_ticketDao,
                                                private val detalleVenta: detalle_ventaDao
) : FacturaRepository {
    override fun busqueda(
        fechaini: LocalDate,
        fechafinal: LocalDate,
        datoSemejante: String
    ): Flow<List<List<String>>> {



        return  flowOf(listOf(
            listOf( " Adderlis", "Pendejo")
        ))
    }

    override fun MostrarTodos(): Flow<List<List<String>>> {

        return flowOf(listOf(
            listOf( " Adderlis", "Pendejo")
        ))
    }
 
    override fun DetalleFactura(numeroFactura: Long): Flow<List<List<String>>> {

       return flowOf(listOf(
           listOf( " Adderlis", "Pendejo")
       ))
    }
}
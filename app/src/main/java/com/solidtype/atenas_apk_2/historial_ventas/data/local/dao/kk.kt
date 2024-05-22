package com.solidtype.atenas_apk_2.historial_ventas.data.local.dao

import com.solidtype.atenas_apk_2.historial_ventas.data.local.dao.actualizacion.ventaDao
import com.solidtype.atenas_apk_2.historial_ventas.domain.model.HistorialTicketEntidad
import com.solidtype.atenas_apk_2.historial_ventas.domain.model.actualizacion.venta
import com.solidtype.atenas_apk_2.util.toLocalDate
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class kk @Inject constructor(
    private val historial: HistorialTicketDAO,
    private val ventas: ventaDao
) {
    suspend fun ingresar() {
            ventas.addVenta(
                venta(
                    2,
                    1,
                    1,
                    1,
                    0.00,
                    8350.00,
                    2.0,
                    0,
                    "".toLocalDate(),
                    true
                )
            )



        //  historial.setHistorialTiket(HistorialTicketEntidad(11,"dd","dd",22,"dd","dd","dd","dd",11.0,11.0,"dd",22.0,"22","Ticket","22-22-2222","22-25-2222"))
    }

    /*
    val id_venta: Long,
    val id_vendedor: Long,
    val id_cliente: Long,
    val id_tipo_venta: Long,
    val subtotal: Double,
    val impuesto: Double,
    val total: Double,
    val cantidad: Int,
    val fecha: LocalDate,
    val estado: Boolean
     */
    suspend fun obtener() {
        var l = historial.getHistorialTicket()
        l.collect { product ->
            for (i in product) {
                println(i)
            }
        }
    }

    suspend fun obtenerByFC() {
        //var l = historial.getHistorialTicketFechaDias("22-22-2222","22-25-2222","Ticket")

    }
}

package com.solidtype.atenas_apk_2.historial_ventas.data.implementaciones

import android.net.Uri
import com.solidtype.atenas_apk_2.core.daos.tipo_ventaDao
import com.solidtype.atenas_apk_2.core.entidades.tipo_venta
import com.solidtype.atenas_apk_2.facturacion.domain.model.VentasRelacionadas
import com.solidtype.atenas_apk_2.gestion_tickets.data.ticketDao
import com.solidtype.atenas_apk_2.historial_ventas.data.local.dao.actualizacion.ventaDao
import com.solidtype.atenas_apk_2.historial_ventas.data.remoteHistoVentaFB.intefaces.MediatorHistorialVentas
import com.solidtype.atenas_apk_2.historial_ventas.data.remoteTicketsFB.interfaces.RemoteTicketsFB
import com.solidtype.atenas_apk_2.historial_ventas.domain.repositories.HistorialRepository
import com.solidtype.atenas_apk_2.gestion_tickets.domain.model.ticket
import com.solidtype.atenas_apk_2.historial_ventas.domain.model.actualizacion.TipoVentaTicket
import com.solidtype.atenas_apk_2.historial_ventas.domain.model.actualizacion.TipoVentaVenta
import com.solidtype.atenas_apk_2.historial_ventas.domain.model.actualizacion.venta
import com.solidtype.atenas_apk_2.util.ListaTicket
import com.solidtype.atenas_apk_2.util.XlsManeger
import com.solidtype.atenas_apk_2.util.toLocalDate
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import java.time.LocalDate
import javax.inject.Inject

class HistorialRepositoryImp @Inject constructor(
    private val dao: tipo_ventaDao,
    private val excel: XlsManeger,
    private val daoTickets: ticketDao,
    private val sync1: MediatorHistorialVentas,
    private val sync2: RemoteTicketsFB
) : HistorialRepository {

    override fun mostrarTodasVentas(): Flow<List<TipoVentaVenta>> {
        return dao.getTipoVentas()
    }

    override suspend fun exportarVentas(listaProductos: List<TipoVentaVenta>): Uri {
        val columnas = listOf(
            "id_venta", "id_cliente", "cantidad", "tipo_pago", "No. Transaccion", "subtotal",
            "descuento", "impuesto", "total", "abono", "restante", "fecha"
        )


        val productosVendidos: MutableList<List<String>> = mutableListOf()
        try {
            for (productos in listaProductos) {
                val temp = mutableListOf<String>()
                temp.add(productos.venta.id_venta.toString())
                temp.add(productos.venta.id_cliente.toString())
                temp.add(productos.venta.id_cliente.toString())
                temp.add(productos.venta.cantidad.toString())
                temp.add(productos.tipoVenta.tipo_pago)
                temp.add(productos.tipoVenta.numeroTransaccion)
                temp.add(productos.tipoVenta.subtotal.toString())
                temp.add(productos.tipoVenta.descuento.toString())
                temp.add(productos.tipoVenta.impuesto.toString())
                temp.add(productos.tipoVenta.total.toString())
                temp.add(productos.tipoVenta.abono.toString())
                temp.add(productos.tipoVenta.fecha.toString())
                productosVendidos.add(temp)

            }

            return excel.crearXls(
                "Ventas_${System.currentTimeMillis()}",
                columnas,
                productosVendidos
            )
        } catch (_: Exception) {
            println("Error en la conversion de datos")
        }

        return Uri.EMPTY
    }

    override suspend fun exportarHistorialTickets(listaProductos: List<TipoVentaTicket>): Uri {
        val columnas = ListaTicket()
        val productosVendidos: MutableList<List<String>> = mutableListOf()
        try {
            for (productos in listaProductos) {
                val temp = mutableListOf<String>()

                temp.add(productos.venta.id_ticket.toString())
                temp.add(productos.servicios)
                temp.add(productos.venta.falla)
                temp.add(productos.tipoVenta.tipo_pago)
                temp.add(productos.tipoVenta.numeroTransaccion)
                temp.add(productos.tipoVenta.subtotal.toString())
                temp.add(productos.tipoVenta.descuento.toString())
                temp.add(productos.tipoVenta.impuesto.toString())
                temp.add(productos.tipoVenta.total.toString())
                temp.add(productos.tipoVenta.abono.toString())
                temp.add(productos.tipoVenta.restantante.toString())
                temp.add(productos.tipoVenta.fecha.toString())
                temp.add(if (productos.tipoVenta.estado) "open" else "closed")
                productosVendidos.add(temp)
            }
            return excel.crearXls("HistorialTickets", columnas, productosVendidos)
        } catch (_: Exception) {
            println("Error en la conversion de datos")
        }

        return Uri.EMPTY
    }

    //Removi la variable fecha final de donde la recive el DAO, ARREGLALO!
    override fun buscarPorFechasCategoriasVentas(
        desde: LocalDate,
        hasta: LocalDate,

        ): Flow<List<TipoVentaVenta>> {

        return dao.buscarTiposVentasFecha(desde, hasta)

    }

    override fun mostrarTickets(): Flow<List<TipoVentaTicket>> {
        return daoTickets.getTickets()

    }

    override fun mostrarTicketsPorFecha(
        desde: LocalDate,
        hasta: LocalDate
    ): Flow<List<TipoVentaTicket>> {
        //Aqui cambie porque actualize la base de datos.
        return daoTickets.getTicketsByFechas(desde, hasta)
    }

    override suspend fun sync() {

    }
}
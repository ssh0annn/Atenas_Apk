package com.solidtype.atenas_apk_2.historial_ventas.data.implementaciones

import android.net.Uri
import com.solidtype.atenas_apk_2.gestion_tickets.data.ticketDao
import com.solidtype.atenas_apk_2.historial_ventas.data.local.dao.actualizacion.ventaDao
import com.solidtype.atenas_apk_2.historial_ventas.data.remoteHistoVentaFB.intefaces.MediatorHistorialVentas
import com.solidtype.atenas_apk_2.historial_ventas.data.remoteTicketsFB.interfaces.RemoteTicketsFB
import com.solidtype.atenas_apk_2.historial_ventas.domain.repositories.HistorialRepository
import com.solidtype.atenas_apk_2.gestion_tickets.domain.model.ticket
import com.solidtype.atenas_apk_2.historial_ventas.domain.model.actualizacion.venta
import com.solidtype.atenas_apk_2.util.ListaTicket
import com.solidtype.atenas_apk_2.util.XlsManeger
import com.solidtype.atenas_apk_2.util.toGetColumns
import com.solidtype.atenas_apk_2.util.toLocalDate
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import javax.inject.Inject

class HistorialRepositoryImp @Inject constructor(
    private val dao: ventaDao,
    private val excel: XlsManeger,
    private val daoTickets: ticketDao,
    private val sync1: MediatorHistorialVentas,
    private val sync2: RemoteTicketsFB
) : HistorialRepository {

    override fun mostrarTodasVentas(): Flow<List<venta>> {
        return dao.getVentas()
    }

    suspend fun insertalTemporal(
        ojeto:venta
    ){
        dao.addVenta(ojeto)
    }

    override suspend fun exportarVentas(listaProductos:List<venta>): Uri {
        val columnas = listaProductos[1].toGetColumns()
        val productosVendidos:MutableList<List<String>> = mutableListOf()
        try {
            for(productos in listaProductos ){
                val temp = mutableListOf<String>()

                temp.add(productos.id_venta.toString())
                temp.add(productos.id_vendedor.toString())
                temp.add(productos.id_cliente.toString())
                temp.add(productos.id_tipo_venta.toString())
                temp.add(productos.subtotal.toString())
                temp.add(productos.impuesto.toString())
                temp.add(productos.total.toString())
                temp.add(productos.cantidad.toString())
                temp.add(productos.fecha.toString())
                temp.add(productos.estado.toString())
                productosVendidos.add(temp)

            }

            return excel.crearXls("Ventas", columnas,productosVendidos )
        }catch( _ : Exception){
            println("Error en la conversion de datos")
        }

        return Uri.EMPTY
    }

    override suspend fun exportarHistorialTickets(listaProductos: List<ticket>): Uri {
        val columnas = ListaTicket()

        val productosVendidos:MutableList<List<String>> = mutableListOf()
        try {
            for(productos in listaProductos ){
                val temp = mutableListOf<String>()

                temp.add(productos.id_ticket.toString())
                temp.add(productos.id_vendedor.toString())
                temp.add(productos.id_cliente.toString())
                temp.add(productos.id_tipo_venta.toString())
                productos.assesorios?.let { temp.add(it) }
                temp.add(productos.fecha_inicio.toString())
                temp.add(productos.fecha_final.toString())
                temp.add(productos.estado.toString())
                productosVendidos.add(temp)
            }
            return excel.crearXls("HistorialTickets", columnas,productosVendidos )
        }catch( _ : Exception){
            println("Error en la conversion de datos")
        }

        return Uri.EMPTY
    }

    //Removi la variable fecha final de donde la recive el DAO, ARREGLALO!
    override fun buscarPorFechasCategoriasVentas(
        Fecha_inicio: String,
        fecha_final: String,

    ): Flow<List<venta>> {

       return dao.getHistorialVentaFechaCategoria(Fecha_inicio.toLocalDate(), fecha_final.toLocalDate())

    }

    override fun mostrarTickets(): Flow<List<ticket>> {
        return daoTickets.getTickets()

    }

    override fun mostrarTicketsPorFecha(
        fechaIni: String,
        fechaFinal:String
    ): Flow<List<ticket>> {
        //Aqui cambie porque actualize la base de datos.
       return daoTickets.getTicketsByFechas(fechaIni.toLocalDate(), fechaFinal.toLocalDate())
    }

    override suspend fun sync() {
        withContext(Dispatchers.Default){
            sync1()
            sync2.asycTickets()
        }
    }
}
package com.solidtype.atenas_apk_2.historial_ventas.data.implementaciones

import android.net.Uri
import com.solidtype.atenas_apk_2.historial_ventas.data.local.dao.HistorialTicketDAO
import com.solidtype.atenas_apk_2.historial_ventas.data.local.dao.HistorialVentaDAO
import com.solidtype.atenas_apk_2.historial_ventas.data.remoteHistoVentaFB.mediator.MediatorHistorialVentas
import com.solidtype.atenas_apk_2.historial_ventas.data.remoteTicketsFB.mediadorTicket.RemoteTicketsFB
import com.solidtype.atenas_apk_2.historial_ventas.domain.model.HistorialTicketEntidad
import com.solidtype.atenas_apk_2.historial_ventas.domain.repositories.HistorialRepository
import com.solidtype.atenas_apk_2.historial_ventas.domain.model.HistorialVentaEntidad
import com.solidtype.atenas_apk_2.util.XlsManeger
import com.solidtype.atenas_apk_2.util.toIsoDate
import com.solidtype.atenas_apk_2.util.toLocalDate
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import javax.inject.Inject

class HistorialRepositoryImp @Inject constructor(
    private val dao: HistorialVentaDAO,
    private val excel: XlsManeger,
    private val daoTickets:HistorialTicketDAO,
    private val sync1: MediatorHistorialVentas,
    private val sync2: RemoteTicketsFB
) : HistorialRepository {

    override fun mostrarTodasVentas(): Flow<List<HistorialVentaEntidad>> {


        return dao.getHistorialVenta()
    }

    fun insertalTemporal(
        ojeto:HistorialVentaEntidad
    ){
        dao.setHistorialVenta(ojeto)
    }

    override suspend fun exportarVentas(listaProductos:List<HistorialVentaEntidad>): Uri {
        val columnas = listOf(
            "Codigo",
            "Nombre",
            "NombreCliente",
            "Descripcion",
            "Imei",
            "Cantidad",
            "Categoria",
            "Modelo",
            "Marca",
            "Precio",
            "TipoVenta",
            "Total",
            "FechaFin"
        )
        val productosVendidos:MutableList<List<String>> = mutableListOf()
        try {
            for(productos in listaProductos ){
                val temp = mutableListOf<String>()

                temp.add(productos.Codigo.toString())
                temp.add(productos.Nombre)
                temp.add(productos.NombreCliente)
                temp.add(productos.Descripcion)
                temp.add(productos.Imei)
                temp.add(productos.Cantidad.toString())
                temp.add(productos.Categoria)
                temp.add(productos.Modelo)
                temp.add(productos.Marca)
                temp.add(productos.Precio.toString())
                temp.add(productos.TipoVenta)
                temp.add(productos.Total.toString())
                temp.add(productos.FechaIni.toString())
                productosVendidos.add(temp)
            }

            return excel.crearXls("HistorialVentas", columnas,productosVendidos )
        }catch( _ : Exception){
            println("Error en la conversion de datos")
        }

        return Uri.EMPTY
    }

    override suspend fun exportarHistorialTickets(listaProductos: List<HistorialTicketEntidad>): Uri {
        val columnas = listOf(
            "Codigo",
            "NombreCliente",
            "Modelo",
            "Telefono",
            "FaltaEquipo",
            "EstadoEquipo",
            "Marca",
            "Email",
            "Restante",
            "Abono",
            "Nota",
            "Precio",
            "Servicio",
            "Categoria",
            "FechaInicial",
            "FechaFinal"
        )
        val productosVendidos:MutableList<List<String>> = mutableListOf()
        try {
            for(productos in listaProductos ){
                val temp = mutableListOf<String>()

                temp.add(productos.Codigo.toString())
                temp.add(productos.NombreCliente)
                temp.add(productos.Modelo)
                temp.add(productos.Telefono.toString())
                temp.add(productos.FaltaEquipo)
                temp.add(productos.EstadoEquipo)
                temp.add(productos.Marca)
                temp.add(productos.Email)
                temp.add(productos.Restante.toString())
                temp.add(productos.Abono.toString())
                temp.add(productos.Nota)
                temp.add(productos.Precio.toString())
                temp.add(productos.Servicio)
                temp.add(productos.Categoria)
                temp.add(productos.FechaInicial.toString())
                temp.add(productos.FechaFinal.toString())
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
        fecha_inicio: String,
        fecha_final: String,
        categoria: String
    ): Flow<List<HistorialVentaEntidad>> {

       return dao.getHistorialVentaFechaCategoria(fecha_inicio.toLocalDate(), fecha_final.toLocalDate())

    }

    override fun mostrarTickets(): Flow<List<HistorialTicketEntidad>> {
        return daoTickets.getHistorialTicket()

    }

    override fun mostrarTicketsPorFecha(
        fechaIni: String,
        fechaFinal:String,
        catego: String
    ): Flow<List<HistorialTicketEntidad>> {
        //Aqui cambie porque actualize la base de datos.

       return daoTickets.getHistorialTicketFechaDias(fechaIni.toLocalDate(), fechaFinal.toLocalDate())
    }

    override suspend fun sync() {
        withContext(Dispatchers.Default){
            sync1()
            sync2.asycTickets()
        }
    }
}
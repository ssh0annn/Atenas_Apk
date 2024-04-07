package com.solidtype.atenas_apk_2.historial_ventas.data.implementaciones

import android.net.Uri
import com.solidtype.atenas_apk_2.historial_ventas.data.local.dao.HistorialVentaDAO
import com.solidtype.atenas_apk_2.historial_ventas.domain.repositories.HistorialRepository
import com.solidtype.atenas_apk_2.historial_ventas.domain.model.HistorialVentaEntidad
import com.solidtype.atenas_apk_2.util.XlsManeger
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class HistorialRepositoryImp @Inject constructor(
    private val dao: HistorialVentaDAO,
    private val excel: XlsManeger
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
            "Imei",
            "Descripcion",
            "Cantidad",
            "Categoria",
            "Modelo",
            "Marca",
            "Precio",
            "TipoVenta",
            "FechaFin",
            "FechaIni"
        )
        val productosVendidos:MutableList<List<String>> = mutableListOf()
        try {
            for(productos in listaProductos ){
                val temp = mutableListOf<String>()

                temp.add(productos.Codigo.toString())
                temp.add(productos.Nombre)
                temp.add(productos.TipoVenta)
                temp.add(productos.NombreCliente!!)
                temp.add(productos.Imei.toString())
                temp.add(productos.Descripcion)
                temp.add(productos.Cantidad.toString())
                temp.add(productos.Categoria)
                temp.add(productos.Modelo)
                temp.add(productos.Marca)
                temp.add(productos.Precio.toString())
                temp.add(productos.TipoVenta)
                temp.add(productos.FechaIni)
                temp.add(productos.FechaFin)

                productosVendidos.add(temp)

            }

            return excel.crearXls("HistorialVentas", columnas,productosVendidos )
        }catch( _ : Exception){
            println("Error en la conversion de datos")

        }

        return Uri.EMPTY
    }

    override fun buscarPorFechasCategoriasVentas(
        fecha_inicio: String,
        fecha_final: String,
        categoria: String
    ): Flow<List<HistorialVentaEntidad>> {
       return dao.getHistorialVentaFechaCategoria(fecha_final, fecha_inicio, categoria)
    }
}
package com.solidtype.atenas_apk_2.products.data.repositoryImpl

import android.net.Uri
import com.solidtype.atenas_apk_2.products.data.local.dao.categoriaDao
import com.solidtype.atenas_apk_2.products.data.local.dao.inventarioDao
import com.solidtype.atenas_apk_2.products.data.remote.remoteProFB.interfaces.MediatorProducts
import com.solidtype.atenas_apk_2.products.domain.model.actualizacion.categoria
import com.solidtype.atenas_apk_2.products.domain.model.actualizacion.inventario
import com.solidtype.atenas_apk_2.products.domain.repository.InventarioRepo
import com.solidtype.atenas_apk_2.util.XlsManeger
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import javax.inject.Inject

class InventarioRepoImpl @Inject constructor(
    private val catego:categoriaDao,
    private val daoProductos: inventarioDao, private val excel: XlsManeger,
    private val mediador2: MediatorProducts
) : InventarioRepo {


    override fun getProducts(): Flow<List<inventario>> {

        return daoProductos.getInventario()

    }

    override suspend fun getProductByCodigo(codigo: Int): inventario {
        return daoProductos.getInventariosById(codigo)
    }

    override fun searchProductsLike(datos: String): Flow<List<inventario>> {
        return daoProductos.buscarEnInventario(datos)
    }

    override suspend fun createProducts(prodcuto: inventario): Boolean {
        daoProductos.addInventario(prodcuto)
        return true
    }

    override suspend fun deleteProduct(codigo: inventario): Boolean {
        daoProductos.deleteInventario(codigo)
        return true//hay que manejarlo
    }

    override suspend fun updateProduct(producto: inventario): Boolean {
        daoProductos.updateInventario(producto)
        return true //hay que manejarlo

    }

    override suspend fun exportarExcel(productos: List<inventario>): Uri =
        withContext(Dispatchers.Default) {

            val columnas = listOf(
                "id_inventario",
                "id_categoria",
                "id_proveedor",
                "nombre",
                "marca",
                "modelo",
                "cantidad",
                "precio_compra",
                "precio_venta",
                "descripcion",
                "estado"
            )
            val datos: MutableList<List<String>> = mutableListOf()

            try {

                for (i in productos) {
                    val rowdata = mutableListOf<String>()
                    rowdata.add(i.id_inventario.toString())
                    rowdata.add(i.id_categoria.toString())
                    rowdata.add(i.id_proveedor.toString())
                    i.nombre?.let { rowdata.add(it) }
                    rowdata.add(i.modelo.toString())
                    rowdata.add(i.cantidad.toString())
                    rowdata.add(i.precio_compra.toString())
                    rowdata.add(i.precio_venta.toString())
                    rowdata.add(i.descripcion.toString())
                    rowdata.add(i.estado.toString())

                    datos.add(rowdata)
                }


            } catch (e: Exception) {
                println("Error en el repositorio recorriendo el flow")
            }


            return@withContext excel.crearXls(
                "AtenasInventario${System.currentTimeMillis()}",
                columnas,
                datos
            )
        }

    override suspend fun importarExcel(path: Uri): Boolean {
//Manejar excepcion de validacin de datos.
        val datos = excel.importarXlsx(path)
        val listaProductos: MutableList<inventario> = mutableListOf()
        if (validarNombresColumnas(datos[0])) {
            try {
                for ((index, i) in datos.withIndex()) {
                    if (index > 0) {
                        listaProductos.add(
                            inventario(
                                id_inventario = i[0].toLong(),
                                id_categoria = i[1].toLong(),
                                id_proveedor = i[2].toLong(),
                                nombre = i[3],
                                marca = i[4],
                                modelo = i[5],
                                cantidad = i[6].toInt(),
                                precio_compra = i[7].toDouble(),
                                precio_venta = i[8].toDouble(),
                                impuesto = i[9].toDouble(),
                                descripcion = i[10],
                                estado = true
                            )
                        )
                    }
                }



                daoProductos.addInventarios(listaProductos.toList())
                println("Insertando datos !!...$datos")
            } catch (e: Exception) {
                println("Error importando excel, ver formato: $e")
            }
        }

        return false
    }

    override suspend fun syncronizacionProductos() {
        mediador2()
    }

    override fun getCategorias(): Flow<List<categoria>> {
        return catego.getCategorias()
    }

    override suspend fun agregarCategorias(categoria: categoria) {
           catego.addCategoria(categoria)
    }

}

private fun validarNombresColumnas(columnas: List<String?>): Boolean {
    val nombresOrigin = listOf(
        "Code_Product",
        "Name_Product",
        "Description_Product",
        "Category_Product",
        "Price_Product",
        "Model_Product",
        "Price_Vending_Product",
        "Tracemark_Product",
        "Count_Product"
    )

    if (columnas.isNotEmpty()) {
        if (nombresOrigin == columnas) {
            return true
        }
    }
    println("Los datos son incorrectos... verifica")

    return false

}
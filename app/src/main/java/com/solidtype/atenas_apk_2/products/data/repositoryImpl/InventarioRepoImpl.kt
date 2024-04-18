package com.solidtype.atenas_apk_2.products.data.repositoryImpl

import android.net.Uri
import com.solidtype.atenas_apk_2.products.data.local.dao.ProductDao
import com.solidtype.atenas_apk_2.products.data.remoteProFB.mediator.MediatorProducts
import com.solidtype.atenas_apk_2.products.domain.model.ProductEntity
import com.solidtype.atenas_apk_2.products.domain.repository.InventarioRepo
import com.solidtype.atenas_apk_2.util.XlsManeger
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import javax.inject.Inject

class InventarioRepoImpl @Inject constructor(
    private val daoProductos: ProductDao, private val excel: XlsManeger,
    private val mediador2: MediatorProducts
) : InventarioRepo {


    override fun getProducts(): Flow<List<ProductEntity>> {

        return daoProductos.getProducts()

    }

    override fun getProductByCodigo(codigo: Int): Flow<List<ProductEntity>> {
        return daoProductos.getAllProductById(codigo)
    }

    override fun searchProductsLike(datos: String): Flow<List<ProductEntity>> {
        return daoProductos.getProductsLike(datos)
    }

    override suspend fun createProducts(prodcuto: ProductEntity): Boolean {
        daoProductos.insertPro(prodcuto)
        return true
    }

    override suspend fun deleteProduct(codigo: ProductEntity): Boolean {
        daoProductos.deleteProduct(codigo)
        return true//hay que manejarlo
    }

    override suspend fun updateProduct(producto: ProductEntity): Boolean {
        daoProductos.updateProduct(producto)
        return true //hay que manejarlo

    }

    override suspend fun exportarExcel(productos: List<ProductEntity>): Uri =
        withContext(Dispatchers.Default) {

            val columnas = listOf(
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
            val datos: MutableList<List<String>> = mutableListOf()

            try {

                for (i in productos) {
                    val rowdata = mutableListOf<String>()
                    rowdata.add(i.Code_Product.toString())
                    rowdata.add(i.Name_Product)
                    rowdata.add(i.Description_Product)
                    rowdata.add(i.Category_Product)
                    rowdata.add(i.Price_Product.toString())
                    rowdata.add(i.Model_Product)
                    rowdata.add(i.Price_Vending_Product.toString())
                    rowdata.add(i.Tracemark_Product)
                    rowdata.add(i.Count_Product.toString())

                    datos.add(rowdata)
                }


            } catch (e: Exception) {
                println("Error en el repositorio recorriendo el flow")
            }


            return@withContext excel.crearXls(
                "AtenasProductos${System.currentTimeMillis()}",
                columnas,
                datos
            )
        }

    override suspend fun importarExcel(path: Uri): Boolean {
//Manejar excepcion de validacin de datos.
        val datos = excel.importarXlsx(path)
        val listaProductos: MutableList<ProductEntity> = mutableListOf()
        if (validarNombresColumnas(datos[0])) {
            try {
                for ((index, i) in datos.withIndex()) {
                    if (index > 0) {
                        listaProductos.add(
                            ProductEntity(
                                Code_Product = i[0].toInt(),
                                Name_Product = i[1],
                                Description_Product = i[2],
                                Category_Product = i[3],
                                Price_Product = i[4].toDouble(),
                                Model_Product = i[5],
                                Price_Vending_Product = i[6].toDouble(),
                                Tracemark_Product = i[7],
                                Count_Product = i[8].toInt()
                            )
                        )
                    }
                }


                daoProductos.insertAllProducts(listaProductos)
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
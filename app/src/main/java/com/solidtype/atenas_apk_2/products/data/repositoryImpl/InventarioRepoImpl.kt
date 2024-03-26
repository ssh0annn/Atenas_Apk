package com.solidtype.atenas_apk_2.products.data.repositoryImpl

import com.solidtype.atenas_apk_2.products.data.local.dao.ProductDao
import com.solidtype.atenas_apk_2.products.data.remote.MediatorRemote.MediatorFbPrododucts
import com.solidtype.atenas_apk_2.products.domain.model.DataProductos
import com.solidtype.atenas_apk_2.products.domain.model.ProductEntity
import com.solidtype.atenas_apk_2.products.domain.repository.InventarioRepo
import com.solidtype.atenas_apk_2.util.XlsManeger
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import javax.inject.Inject

class InventarioRepoImpl @Inject constructor(
    private val daoProductos: ProductDao,
    private val excel: XlsManeger,
    private val mediador:MediatorFbPrododucts
):InventarioRepo {
    override  fun getProducts(): Flow<List<ProductEntity>> {

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

    override suspend fun exportarExcel(): String? {
        val productos =getProducts()
        var columnas= listOf(
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
         var datos:MutableList<List<String?>> = mutableListOf()

        try {
            productos.collect{value ->
                for (i in value){
                    var rowdata = mutableListOf<String?>()
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

            }
        }catch (e: Exception){
                println("Error en el repositorio recorriendo el flow")
        }


        return excel.crearXls("AtenasProductos${System.currentTimeMillis()}",columnas,datos)
    }

    override suspend fun importarExcel(path:String): Boolean {

          val datos =excel.importarXlsx(path)
        if(validarNombresColumnas(datos.get(1))){
            try {
                for((index, i) in datos.withIndex()){
                    if(index>0){
                        ProductEntity(
                            Code_Product = i.get(0).toInt(),
                            Name_Product = i.get(1),
                            Description_Product = i.get(2),
                            Category_Product = i.get(3),
                            Price_Product = i.get(4).toDouble(),
                            Model_Product = i.get(5),
                            Price_Vending_Product = i.get(6).toDouble(),
                            Tracemark_Product = i.get(7),
                            Count_Product = i.get(8).toInt())
                    }
                }

            }catch (e:Exception){
                println("Error importando excel, ver formato: $e")
            }
        }



        return false
    }

    override suspend fun syncronizacionProductos() {

        mediador.sync()
    }

    private fun validarNombresColumnas(columnas:List<String?>):Boolean{
        val nombresOrigin= listOf("Code_Product",
                "Name_Product",
            "Description_Product",
            "Category_Product",
            "Price_Product",
            "Model_Product",
            "Price_Vending_Product",
            "Tracemark_Product",
            "Count_Product")

        if(columnas.isNotEmpty()){
            if(nombresOrigin.equals(columnas)){
                return true
            }
        }
            return false
    }
}
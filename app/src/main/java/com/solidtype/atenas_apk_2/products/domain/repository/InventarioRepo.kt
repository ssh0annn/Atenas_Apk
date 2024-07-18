package com.solidtype.atenas_apk_2.products.domain.repository


import android.net.Uri
import com.solidtype.atenas_apk_2.products.domain.model.actualizacion.categoria
import com.solidtype.atenas_apk_2.products.domain.model.actualizacion.inventario
import kotlinx.coroutines.flow.Flow

interface InventarioRepo {
    fun getProducts(): Flow<List<inventario>>
    suspend fun getProductByCodigo(codigo:Int): inventario
    fun searchProductsLike(datos:String):Flow<List<inventario>>
    suspend fun createProducts(prodcuto: inventario):Boolean
    suspend fun deleteProduct(codigo:inventario):Boolean
    suspend fun deleteProductLista(listaProductos:List<inventario>)
    suspend fun updateProduct(producto:inventario): Boolean
    suspend fun exportarExcel(productos: List<inventario>):Uri
    suspend fun importarExcel(path:Uri):Boolean
    suspend fun syncronizacionProductos()

    fun getCategorias():Flow<List<categoria>>

    suspend fun agregarCategorias(categoria: categoria)
    suspend fun eliminarCategoria(categoria:categoria)
    suspend fun buscarCategorias(any:String):Flow<List<categoria>>


}
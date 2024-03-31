package com.solidtype.atenas_apk_2.products.domain.repository


import android.net.Uri
import com.solidtype.atenas_apk_2.products.domain.model.DataProductos
import com.solidtype.atenas_apk_2.products.domain.model.ProductEntity
import kotlinx.coroutines.flow.Flow

interface InventarioRepo {
    fun getProducts(): Flow<List<ProductEntity>>
    fun getProductByCodigo(codigo:Int): Flow<List<ProductEntity>>
    fun searchProductsLike(datos:String):Flow<List<ProductEntity>>
    suspend fun createProducts(prodcuto: ProductEntity):Boolean
    suspend fun deleteProduct(codigo:ProductEntity):Boolean
    suspend fun updateProduct(producto:ProductEntity): Boolean
    suspend fun exportarExcel(productos: List<ProductEntity>):Uri
    suspend fun importarExcel(path:Uri):Boolean
    suspend fun syncronizacionProductos()

}
package com.solidtype.atenas_apk_2.products.domain.repository

import com.solidtype.atenas_apk_2.products.domain.model.DataProductos
import kotlinx.coroutines.flow.Flow

interface InventarioRepo {
    fun getProducts(): Flow<List<DataProductos>>
    fun getProductByCodigo(codigo:Int): Flow<List<DataProductos>>
    fun searchProductsLike(datos:Any):Flow<List<DataProductos>>
    suspend fun createProducts(prodcuto: DataProductos):Boolean
    suspend fun deleteProduct(codigo:Int):Boolean
    suspend fun updateProduct(producto:DataProductos): Boolean

}
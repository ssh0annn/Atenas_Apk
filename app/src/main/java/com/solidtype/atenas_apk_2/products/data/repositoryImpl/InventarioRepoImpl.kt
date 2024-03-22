package com.solidtype.atenas_apk_2.products.data.repositoryImpl

import com.solidtype.atenas_apk_2.products.data.local.dao.ProductDao
import com.solidtype.atenas_apk_2.products.domain.model.DataProductos
import com.solidtype.atenas_apk_2.products.domain.model.ProductEntity
import com.solidtype.atenas_apk_2.products.domain.repository.InventarioRepo
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class InventarioRepoImpl @Inject constructor(
    private val daoProductos: ProductDao
):InventarioRepo {
    override fun getProducts(): Flow<List<DataProductos>> {
        TODO("Not yet implemented")
    }

    override fun getProductByCodigo(codigo: Int): Flow<List<DataProductos>> {
        TODO("Not yet implemented")
    }

    override fun searchProductsLike(datos: Any): Flow<List<DataProductos>> {
        TODO("Not yet implemented")
    }

    override suspend fun createProducts(prodcuto: ProductEntity): Boolean {
        daoProductos.insertPro(prodcuto)
        return true
    }

    override suspend fun deleteProduct(codigo: Int): Boolean {
        TODO("Not yet implemented")
    }

    override suspend fun updateProduct(producto: DataProductos): Boolean {
        TODO("Not yet implemented")
    }
}
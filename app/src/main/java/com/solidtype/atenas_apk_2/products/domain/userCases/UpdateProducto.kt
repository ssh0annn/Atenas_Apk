package com.solidtype.atenas_apk_2.products.domain.userCases

import com.solidtype.atenas_apk_2.products.domain.model.DataProductos
import com.solidtype.atenas_apk_2.products.domain.repository.InventarioRepo
import javax.inject.Inject

class UpdateProducto @Inject constructor(private val repo: InventarioRepo) {

    suspend operator fun invoke(productos: DataProductos)=repo.updateProduct(productos)
}
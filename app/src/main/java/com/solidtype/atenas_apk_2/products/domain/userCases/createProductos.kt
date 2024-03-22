package com.solidtype.atenas_apk_2.products.domain.userCases

import com.solidtype.atenas_apk_2.products.domain.model.DataProductos
import com.solidtype.atenas_apk_2.products.domain.model.ProductEntity
import com.solidtype.atenas_apk_2.products.domain.repository.InventarioRepo
import javax.inject.Inject

class createProductos @Inject constructor(private val repo: InventarioRepo) {
    suspend operator fun invoke(producto:ProductEntity)=repo.createProducts(producto)
}
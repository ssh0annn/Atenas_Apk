package com.solidtype.atenas_apk_2.products.domain.userCases

import com.solidtype.atenas_apk_2.products.domain.model.actualizacion.inventario
import com.solidtype.atenas_apk_2.products.domain.repository.InventarioRepo
import javax.inject.Inject

class createProductos @Inject constructor(private val repo: InventarioRepo) {
    suspend operator fun invoke(producto:inventario)=repo.createProducts(producto)
}
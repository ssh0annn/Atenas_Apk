package com.solidtype.atenas_apk_2.products.domain.userCases

import com.solidtype.atenas_apk_2.products.domain.model.ProductEntity
import com.solidtype.atenas_apk_2.products.domain.repository.InventarioRepo
import javax.inject.Inject

class ExportarExcel @Inject constructor(private val repo: InventarioRepo) {

    suspend operator fun invoke(productos:List<ProductEntity>)=repo.exportarExcel(productos)
}
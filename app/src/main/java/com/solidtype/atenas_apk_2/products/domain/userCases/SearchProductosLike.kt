package com.solidtype.atenas_apk_2.products.domain.userCases

import com.solidtype.atenas_apk_2.products.domain.repository.InventarioRepo
import javax.inject.Inject

class SearchProductosLike @Inject constructor(private val repo: InventarioRepo) {
    operator fun invoke(datos:Any)=repo.searchProductsLike(datos)
}
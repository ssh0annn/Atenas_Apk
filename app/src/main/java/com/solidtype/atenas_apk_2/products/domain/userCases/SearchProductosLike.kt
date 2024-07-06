package com.solidtype.atenas_apk_2.products.domain.userCases

import com.solidtype.atenas_apk_2.products.domain.repository.InventarioRepo
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class SearchProductosLike @Inject constructor(private val repo: InventarioRepo) {
    operator fun invoke(datos:String, estado:Boolean)=repo.searchProductsLike(datos).map { data -> data.filter { it.estado ==estado } }
}
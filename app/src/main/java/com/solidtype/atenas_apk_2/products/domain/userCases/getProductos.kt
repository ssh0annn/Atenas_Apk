package com.solidtype.atenas_apk_2.products.domain.userCases

import com.solidtype.atenas_apk_2.products.domain.repository.InventarioRepo
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class getProductos @Inject constructor(private val repo: InventarioRepo) {

      operator fun invoke(estado:Boolean)=repo.getProducts().map { data -> data.filter { it.estado == estado } }
}
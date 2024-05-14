package com.solidtype.atenas_apk_2.products.domain.userCases

import com.solidtype.atenas_apk_2.products.domain.repository.InventarioRepo
import javax.inject.Inject

class GetCategorias  @Inject constructor(private val repo: InventarioRepo){

     operator fun invoke() = repo.getCategorias()
}
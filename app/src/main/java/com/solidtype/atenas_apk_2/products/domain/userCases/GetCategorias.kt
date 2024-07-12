package com.solidtype.atenas_apk_2.products.domain.userCases

import com.solidtype.atenas_apk_2.products.domain.repository.InventarioRepo
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetCategorias  @Inject constructor(private val repo: InventarioRepo){

     operator fun invoke(estado:Boolean) = repo.getCategorias()
}
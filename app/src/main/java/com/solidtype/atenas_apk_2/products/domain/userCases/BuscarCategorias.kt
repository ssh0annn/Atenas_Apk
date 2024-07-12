package com.solidtype.atenas_apk_2.products.domain.userCases

import com.solidtype.atenas_apk_2.products.domain.repository.InventarioRepo
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class BuscarCategorias @Inject constructor(private val repo: InventarioRepo){

    suspend operator fun invoke(any:String) = repo.buscarCategorias(any)
}
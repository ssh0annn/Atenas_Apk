package com.solidtype.atenas_apk_2.products.domain.userCases

import com.solidtype.atenas_apk_2.products.domain.model.actualizacion.categoria
import com.solidtype.atenas_apk_2.products.domain.repository.InventarioRepo
import javax.inject.Inject

class CrearCategoria @Inject constructor(private val repo: InventarioRepo) {
    suspend operator fun invoke(catego:categoria) = repo.agregarCategorias(catego)
}
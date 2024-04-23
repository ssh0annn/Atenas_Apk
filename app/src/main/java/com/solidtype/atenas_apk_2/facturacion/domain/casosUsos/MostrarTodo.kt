package com.solidtype.atenas_apk_2.facturacion.domain.casosUsos

import com.solidtype.atenas_apk_2.facturacion.domain.repositorio.FacturaRepository
import javax.inject.Inject

class MostrarTodo @Inject constructor(private val repo: FacturaRepository) {

    operator  fun invoke() = repo.MostrarTodos()

}
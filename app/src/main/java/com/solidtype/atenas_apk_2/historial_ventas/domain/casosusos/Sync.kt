package com.solidtype.atenas_apk_2.historial_ventas.domain.casosusos

import com.solidtype.atenas_apk_2.historial_ventas.domain.repositories.HistorialRepository
import javax.inject.Inject

class Sync @Inject constructor(private val repo:HistorialRepository) {

    suspend operator fun invoke() = repo.sync()
}
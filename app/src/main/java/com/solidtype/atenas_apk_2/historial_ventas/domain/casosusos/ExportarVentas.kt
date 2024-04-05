package com.solidtype.atenas_apk_2.historial_ventas.domain.casosusos

import com.solidtype.atenas_apk_2.historial_ventas.domain.repositories.HistorialRepository
import com.solidtype.atenas_apk_2.historial_ventas.domain.model.HistorialVentaEntidad
import javax.inject.Inject

class ExportarVentas @Inject constructor(private val repo: HistorialRepository) {
    suspend operator fun invoke(listaProductos:List<HistorialVentaEntidad>) = repo.exportarVentas(listaProductos)
}
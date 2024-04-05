package com.solidtype.atenas_apk_2.historial_ventas.domain.casosusos

import com.solidtype.atenas_apk_2.historial_ventas.domain.repositories.HistorialRepository
import javax.inject.Inject

class BuscarporFechCatego @Inject constructor(private val repo: HistorialRepository) {

    operator fun invoke(fecha_inicio:String, fecha_final: String, categoria:String)=repo.buscarPorFechasCategoriasVentas(fecha_inicio, fecha_final, categoria)
}
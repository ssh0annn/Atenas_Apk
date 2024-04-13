package com.solidtype.atenas_apk_2.historial_ventas.domain.casosusos

import com.solidtype.atenas_apk_2.historial_ventas.domain.repositories.HistorialRepository
import javax.inject.Inject

class VerTicketsPorFechas @Inject constructor(private val repo: HistorialRepository) {

    operator fun invoke( fecha: String,
                         dias: Int)=repo.mostrarTicketsPorFecha(fecha, dias)

    }
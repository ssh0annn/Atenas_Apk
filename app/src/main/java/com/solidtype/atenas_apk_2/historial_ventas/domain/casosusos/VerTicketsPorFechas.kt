package com.solidtype.atenas_apk_2.historial_ventas.domain.casosusos

import com.solidtype.atenas_apk_2.historial_ventas.domain.repositories.HistorialRepository
import java.time.LocalDate
import javax.inject.Inject

class VerTicketsPorFechas @Inject constructor(private val repo: HistorialRepository) {


    operator fun invoke(  fechaIni: LocalDate,
                          fechaFinal:LocalDate,
                          )=repo.mostrarTicketsPorFecha(fechaIni, fechaFinal)


    }
package com.solidtype.atenas_apk_2.facturacion.domain.casosUsos

import com.solidtype.atenas_apk_2.facturacion.domain.repositorio.FacturaRepository
import java.time.LocalDate
import javax.inject.Inject

class BuscarFacturas @Inject constructor(private val repo:FacturaRepository ) {
    operator fun invoke(fechaini:LocalDate, fechafinal:LocalDate, datoSemejante:String)= repo.busqueda(fechaini, fechafinal, datoSemejante)
}
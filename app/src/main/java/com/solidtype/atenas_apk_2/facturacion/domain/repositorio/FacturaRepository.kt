package com.solidtype.atenas_apk_2.facturacion.domain.repositorio

import kotlinx.coroutines.flow.Flow
import java.time.LocalDate

interface FacturaRepository{

    fun busqueda(fechaini:LocalDate, fechafinal:LocalDate, datoSemejante:String): Flow<List<List<String>>>

    fun MostrarTodos():Flow<List<List<String>>>

    fun DetalleFactura(numeroFactura:Long):Flow<List<List<String>>>


}
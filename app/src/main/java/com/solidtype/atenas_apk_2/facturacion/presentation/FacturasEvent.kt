package com.solidtype.atenas_apk_2.facturacion.presentation

import java.time.LocalDate

sealed class FacturasEvent {
    data object GetFacturas:FacturasEvent()
    data class BuscarFacturas(val semejante:String, val desde:LocalDate, val hasta:LocalDate ):FacturasEvent()
    object LimpiarMensaje:FacturasEvent()

}
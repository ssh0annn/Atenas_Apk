package com.solidtype.atenas_apk_2.servicios.presentation.modelo

enum class FormaPagos(val descripcion:String) {

    CREDITO("Credito"),
    EFECTIVO("Efectivo"),
    CREDIT_CARD("CR Card"),
    CREDIT_DEBIT("DB Card"),
    TRANSATIONS("Transferencia");

    override fun toString():String{
        return descripcion
    }

}

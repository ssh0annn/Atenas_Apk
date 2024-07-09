package com.solidtype.atenas_apk_2.core.entidades

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDate

@Entity
data class tipo_venta (
    @PrimaryKey(autoGenerate = true) var id_tipo_venta :Long = 0L,
    val tipo_pago :String = "Efectivo",
    val numeroTransaccion:String = "",
    val tipo_comprobante :String = "",
    val serie_comprobante :String = "",
    val num_comprobate :String = "",
    val presupuesto :Double =0.0,
    val descuento :Double = 0.0,
    var subtotal :Double = 0.0,
    var impuesto :Double = 0.0,
    var total :Double = 0.0,
    var abono :Double = 0.0,
    var restantante:Double = 0.0,
    val fecha:LocalDate = LocalDate.now(),
    @ColumnInfo(defaultValue = "true") val estado :Boolean = true
)
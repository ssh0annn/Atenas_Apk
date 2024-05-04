package com.solidtype.atenas_apk_2.core.entidades

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class tipo_venta (
    @PrimaryKey(autoGenerate = true) val id_tipo_venta :Long,
    val nombre :String,
    val tiene_descuento :Boolean,
    val descuento :Double?,
    val tipo_comprobante :String?,
    val serie_comprobante :String?,
    val num_comprobate :String?,
    @ColumnInfo(defaultValue = "true") val estado :Boolean
)
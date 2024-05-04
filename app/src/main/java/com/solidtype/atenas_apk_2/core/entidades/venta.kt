package com.solidtype.atenas_apk_2.core.entidades

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.solidtype.atenas_apk_2.core.entidades.inventario

@Entity(foreignKeys = [
    ForeignKey(entity = inventario::class, parentColumns = ["id_inventario"], childColumns = ["id_producto"])
])
data class venta (
    @PrimaryKey(autoGenerate = true) val id_venta :Long,
    val id_producto :Long,
    val codigo :Int,
    val subtotal :Double,
    val impuesto :Double,
    val total :Double,
    val cantidad :Int,
    @ColumnInfo(defaultValue = "true") val estado :Boolean
)
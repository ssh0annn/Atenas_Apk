package com.solidtype.atenas_apk_2.facturacion.domain.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import com.solidtype.atenas_apk_2.historial_ventas.domain.model.actualizacion.venta
import com.solidtype.atenas_apk_2.products.domain.model.actualizacion.inventario

@Entity(foreignKeys = [
    ForeignKey(entity = venta::class, parentColumns = ["id_venta"], childColumns = ["id_venta"], onDelete = ForeignKey.CASCADE),
    ForeignKey(entity = inventario::class, parentColumns = ["id_inventario"], childColumns = ["id_producto"], onDelete = ForeignKey.CASCADE)
], indices = [
    Index(value = ["id_venta"], unique = true),
    Index(value = ["id_producto"], unique = true)
])
data class detalle_venta (

    @PrimaryKey(autoGenerate = true) val id_detalle_venta :Long,
    val id_venta :Long,
    val id_producto :Long,
    val cantidad :Int,
    val total :Double,
    @ColumnInfo(defaultValue = "true") val estado :Boolean
)
package com.solidtype.atenas_apk_2.facturacion.domain.model

import androidx.room.Embedded
import androidx.room.Relation
import com.solidtype.atenas_apk_2.products.domain.model.actualizacion.inventario

data class DetalleRelacional(
    @Embedded val detalleVenta: detalle_venta,
    @Relation(
        parentColumn = "id_producto",
        entityColumn = "id_inventario"
    )
    val producto: inventario
)

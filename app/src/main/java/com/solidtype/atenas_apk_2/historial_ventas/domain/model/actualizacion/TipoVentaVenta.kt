package com.solidtype.atenas_apk_2.historial_ventas.domain.model.actualizacion

import androidx.room.Embedded
import androidx.room.Relation
import com.solidtype.atenas_apk_2.core.entidades.tipo_venta

data class TipoVentaVenta(
    @Embedded val venta: venta,
    @Relation(
        parentColumn = "id_tipo_venta",
        entityColumn = "id_tipo_venta"
    )
    val tipoVenta: tipo_venta
)

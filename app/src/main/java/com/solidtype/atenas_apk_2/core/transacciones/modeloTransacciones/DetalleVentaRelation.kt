package com.solidtype.atenas_apk_2.core.transacciones.modeloTransacciones

import androidx.room.Embedded
import androidx.room.Relation
import com.solidtype.atenas_apk_2.core.entidades.tipo_venta
import com.solidtype.atenas_apk_2.facturacion.domain.model.detalle_venta
import com.solidtype.atenas_apk_2.historial_ventas.domain.model.actualizacion.venta
import com.solidtype.atenas_apk_2.products.domain.model.actualizacion.inventario

class DetalleVentaRelation (
    @Embedded val detalleVenta: detalle_venta,

    @Relation(
        parentColumn = "id_venta",
        entityColumn = "id_venta"
    )
    val venta: venta,

    @Relation(
        parentColumn = "id_producto",
        entityColumn = "id_inventario"
    )
    val inventario: inventario,

    @Relation(
        parentColumn = "id_tipo_venta",
        entityColumn = "id_tipo_venta"
    )
    val tipo_venta: tipo_venta,

    )
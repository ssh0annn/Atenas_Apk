package com.solidtype.atenas_apk_2.core.Transacciones.ModeloTransacciones

import androidx.room.Embedded
import androidx.room.Relation
import com.solidtype.atenas_apk_2.facturacion.domain.model.detalle_venta
import com.solidtype.atenas_apk_2.gestion_proveedores.data.persona
import com.solidtype.atenas_apk_2.gestion_usuarios.domain.modelo.usuario
import com.solidtype.atenas_apk_2.products.domain.model.ProductEntity
import com.solidtype.atenas_apk_2.products.domain.model.actualizacion.categoria
import com.solidtype.atenas_apk_2.products.domain.model.actualizacion.inventario

data class InventarioModeloRelation(
    @Embedded val inventario: inventario,

    @Relation(
        parentColumn = "id_inventario",
        entityColumn = "id_detalle_venta",
    )
    val detalle: detalle_venta,

    @Relation(
        parentColumn = "id_categoria",
        entityColumn = "id_categoria",
    )
    val categoria: categoria,

    @Relation(
        parentColumn = "id_proveedor",
        entityColumn = "id_persona",
    )
    val provedor: persona
)
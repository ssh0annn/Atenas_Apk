package com.solidtype.atenas_apk_2.core.transacciones.modeloTransacciones

import androidx.room.Embedded
import androidx.room.Relation
import com.solidtype.atenas_apk_2.gestion_proveedores.data.persona
import com.solidtype.atenas_apk_2.products.domain.model.actualizacion.categoria
import com.solidtype.atenas_apk_2.products.domain.model.actualizacion.inventario

data class InventarioModeloRelation(
    @Embedded val inventario: inventario,

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
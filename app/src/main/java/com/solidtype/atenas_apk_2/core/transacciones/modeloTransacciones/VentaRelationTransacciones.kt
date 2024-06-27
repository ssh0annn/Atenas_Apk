package com.solidtype.atenas_apk_2.core.transacciones.modeloTransacciones

import androidx.room.Embedded
import androidx.room.Relation
import com.solidtype.atenas_apk_2.core.entidades.tipo_venta
import com.solidtype.atenas_apk_2.gestion_proveedores.data.persona
import com.solidtype.atenas_apk_2.gestion_usuarios.domain.modelo.usuario
import com.solidtype.atenas_apk_2.historial_ventas.domain.model.actualizacion.venta

data class  VentaRelationTransacciones (
    @Embedded val venta: venta,

    @Relation(
        parentColumn = "id_vendedor",
        entityColumn = "id_usuario"
    )
    val vendedor: usuario,

    @Relation(
        parentColumn = "id_cliente",
        entityColumn = "id_persona"
    )
    val cliente: persona,

    @Relation(
        parentColumn = "id_tipo_venta",
        entityColumn = "id_tipo_venta"
    )
    val tipo_venta: tipo_venta,

    )
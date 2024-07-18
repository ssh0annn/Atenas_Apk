package com.solidtype.atenas_apk_2.historial_ventas.domain.model.actualizacion

import androidx.room.Embedded
import androidx.room.Relation
import com.solidtype.atenas_apk_2.core.entidades.tipo_venta
import com.solidtype.atenas_apk_2.gestion_tickets.domain.model.ticket
import com.solidtype.atenas_apk_2.servicios.modelo.servicio

data class TipoVentaTicket(
    @Embedded val venta: ticket,
    @Relation(
        parentColumn = "id_tipo_venta",
        entityColumn = "id_tipo_venta"
    )
    val tipoVenta: tipo_venta,
    @Relation(
        parentColumn = "id_servicio",
        entityColumn = "id_servicio",
        entity = servicio::class,
        projection = ["nombre"]
    )
    val servicios: String
)

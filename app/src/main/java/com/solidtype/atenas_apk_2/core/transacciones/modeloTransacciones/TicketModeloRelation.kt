package com.solidtype.atenas_apk_2.core.transacciones.modeloTransacciones

import androidx.room.Embedded
import androidx.room.Relation
import com.solidtype.atenas_apk_2.core.entidades.tipo_venta
import com.solidtype.atenas_apk_2.dispositivos.model.Dispositivo
import com.solidtype.atenas_apk_2.gestion_proveedores.data.persona
import com.solidtype.atenas_apk_2.gestion_tickets.domain.model.ticket
import com.solidtype.atenas_apk_2.gestion_usuarios.domain.modelo.usuario
import com.solidtype.atenas_apk_2.servicios.modelo.servicio

data class TicketModeloRelation(
    @Embedded val ticket: ticket,

    @Relation(
        parentColumn = "id_vendedor",
        entityColumn = "id_usuario"
    )
    val vendedor: usuario,

    @Relation(
        parentColumn = "id_servicio",
        entityColumn = "id_servicio"
    )
    val servicio: servicio,

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

    @Relation(
        parentColumn = "id_dispositivo",
        entityColumn = "id_dispositivo"
    )
    val dispositivo: Dispositivo,

    )

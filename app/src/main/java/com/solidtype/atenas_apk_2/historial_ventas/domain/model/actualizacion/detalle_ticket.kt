package com.solidtype.atenas_apk_2.historial_ventas.domain.model.actualizacion

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.solidtype.atenas_apk_2.core.entidades.persona
import com.solidtype.atenas_apk_2.core.entidades.ticket
import com.solidtype.atenas_apk_2.core.entidades.tipo_venta
import com.solidtype.atenas_apk_2.core.entidades.usuario

@Entity(foreignKeys = [
    ForeignKey(entity = ticket::class, parentColumns = ["id_ticket"], childColumns = ["codigo_ticket"]),
    ForeignKey(entity = usuario::class, parentColumns = ["id_usuario"], childColumns = ["id_vendedor"]),
    ForeignKey(entity = persona::class, parentColumns = ["id_persona"], childColumns = ["id_cliente"]),
    ForeignKey(entity = tipo_venta::class, parentColumns = ["id_tipo_venta"], childColumns = ["id_tipo_venta"]),
])
data class detalle_ticket (
    @PrimaryKey(autoGenerate = true) val id_detalle_ticket :Long,
    val codigo_ticket :Long,
    val id_vendedor :Long,
    val id_cliente :Long,
    val id_tipo_venta :Long,
    val codigo :Int,
    val estado_equipo :String,
    val falta_equipo :String,
    val nota :String,
    val cantidad :Int,
    val total :Double,
    val fecha_inicio :String,
    val fecha_final :String,
    @ColumnInfo(defaultValue = "true") val estado :Boolean
)
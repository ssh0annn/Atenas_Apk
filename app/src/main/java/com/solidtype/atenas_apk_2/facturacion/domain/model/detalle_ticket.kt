package com.solidtype.atenas_apk_2.facturacion.domain.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.solidtype.atenas_apk_2.servicios.modelo.servicio
import com.solidtype.atenas_apk_2.historial_ventas.domain.model.actualizacion.ticket

@Entity(foreignKeys = [
    ForeignKey(entity = servicio::class, parentColumns = ["id_servicio"], childColumns = ["tipo_servicio"]),
    ForeignKey(entity = ticket::class, parentColumns = ["id_ticket"], childColumns = ["id_ticket"], onDelete = ForeignKey.CASCADE)
])
data class detalle_ticket (
    @PrimaryKey(autoGenerate = true) val id_detalle_ticket :Long,
    val id_ticket :Long,
    val tipo_servicio :Long,
    val estado_equipo :String,
    val falta_equipo :String,
    val nota :String?,
    val cantidad :Int,
    val total :Double?,
    @ColumnInfo(defaultValue = "true") val estado :Boolean
)
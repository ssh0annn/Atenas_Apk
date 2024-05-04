package com.solidtype.atenas_apk_2.historial_ventas.domain.model.actualizacion

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.solidtype.atenas_apk_2.core.entidades.persona
import com.solidtype.atenas_apk_2.core.entidades.tipo_venta
import com.solidtype.atenas_apk_2.core.entidades.usuario
import com.solidtype.atenas_apk_2.core.entidades.venta

@Entity(foreignKeys = [
    ForeignKey(entity = venta::class, parentColumns = ["id_venta"], childColumns = ["codigo_venta"]),
    ForeignKey(entity = usuario::class, parentColumns = ["id_usuario"], childColumns = ["id_vendedor"]),
    ForeignKey(entity = persona::class, parentColumns = ["id_persona"], childColumns = ["id_cliente"]),
    ForeignKey(entity = tipo_venta::class, parentColumns = ["id_tipo_venta"], childColumns = ["id_tipo_venta"]),
])
data class detalle_venta (
    @PrimaryKey(autoGenerate = true) val id_detalle_venta :Long,
    val codigo_venta :Long,
    val id_vendedor :Long,
    val id_cliente :Long,
    val id_tipo_venta :Long,
    val codigo :Int,
    val cantidad :Int,
    val total :Double,
    val fecha :String,
    @ColumnInfo(defaultValue = "true") val estado :Boolean
)
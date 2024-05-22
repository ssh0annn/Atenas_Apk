package com.solidtype.atenas_apk_2.historial_ventas.domain.model.actualizacion

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.solidtype.atenas_apk_2.core.entidades.Dispositivo
import com.solidtype.atenas_apk_2.gestion_proveedores.domain.modelo.persona
import com.solidtype.atenas_apk_2.core.entidades.tipo_venta
import com.solidtype.atenas_apk_2.gestion_usuarios.domain.modelo.usuario
import java.time.LocalDate

@Entity(foreignKeys = [
    ForeignKey(entity = usuario::class, parentColumns = ["id_usuario"], childColumns = ["id_vendedor"]),
    ForeignKey(entity = persona::class, parentColumns = ["id_persona"], childColumns = ["id_cliente"]),
    ForeignKey(entity = tipo_venta::class, parentColumns = ["id_tipo_venta"], childColumns = ["id_tipo_venta"]),
    ForeignKey(entity = Dispositivo::class, parentColumns = ["id_dispositivo"], childColumns = ["id_dispositivo"])
])
data class ticket (
    @PrimaryKey(autoGenerate = true) val id_ticket :Long,
    val id_vendedor :Long,
    val id_cliente :Long,
    val id_tipo_venta :Long,
    val id_dispositivo :Long,
    val imei :String,
    val falla :String,
    val descripcion :String,
    val nota :String,
    val assesorios :String,
    val total :Double,
    val abono :Double,
    val presupuesto :Double,
    val subtotal :Double,
    val impuesto :Double,
    val fecha_inicio : LocalDate,
    val fecha_final : LocalDate,
    @ColumnInfo(defaultValue = "true") val estado :Boolean
)
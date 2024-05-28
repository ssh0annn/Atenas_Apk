package com.solidtype.atenas_apk_2.gestion_tickets.domain.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.solidtype.atenas_apk_2.dispositivos.model.Dispositivo
import com.solidtype.atenas_apk_2.gestion_proveedores.data.persona
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
   @PrimaryKey(autoGenerate = false) var id_ticket :Long= 0 ,
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
    var abono :Double,
    val presupuesto :Double,
    val subtotal :Double,
    val impuesto :Double,
    var fecha_inicio : LocalDate,
    var fecha_final : LocalDate,
    @ColumnInfo(defaultValue = "true") var estado :Boolean
)


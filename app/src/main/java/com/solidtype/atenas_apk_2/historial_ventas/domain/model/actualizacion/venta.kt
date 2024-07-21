package com.solidtype.atenas_apk_2.historial_ventas.domain.model.actualizacion

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import com.solidtype.atenas_apk_2.gestion_proveedores.data.persona
import com.solidtype.atenas_apk_2.core.entidades.tipo_venta
import com.solidtype.atenas_apk_2.gestion_usuarios.domain.modelo.usuario
import java.time.LocalDate

@Entity(foreignKeys = [
    ForeignKey(entity = usuario::class, parentColumns = ["id_usuario"], childColumns = ["id_vendedor"], onDelete = ForeignKey.CASCADE),
    ForeignKey(entity = persona::class, parentColumns = ["id_persona"], childColumns = ["id_cliente"], onDelete = ForeignKey.CASCADE),
    ForeignKey(entity = tipo_venta::class, parentColumns = ["id_tipo_venta"], childColumns = ["id_tipo_venta"], onDelete = ForeignKey.CASCADE),
], indices = [
    Index(value = ["id_vendedor"], unique = false),
    Index(value = ["id_cliente"], unique = false),
    Index(value = ["id_tipo_venta"], unique = false)
])
data class venta (
    @PrimaryKey(autoGenerate = true) val id_venta :Long,
    val id_vendedor :Long,
    val id_cliente :Long,
    val id_tipo_venta :Long,
    val cantidad :Int, //Cantidad articulos
    val fecha : LocalDate,
    @ColumnInfo(defaultValue = "true") val estado :Boolean
)

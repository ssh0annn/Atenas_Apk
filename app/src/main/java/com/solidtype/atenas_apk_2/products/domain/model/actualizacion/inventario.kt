package com.solidtype.atenas_apk_2.products.domain.model.actualizacion

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.solidtype.atenas_apk_2.core.entidades.persona

@Entity(foreignKeys = [
    ForeignKey(entity = categoria::class, parentColumns = ["id_categoria"], childColumns = ["id_categoria"]),
    ForeignKey(entity = persona::class, parentColumns = ["id_persona"], childColumns = ["id_proveedor"])
])
data class inventario (
    @PrimaryKey(autoGenerate = true) val id_inventario :Long = 7000,
    val id_categoria :Long,
    val id_proveedor :Long?,
    val nombre :String,
    val marca :String?,
    val modelo :String?,
    val cantidad :Int,
    val precio_compra :Double,
    val precio_venta :Double,
    val impuesto : Double,
    val descripcion :String?,
    @ColumnInfo(defaultValue = "true") val estado :Boolean
)
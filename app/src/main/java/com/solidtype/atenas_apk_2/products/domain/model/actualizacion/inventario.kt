package com.solidtype.atenas_apk_2.products.domain.model.actualizacion

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import com.solidtype.atenas_apk_2.gestion_proveedores.data.persona

@Entity(foreignKeys = [
    ForeignKey(entity = categoria::class, parentColumns = ["id_categoria"], childColumns = ["id_categoria"], onDelete = ForeignKey.CASCADE),
    ForeignKey(entity = persona::class, parentColumns = ["id_persona"], childColumns = ["id_proveedor"], onDelete = ForeignKey.CASCADE)
], indices = [
    Index(value = ["id_categoria"], unique = false),
    Index(value = ["id_proveedor"], unique = false)
])
data class inventario (
    @PrimaryKey(autoGenerate = true) val id_inventario :Long? = null,
    val id_categoria :Long,
    val id_proveedor :Long?,
    val nombre :String?,
    val marca :String?,
    val modelo :String?,
    val cantidad :Int,
    val precio_compra :Double,
    val precio_venta :Double,
    val impuesto : Double,
    val descripcion :String?,
    @ColumnInfo(defaultValue = "true") var estado :Boolean
){
    constructor():this(
        0,
        0,
        0,
        null,
        null,
        null,
        0,
        0.0,
        0.0,
        0.0,
        null,
        true
    )
}
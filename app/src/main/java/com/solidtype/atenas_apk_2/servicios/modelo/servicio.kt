package com.solidtype.atenas_apk_2.servicios.modelo

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class servicio (
    @PrimaryKey(autoGenerate = true) val id_servicio : Long =0,
    val nombre :String,
    val descripcion :String?,
    @ColumnInfo(defaultValue = "true") val estado :Boolean
)

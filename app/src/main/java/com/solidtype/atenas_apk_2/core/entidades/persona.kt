package com.solidtype.atenas_apk_2.core.entidades

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class persona (
    @PrimaryKey(autoGenerate = true) val id_persona :Long,
    val tipo_persona :String,
    val nombre :String,
    val tipo_documento :String,
    val documento :String,
    val direccion :String?,
    val telefono :String,
    val email :String,
    @ColumnInfo(defaultValue = "true") val estado :Boolean
)
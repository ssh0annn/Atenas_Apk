package com.solidtype.atenas_apk_2.core.entidades

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class roll_usuarios (
    @PrimaryKey(autoGenerate = true) val id_roll_usuario : Long,
    val nombre : String,
    val descripcion : String?,
    @ColumnInfo(defaultValue = "true") val estado : Boolean
)
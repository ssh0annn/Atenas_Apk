package com.solidtype.atenas_apk_2.core.entidades

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(foreignKeys = [
    ForeignKey(entity = roll_usuarios::class, parentColumns = ["id_roll_usuario"], childColumns = ["id_roll_usuario"])
])
data class usuario (
    @PrimaryKey(autoGenerate = true) val id_usuario :Long,
    val id_roll_usuario :Long,
    val nombre :String, // vendedor, tecnico
    val apellido :String,
    val email :String,
    val direccion_negocio :String?,
    val nombre_negocio :String?,
    val licencia :String?,
    val clave :String,
    val telefono :String,
    @ColumnInfo(defaultValue = "true") val estado :Boolean
)
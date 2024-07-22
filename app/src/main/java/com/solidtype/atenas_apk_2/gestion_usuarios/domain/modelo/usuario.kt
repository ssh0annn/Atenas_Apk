package com.solidtype.atenas_apk_2.gestion_usuarios.domain.modelo

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class usuario (
    @PrimaryKey(autoGenerate = true) val id_usuario :Long = 0,
    val nombre :String, // vendedor, tecnico
    val apellido :String,
    val email :String,
    val clave :String,
    val telefono :String,
    @ColumnInfo(defaultValue = "true") var estado :Boolean
)
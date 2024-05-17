package com.solidtype.atenas_apk_2.gestion_usuarios.domain.modelo

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class roll_usuarios (
    @PrimaryKey(autoGenerate = true) val id_roll_usuario : Long, //
    val nombre : String, //roll: administrador, vendedor, tecnico
    val descripcion : String, //
    @ColumnInfo(defaultValue = "true") val estado : Boolean
)

package com.solidtype.atenas_apk_2.perfil_administrador.domain.modelo

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDate

@Entity
data class administrador(
    @PrimaryKey(autoGenerate = true) val id_administrador :Long = 0,
    val nombre :String,
    val apellido :String,
    val correo :String?,
    val telefono:String,
    val clave :String?,
    val direccion_negocio :String,
    val nombre_negocio :String,
    val licencia :String?,
    val fecha_compra :LocalDate?,
    val fecha_caduca :LocalDate?,
    @ColumnInfo(defaultValue = "true") val estado :Boolean?
)
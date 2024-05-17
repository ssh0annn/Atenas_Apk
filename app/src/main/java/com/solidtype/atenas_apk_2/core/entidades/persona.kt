package com.solidtype.atenas_apk_2.core.entidades

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class persona (
    @PrimaryKey(autoGenerate = true) val id_persona :Long = 0,
    val tipo_persona :String,//proveedor, cliente
    val nombre :String,
    val tipo_documento :String,  //cedula, RNC, DNI
    val documento :String, //22838238, numero del documento
    val direccion :String?,
    val telefono :String,
    val email :String,
    @ColumnInfo(defaultValue = "true") val estado :Boolean
)
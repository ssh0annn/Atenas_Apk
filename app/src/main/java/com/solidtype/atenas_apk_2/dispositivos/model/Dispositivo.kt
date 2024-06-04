package com.solidtype.atenas_apk_2.dispositivos.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Dispositivo (
    @PrimaryKey(autoGenerate = true) val id_dispositivo : Long = 10000,
    val nombre_comercial : String,
    val modelo :String,
    val marca :String,
    @ColumnInfo(defaultValue = "true") val estado :Boolean
)
package com.solidtype.atenas_apk_2.core.entidades

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Dispositivo (
    @PrimaryKey(autoGenerate = true) val id_dispositivo : Long,
    val nombre_comercial : String,
    val modelo :String,
    val marca :String
)
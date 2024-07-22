package com.solidtype.atenas_apk_2.core.entidades

import androidx.room.Entity

@Entity(primaryKeys = ["idUsuario", "idRollUsuario"])
data class relacion_usuario_roll (
    val idUsuario : Long,
    val idRollUsuario : Long
)
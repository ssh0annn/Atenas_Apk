package com.solidtype.atenas_apk_2.core.transacciones.modeloTransacciones

import androidx.room.Embedded
import androidx.room.Relation
import com.solidtype.atenas_apk_2.gestion_usuarios.domain.modelo.roll_usuarios
import com.solidtype.atenas_apk_2.gestion_usuarios.domain.modelo.usuario

data class UsuariosRelation (
    @Embedded val usuarios: usuario,

    @Relation(
        parentColumn = "id_roll_usuario",
        entityColumn = "id_roll_usuario",
    )
    val roll_usuario: roll_usuarios,
)
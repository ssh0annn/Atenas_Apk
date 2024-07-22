package com.solidtype.atenas_apk_2.core.entidades

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import com.solidtype.atenas_apk_2.gestion_usuarios.domain.modelo.usuario

data class RollWithUsuario(
    @Embedded val roll: roll_usuarios,
    @Relation(
        parentColumn = "idRollUsuario",
        entityColumn = "idUsuario",
        associateBy = Junction(relacion_usuario_roll::class)
    )
    val usuarios: List<usuario>
)

data class UsuarioWithRoll(
    @Embedded val usuario: usuario,
    @Relation(
        parentColumn = "idUsuario",
        entityColumn = "idRollUsuario",
        associateBy = Junction(relacion_usuario_roll::class)
    )
    val roles: List<roll_usuarios>
)
package com.solidtype.atenas_apk_2.core.daos

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import com.solidtype.atenas_apk_2.core.entidades.RollWithUsuario
import com.solidtype.atenas_apk_2.core.entidades.UsuarioWithRoll

@Dao
interface relacion_usuario_rollDao {

    @Transaction
    @Query("SELECT * FROM roll_usuarios")
    fun getRollUsuariosByRelacion(): List<RollWithUsuario>

    @Transaction
    @Query("SELECT * FROM usuario")
    fun getUsuariosByRelacion(): List<UsuarioWithRoll>
}
package com.solidtype.atenas_apk_2.gestion_usuarios.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import com.solidtype.atenas_apk_2.gestion_usuarios.domain.modelo.usuario
import kotlinx.coroutines.flow.Flow

@Dao
interface usuarioDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addUsuario(usuario : usuario)
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addUsuarios(usuario : List<usuario>)
    @Query("select * from usuario")
    fun getUsuarios(): Flow<List<usuario>>
    @Query("select * from usuario where id_usuario ==:id")
    suspend fun getUsuariosById(id :Int): usuario
    @Update
    suspend fun updateUsuario(usuario : usuario)
    @Transaction
    suspend fun deleteUsuario(usuario : usuario){
        usuario.estado = false
        updateUsuario(usuario)
    }
    @Query("""
        SELECT * FROM usuario WHERE 
            id_usuario LIKE '%' || :any ||   '%' OR 
         nombre  LIKE '%' || :any ||   '%' OR
         apellido  LIKE '%' || :any ||   '%' OR
         email  LIKE '%' || :any ||   '%' OR
         clave  LIKE '%' || :any ||   '%' OR
         telefono  LIKE '%' || :any ||   '%'
        """)
    fun getUsuariosBySimilar(any :String): Flow<List<usuario>>
}
package com.solidtype.atenas_apk_2.core.daos

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.solidtype.atenas_apk_2.core.entidades.categoria

@Dao
interface categoriaDao {
    @Insert
    suspend fun addCategoria(categoria : categoria)
    @Insert
    suspend fun addCategorias(categoria : List<categoria>)
    @Query("select * from categoria")
    suspend fun getCategorias():List<categoria>
    @Query("select * from categoria where id_categoria ==:id")
    suspend fun getCategoriasById(id :Int):categoria
    @Update
    suspend fun updateCategoria(categoria: categoria)
    @Delete
    suspend fun deleteCategoria(categoria: categoria)
}
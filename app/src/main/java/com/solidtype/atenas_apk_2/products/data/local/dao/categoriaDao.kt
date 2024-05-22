package com.solidtype.atenas_apk_2.products.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.solidtype.atenas_apk_2.products.domain.model.actualizacion.categoria
import kotlinx.coroutines.flow.Flow

@Dao
interface categoriaDao {
    @Insert
    suspend fun addCategoria(categoria : categoria)
    @Insert
    suspend fun addCategorias(categoria : List<categoria>)
    @Query("select * from categoria")
    fun getCategorias(): Flow<List<categoria>>
    @Query("select * from categoria where id_categoria ==:id")
    suspend fun getCategoriasById(id :Int): categoria

    @Query("SELECT * FROM categoria WHERE nombre LIKE '%' || :catego || '%'")
    fun buscarCategorias(catego:String): categoria
    @Update
    suspend fun updateCategoria(categoria: categoria)
    @Delete
    suspend fun deleteCategoria(categoria: categoria)
}
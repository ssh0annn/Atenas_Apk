package com.solidtype.atenas_apk_2.products.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.solidtype.atenas_apk_2.products.domain.model.actualizacion.inventario

@Dao
interface inventarioDao {
    @Insert
    suspend fun addInventario(inventario : inventario)
    @Insert
    suspend fun addInventarios(inventario : List<inventario>)
    @Query("select * from inventario")
    suspend fun getInventario():List<inventario>
    @Query("select * from inventario where id_inventario ==:id")
    suspend fun getInventariosById(id :Int): inventario
    @Update
    suspend fun updateInventario(inventario: inventario)
    @Delete
    suspend fun deleteInventario(inventario: inventario)
}
package com.solidtype.atenas_apk_2.core.daos

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.solidtype.atenas_apk_2.core.entidades.tipo_venta
import kotlinx.coroutines.flow.Flow

@Dao
interface tipo_ventaDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addTipoVenta(tipoVenta: tipo_venta)
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addTipoVentas(tipoVenta : List<tipo_venta>)
    @Query("select * from tipo_venta")
    fun getTipoVentas(): Flow<List<tipo_venta>>
    @Query("select * from tipo_venta where id_tipo_venta ==:id")
    suspend fun getTipoVentasById(id :Int): tipo_venta
    @Update
    suspend fun updateTipoVenta(tipoVenta : tipo_venta)
    @Delete
    suspend fun deleteTipoVenta(tipoVenta : tipo_venta)
}
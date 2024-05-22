package com.solidtype.atenas_apk_2.core.daos

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.solidtype.atenas_apk_2.core.entidades.tipo_venta
import kotlinx.coroutines.flow.Flow

@Dao
interface tipo_ventaDao {
    @Insert
    fun addTipoVenta(tipoVenta: tipo_venta)
    @Insert
    fun addTipoVentas(tipoVenta : List<tipo_venta>)
    @Query("select * from tipo_venta")
    fun getTipoVentas(): Flow<List<tipo_venta>>
    @Query("select * from tipo_venta where id_tipo_venta ==:id")
    fun getTipoVentasById(id :Int): tipo_venta
    @Update
    fun updateTipoVenta(tipoVenta : tipo_venta)
    @Delete
    fun deleteTipoVenta(tipoVenta : tipo_venta)
}
package com.solidtype.atenas_apk_2.core.daos

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.solidtype.atenas_apk_2.core.entidades.venta

@Dao
interface ventaDao {
    @Insert
    suspend fun addVenta(venta: venta)
    @Insert
    suspend fun addVentas(venta : List<venta>)
    @Query("select * from venta")
    suspend fun getVentas():List<venta>
    @Query("select * from venta where id_venta ==:id")
    suspend fun getVentasById(id :Int): venta
    @Update
    suspend fun updateVenta(venta : venta)
    @Delete
    suspend fun deleteVenta(venta : venta)
}
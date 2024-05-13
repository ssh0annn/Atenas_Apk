package com.solidtype.atenas_apk_2.historial_ventas.data.local.dao.actualizacion

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.solidtype.atenas_apk_2.historial_ventas.domain.model.actualizacion.venta
import kotlinx.coroutines.flow.Flow

@Dao
interface ventaDao {
    @Insert
    fun addVenta(venta: venta)
    @Insert
    fun addVentas(venta : List<venta>)
    @Query("select * from venta")
    fun getVentas(): Flow<List<venta>>
    @Query("select * from venta where id_venta ==:id")
    fun getVentasById(id :Int): venta
    @Update
    fun updateVenta(venta : venta)
    @Delete
    fun deleteVenta(venta : venta)
}
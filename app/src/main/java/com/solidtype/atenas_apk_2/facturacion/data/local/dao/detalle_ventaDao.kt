package com.solidtype.atenas_apk_2.facturacion.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.solidtype.atenas_apk_2.facturacion.domain.model.detalle_venta

@Dao
interface detalle_ventaDao {
    @Insert
    suspend fun addDetalleVenta(detalleVenta : detalle_venta)
    @Insert
    suspend fun addDetalleVentas(detalleVenta : List<detalle_venta>)
    @Query("select * from detalle_venta")
    suspend fun getDetalleVentas():List<detalle_venta>
    @Query("select * from detalle_venta where id_detalle_venta ==:id")
    suspend fun getDetalleVentaById(id :Int): detalle_venta
    @Update
    suspend fun updateDetalleVenta(detalleVenta: detalle_venta)
    @Delete
    suspend fun deleteDetalleVenta(detalleVenta: detalle_venta)
}
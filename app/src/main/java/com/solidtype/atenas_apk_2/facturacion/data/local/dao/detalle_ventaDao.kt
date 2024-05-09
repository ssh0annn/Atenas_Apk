package com.solidtype.atenas_apk_2.facturacion.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.solidtype.atenas_apk_2.facturacion.domain.model.detalle_venta
import kotlinx.coroutines.flow.Flow

@Dao
interface detalle_ventaDao {
    @Insert
    fun addDetalleVenta(detalleVenta : detalle_venta)
    @Insert
    fun addDetalleVentas(detalleVenta : List<detalle_venta>)
    @Query("select * from detalle_venta")
    fun getDetalleVentas(): Flow<List<detalle_venta>>
    @Query("select * from detalle_venta where id_detalle_venta ==:id")
    fun getDetalleVentaById(id :Int): detalle_venta
    @Update
    fun updateDetalleVenta(detalleVenta: detalle_venta)
    @Delete
    fun deleteDetalleVenta(detalleVenta: detalle_venta)
}
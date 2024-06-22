package com.solidtype.atenas_apk_2.facturacion.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.solidtype.atenas_apk_2.facturacion.domain.model.detalle_venta
import com.solidtype.atenas_apk_2.historial_ventas.domain.model.actualizacion.venta
import kotlinx.coroutines.flow.Flow

@Dao
interface detalle_ventaDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addDetalleVenta(detalleVenta : detalle_venta)
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addDetalleVentas(detalleVenta : List<detalle_venta>)
    @Query("select * from detalle_venta")
    fun getDetalleVentas(): Flow<List<detalle_venta>>
    @Query("select * from detalle_venta where id_venta ==:id_venta")
    fun getDetalleVentasByIdVenta(id_venta :Int):Flow<List<detalle_venta>>
    @Query("select * from detalle_venta where id_producto ==:id_producto")
    fun getDetalleVentasByIdProducto(id_producto :Int):Flow<List<detalle_venta>>
    @Query("select * from detalle_venta where id_tipo_venta ==:id")
    fun getDetalleVentasByIdTipoVenta(id :Long): Flow<List<detalle_venta>>
    @Query("select * from detalle_venta where id_detalle_venta ==:id")
    suspend fun getDetalleVentaById(id :Int): detalle_venta
    @Query("select * from detalle_venta where id_venta ==:id")
    fun getDetalleVentaByIdVenta(id :Long): detalle_venta
    @Update
    suspend fun updateDetalleVenta(detalleVenta: detalle_venta)
    @Delete
    suspend fun deleteDetalleVenta(detalleVenta: detalle_venta)
}
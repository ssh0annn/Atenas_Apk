package com.solidtype.atenas_apk_2.core.daos

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import com.solidtype.atenas_apk_2.core.entidades.tipo_venta
import com.solidtype.atenas_apk_2.historial_ventas.domain.model.actualizacion.TipoVentaTicket
import com.solidtype.atenas_apk_2.historial_ventas.domain.model.actualizacion.TipoVentaVenta
import kotlinx.coroutines.flow.Flow
import java.time.LocalDate

@Dao
interface tipo_ventaDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addTipoVenta(tipoVenta: tipo_venta)
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addTipoVentas(tipoVenta : List<tipo_venta>)
    @Transaction
    @Query("select * from venta")
    fun getTipoVentas(): Flow<List<TipoVentaVenta>>
    @Query("""
            SELECT * FROM tipo_venta WHERE id_tipo_venta == :id
    """)
    fun buscarTipoVentaPorId(id: Long):tipo_venta

    @Update
    suspend fun updateTipoVenta(tipoVenta : tipo_venta)
    @Delete
    suspend fun deleteTipoVenta(tipoVenta : tipo_venta)
    //Otra baina bien
    @Transaction
    @Query("SELECT * FROM venta WHERE fecha BETWEEN :desde AND :hasta")
    fun buscarTiposVentasFecha(desde:LocalDate, hasta:LocalDate): Flow<List<TipoVentaVenta>>


}
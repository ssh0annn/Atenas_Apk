package com.solidtype.atenas_apk_2.historial_ventas.data.local.dao.actualizacion

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.solidtype.atenas_apk_2.historial_ventas.domain.model.actualizacion.venta
import kotlinx.coroutines.flow.Flow
import java.time.LocalDate

@Dao
interface ventaDao {
    @Insert
    suspend fun addVenta(venta: venta)
    @Insert
    suspend fun addVentas(venta : List<venta>)
    @Query("select * from venta")
    fun getVentas(): Flow<List<venta>>
    @Query("select * from venta where id_venta ==:id")
    suspend fun getVentasById(id :Int): venta
    @Update
    suspend fun updateVenta(venta : venta)
    @Delete
    suspend fun deleteVenta(venta : venta)

    @Query("""SELECT * FROM venta
           WHERE id_venta LIKE '%' || :any || '%'
           OR id_vendedor LIKE '%' || :any || '%'
           OR id_cliente LIKE '%' || :any || '%'
           OR id_tipo_venta LIKE '%' || :any || '%'
           AND (fecha BETWEEN :fechaIni AND :fechaFin)""")
    fun buscarporRangoFecha(any: String, fechaIni:LocalDate, fechaFin:LocalDate):Flow<List<venta>>
}

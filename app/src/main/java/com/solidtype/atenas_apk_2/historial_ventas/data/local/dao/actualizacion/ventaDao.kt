package com.solidtype.atenas_apk_2.historial_ventas.data.local.dao.actualizacion

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.solidtype.atenas_apk_2.historial_ventas.domain.model.HistorialVentaEntidad
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
    @Query("""select * from venta 
             where id_venta like'%' || :any || '%' 
             or id_vendedor like '%' || :any || '%'
             or id_cliente like '%' || :any || '%'
             or id_tipo_venta like '%' || :any || '%'
             and fecha between :fechaInicial and :fechaFinal
            """)
    fun getVentasByIdsAndFecha(any : String, fechaInicial : LocalDate, fechaFinal : LocalDate):Flow<List<venta>>

    @Query("SELECT * FROM venta WHERE fecha BETWEEN :fechaI AND :fechaF")
    fun getHistorialVentaFechaCategoria(fechaI : LocalDate, fechaF: LocalDate): Flow<List<venta>>

    @Update
    suspend fun updateVenta(venta : venta)
    @Delete
    suspend fun deleteVenta(venta : venta)
}

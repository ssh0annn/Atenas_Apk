package com.solidtype.atenas_apk_2.historial_ventas.data.local.dao.actualizacion

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import com.solidtype.atenas_apk_2.facturacion.domain.model.VentasRelacionadas
import com.solidtype.atenas_apk_2.historial_ventas.domain.model.actualizacion.venta
import kotlinx.coroutines.flow.Flow
import java.time.LocalDate

@Dao
interface ventaDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addVenta(venta: venta)
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addVentas(venta : List<venta>)
    @Query("select * from venta")
    fun getVentas(): Flow<List<venta>>
    @Query("select * from venta where id_vendedor ==:id")
     fun getVentasByIdUsuario(id :Long): Flow<List<venta>>
    @Query("select * from venta where id_cliente ==:id")
     fun getVentasByIdPersona(id :Long): Flow<List<venta>>
    @Query("select * from venta where id_venta ==:id")
    suspend fun getVentasById(id :Int): venta
    @Query("select * from venta " +
            "where id_venta like'%' || :any || '%' " +
            "or id_vendedor like '%' || :any || '%'" +
            "or id_cliente like '%' || :any || '%'" +
            "or id_tipo_venta like '%' || :any || '%'" +
            "and fecha between :fechaInicial and :fechaFinal")
    fun getVentasByIdsAndFecha(any : String, fechaInicial : LocalDate, fechaFinal : LocalDate):Flow<List<venta>>
    @Query("SELECT * FROM venta WHERE fecha BETWEEN :fechaI AND :fechaF")
    fun getHistorialVentaFechaCategoria(fechaI : LocalDate, fechaF: LocalDate): Flow<List<venta>>
    @Update
    suspend fun updateVenta(venta : venta)
    @Delete
    suspend fun deleteVenta(venta : venta)
    //Bainita bacana paya ... esto es para pro
    @Query("SELECT * From venta")
    fun getVentaWithRelation():Flow<List<VentasRelacionadas>>
    @Transaction
    @Query("""
            SELECT * FROM venta 
            WHERE id_cliente  LIKE '%' || :id || '%'
            or id_vendedor LIKE '%' || :id || '%'
            or id_venta  LIKE '%' || :id || '%'
            AND fecha BETWEEN :desde AND :hasta
    """
    )
    fun buscarVentaWithRelation(id:String,desde:LocalDate, hasta:LocalDate):Flow<List<VentasRelacionadas>>
    @Transaction
    fun insertarVentaWithRelation(venta: VentasRelacionadas){


    }
}

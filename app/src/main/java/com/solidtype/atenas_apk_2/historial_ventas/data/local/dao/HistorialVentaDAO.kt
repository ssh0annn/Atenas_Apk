package com.solidtype.atenas_apk_2.historial_ventas.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.solidtype.atenas_apk_2.historial_ventas.domain.model.HistorialVentaEntidad
import kotlinx.coroutines.flow.Flow
import java.util.Date

@Dao
interface HistorialVentaDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun setHistorialVenta(entidad : HistorialVentaEntidad)

    @Query("SELECT * FROM HistorialVenta_Table")
    fun getHistorialVenta(): Flow<List<HistorialVentaEntidad>>

    @Query("SELECT * FROM HistorialVenta_Table WHERE ((FechaInicial >= :fechaI) AND (FechaFinal <= :fechaF) AND (Categoria = :cate))")
    fun getHistorialVentaFechaCategoria(fechaF : String, fechaI : String, cate : String): Flow<List<HistorialVentaEntidad>>
}

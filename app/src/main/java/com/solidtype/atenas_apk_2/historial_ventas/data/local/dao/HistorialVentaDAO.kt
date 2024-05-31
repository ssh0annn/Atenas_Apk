package com.solidtype.atenas_apk_2.historial_ventas.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.solidtype.atenas_apk_2.historial_ventas.domain.model.HistorialVentaEntidad
import com.solidtype.atenas_apk_2.products.domain.model.actualizacion.categoria
import kotlinx.coroutines.flow.Flow
import java.time.LocalDate
import java.util.Date

@Dao
interface HistorialVentaDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun setHistorialVenta(entidad : HistorialVentaEntidad)

    @Query("SELECT * FROM HistorialVenta_Table")
    fun getHistorialVenta(): Flow<List<HistorialVentaEntidad>>

    @Query("SELECT * FROM HistorialVenta_Table WHERE categoria == :categoria AND (FechaIni BETWEEN :fechaI AND :fechaF)")
    fun getHistorialVentaFechaCategoria(categoria: Long, fechaI : LocalDate, fechaF: LocalDate): Flow<List<HistorialVentaEntidad>>

    //funciones usadas para las operacions aysnc de firebase
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllHistorialVenta(historal: List<HistorialVentaEntidad>)

    @Query("Select * from HistorialVenta_Table")
    fun getHistorialNormal():List<HistorialVentaEntidad>
}

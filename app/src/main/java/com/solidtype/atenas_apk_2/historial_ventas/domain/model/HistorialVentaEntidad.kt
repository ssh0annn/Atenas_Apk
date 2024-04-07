package com.solidtype.atenas_apk_2.historial_ventas.domain.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity(tableName = "HistorialVenta_Table")
data class HistorialVentaEntidad(
        @PrimaryKey val Codigo : Int,
        val Nombre : String,
        val Descripcion : String,
        val Cantidad : Int,
        val Categoria : String,
        val Modelo : String,
        val Marca : String,
        val Precio : Double,
        val TipoVenta : String,
        val Total : Double,
        val FechaFin : String,
        val FechaIni : String
)


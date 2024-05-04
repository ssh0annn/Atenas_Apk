package com.solidtype.atenas_apk_2.historial_ventas.domain.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.type.DateTime
import java.time.LocalDate
import java.util.Date

@Entity(tableName = "HistorialVenta_Table")
data class HistorialVentaEntidad(
        @PrimaryKey val Codigo : Int,
        val Nombre : String,
        val NumeroFactura : Int,
        val Descripcion : String,
        val Imei: String,
        val Cantidad : Int,
        val Categoria : String,
        val Modelo : String,
        val Marca : String,
        val Precio : Double,
        val TipoVenta : String,
        val Total : Double,
        val FechaIni : LocalDate
)


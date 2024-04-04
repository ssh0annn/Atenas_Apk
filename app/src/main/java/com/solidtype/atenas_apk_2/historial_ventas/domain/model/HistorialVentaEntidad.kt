package com.solidtype.atenas_apk_2.historial_ventas.domain.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity(tableName = "HistorialVenta_Table")
data class HistorialVentaEntidad(
        @PrimaryKey val Codigo : Int,
        @ColumnInfo(name = "Nombre") val Nombre : String,
        @ColumnInfo(name = "NombreCliente") val NombreCliente : String?,
        @ColumnInfo(name = "Imei") val Imei : String?,
        @ColumnInfo(name = "Descricion") val Descripcion : String,
        @ColumnInfo(name = "Cantidad") val Cantidad : Int,
        @ColumnInfo(name = "Categoria") val Categoria : String,
        @ColumnInfo(name = "Modelo") val Modelo : String,
        @ColumnInfo(name = "Marca") val Marca : String,
        @ColumnInfo(name = "Precio") val Precio : Double,
        @ColumnInfo(name = "TipoVenta") val TipoVenta : String,
        @ColumnInfo(name = "FechaFinal") val FechaFin : String,
        @ColumnInfo(name = "FechaInicial") val FechaIni : String
)


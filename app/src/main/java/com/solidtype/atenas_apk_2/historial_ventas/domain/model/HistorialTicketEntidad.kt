package com.solidtype.atenas_apk_2.historial_ventas.domain.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "HistorialTicket_Table")
data class HistorialTicketEntidad(
    @PrimaryKey val Codigo : Int,
    val NombreCliente : String,
    val Modelo : String,
    val Telefono : Int,
    val FaltaEquipo : String,
    val EstadoEquipo : String,
    val Marca : String,
    val Email : String,
    val Fecha : String,
    val Restante : Double,
    val Abono : Double,
    val Nota : String,
    val Precio : Double,
    val Servicio : String,
    val Dias : Int
)

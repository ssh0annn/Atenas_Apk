package com.solidtype.atenas_apk_2.historial_ventas.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDate

@Entity(tableName = "HistorialTicket_Table")
data class HistorialTicketEntidad(
    @PrimaryKey val Codigo : Int,
    val NombreCliente : String,
    val NumeroFactura : Int,
    val Modelo : String,
    val Telefono : Int,
    val FaltaEquipo : String,
    val EstadoEquipo : String,
    val Marca : String,
    val Email : String,
    val Restante : Double,
    val Abono : Double,
    val Nota : String,
    val Precio : Double,
    val Servicio : String,
    val Categoria : String,
    val FechaInicial : LocalDate,
    val FechaFinal : LocalDate
)

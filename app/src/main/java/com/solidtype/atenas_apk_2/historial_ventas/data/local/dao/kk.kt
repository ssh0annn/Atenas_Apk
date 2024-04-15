package com.solidtype.atenas_apk_2.historial_ventas.data.local.dao

import com.solidtype.atenas_apk_2.historial_ventas.domain.model.HistorialTicketEntidad
import javax.inject.Inject

class kk @Inject constructor( private val historial : HistorialTicketDAO){
    fun ingresar(){
        historial.setHistorialTiket(HistorialTicketEntidad(11,"dd","dd",22,"dd","dd","dd","dd",11.0,11.0,"dd",22.0,"22","Ticket","22-22-2222","22-25-2222"))
    }
    suspend fun obtener(){
        var l = historial.getHistorialTicket()
        l.collect{
            product ->
            for(i in product){
                println(i)
            }
        }
    }
    suspend fun obtenerByFC(){
        var l = historial.getHistorialTicketFechaDias("22-22-2222","22-25-2222","Ticket")
        l.collect{
                product ->
            for(i in product){
                println(i)
            }
        }
    }
}
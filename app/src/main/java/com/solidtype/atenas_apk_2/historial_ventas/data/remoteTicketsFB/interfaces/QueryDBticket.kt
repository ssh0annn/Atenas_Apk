package com.solidtype.atenas_apk_2.historial_ventas.data.remoteTicketsFB.interfaces

interface QueryDBticket {
    suspend fun getAllTicekts(): List<List<String>>

    suspend fun insertAllTickets(dataToInsert: MutableList<List<String>>)

    suspend fun compararIntrusos(listIntrusos: MutableList<List<String>>): List<List<String>>

    suspend fun compararLocalParriba(listIntrusos: List<List<String>>): List<List<String>>


}
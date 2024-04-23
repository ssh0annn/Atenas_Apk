package com.solidtype.atenas_apk_2.historial_ventas.data.remoteHistoVentaFB.intefaces

interface QueryDBHistorialVentas {
    suspend fun getallDataHistorial(): List<List<String>>

    suspend fun insertAllHistoral(dataToInsert: MutableList<List<String>>)

    suspend fun compararIntrusos(listIntrusos: MutableList<List<String>>): List<List<String>>

    suspend fun compararLocalParriba(listIntrusos: List<List<String>>): List<List<String>>
}
package com.solidtype.atenas_apk_2.products.data.remoteProFB.dataDb.DataDbProducts

interface DataDbProducts {
    suspend fun getAllProducts(): List<List<String>>

    suspend fun insertAllProducts(dataToInsert: MutableList<List<String>>)

    suspend fun compararIntrusos(listIntrusos: MutableList<List<String>>): List<List<String>>

    suspend fun compararLocalParriba(listIntrusos: List<List<String>>): List<List<String>>
}
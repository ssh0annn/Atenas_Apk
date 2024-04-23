package com.solidtype.atenas_apk_2.products.data.remote.remoteProFB.interfaces

interface QueryDBlocal {
    suspend fun getAllProducts(): List<List<String>>

    suspend fun insertAllProducts(dataToInsert: MutableList<List<String>>)

    suspend fun compararIntrusos(listIntrusos: MutableList<List<String>>): List<List<String>>

    suspend fun compararLocalParriba(listIntrusos: List<List<String>>): List<List<String>>
}
package com.solidtype.atenas_apk_2.products.data.remoteProFB

import com.google.firebase.firestore.QuerySnapshot
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.withContext

import javax.inject.Inject

class MediatorProducts @Inject constructor(
    private val queryDblocal: QueryDBlocal,
    private val queryFireStore: QuerysFirstore
) {

    suspend fun ayscPro() {

        val datosFirebaseFirestore = caputarDatosFirebase()
        val datoDS = stringToListOfList(datosFirebaseFirestore)
        if (verficarDataDBLocal()) {
            println("La base de dato esta vacia")

            insertaInDbLocal(datoDS)
        } else {
            println("Contiene datos")


            val sacarIntruso = sacarIntrusos(datoDS)

            if (sacarIntruso.isNotEmpty()) {
                println("Saca estos instrusos: $sacarIntruso")
                coroutineScope {
                    withContext(Dispatchers.Default) {
                        val result =
                            async { queryFireStore.deleteDataFirebase("productos", sacarIntruso) }
                        println("Este es el resultado de tu result: ${result.await()}")
                    }
                }

            }


            //agregar datos a firebase

           val listosParaSubir =  queryDblocal.compararLocalParriba(datoDS)
            println("Listos para subir $listosParaSubir y el size: ${listosParaSubir.size}")
            queryFireStore.insertToFirebase("productos", listosParaSubir)

        }


    }


    private fun stringToListOfList(datos: QuerySnapshot): MutableList<List<String>> {
        return querySnapshotToList(datos)
    }

    private fun querySnapshotToList(query: QuerySnapshot): MutableList<List<String>> {
        val listaDeLista = mutableListOf<List<String>>()
        query.forEach { documentData ->
            val it = documentData.data
            val documentos = mutableListOf<String>()
            documentos.add(it["code_Product"].toString())
            documentos.add(it["name_Product"].toString())
            documentos.add(it["description_Product"].toString())
            documentos.add(it["category_Product"].toString())
            documentos.add(it["price_Product"].toString())
            documentos.add(it["model_Product"].toString())
            documentos.add(it["price_Vending_Product"].toString())
            documentos.add(it["tracemark_Product"].toString())
            documentos.add(it["count_Product"].toString())
            listaDeLista.add(documentos)
        }
        println("Esta es la lista $listaDeLista")
        return listaDeLista

    }

    private suspend fun verficarDataDBLocal(): Boolean {
        var dataLocalState: List<List<String>> = emptyList()
        return coroutineScope {

            dataLocalState = queryDblocal.getAllProducts()
            println("Este es la base local  : $dataLocalState")

            return@coroutineScope dataLocalState.isEmpty()
        }

    }


    private suspend fun insertaInDbLocal(data: MutableList<List<String>>) {
        queryDblocal.insertAllProducts(data)
    }

    private suspend fun sacarIntrusos(posiblesIntrusos: MutableList<List<String>>): List<List<String>> {

        val intrusos = queryDblocal.compararIntrusos(posiblesIntrusos)

        if (intrusos.isNotEmpty()) {
            queryFireStore.deleteDataFirebase("productos", intrusos)
            println("estos son los intrusos --> $intrusos <--")
        }


        return emptyList()
    }

    private suspend fun capturarDatosDB() = queryDblocal.getAllProducts()

    private suspend fun caputarDatosFirebase() = queryFireStore.getAllDataFirebase("productos")


    private fun <E> List<E>.add(element: MutableList<String?>) {

    }
}
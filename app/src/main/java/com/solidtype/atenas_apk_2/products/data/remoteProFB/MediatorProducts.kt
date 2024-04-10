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
    private val codigoProductos = "code_Product"
    suspend fun ayscPro() {
        val querySnapshotDesdeFireStore = caputarDatosFirebaseEnSnapshot()
        val listaDeFireStore = querySnapshotToList(querySnapshotDesdeFireStore!!)
        val baseLocal = capturarDatosDB()
        //Verificamos que la base de datos local este vacia, y que la de firebase no este vacia
        if (baseLocal.isEmpty() && listaDeFireStore.isNotEmpty()) {
            coroutineScope {
                val response = async {
                    try {
                        insertaInDbLocal(listaDeFireStore)
                    } catch (e: Exception) {
                        println("Mediador asyscPro:No se inserto en db local: $e <--")
                    }
                }
                response.await()
            }
        }
        //Evaluamos que firestor este vacia y si datos locales contiene datos , pues insertamos.
        else if (listaDeFireStore.isEmpty() && baseLocal.isNotEmpty()) {
            try {
                queryFireStore.insertToFirebase(
                    "productos",
                    convierteAObjetoMap(baseLocal),
                    codigoProductos
                )
            } catch (e: Exception) {
                println("Problemas de insercion a Firestor: $e")
            }

        } else {
            //si no se cumplen los demas esenarios es porque ambas tienen datos entonces.
            // analizamos datos para subir
            println("Contiene datos: Local : $baseLocal")
            println("Contiene datos: Firestore : $listaDeFireStore")
            val listosParaSubir = queryDblocal.compararLocalParriba(listaDeFireStore)
            //Evaluamos los cambios producidos en base local para reflejarlos enla firestore.
            if (listosParaSubir.isNotEmpty()) {
                println("Listos para subir $listosParaSubir")
                queryFireStore.insertToFirebase(
                    "productos",
                    convierteAObjetoMap(listosParaSubir),
                    codigoProductos
                )
            }
            val sacarIntruso = sacarIntrusos(listaDeFireStore)
            if (sacarIntruso.isNotEmpty()) {
                println("Saca estos instrusos: $sacarIntruso")
                coroutineScope {
                    withContext(Dispatchers.Default) {
                        val result =
                            async {
                                queryFireStore.deleteDataFirebase(
                                    "productos",
                                    convierteAObjetoMap(sacarIntruso),
                                    codigoProductos
                                )
                            }
                        println("Este es el resultado de tu result: ${result.await()}")
                    }
                }
            }
        }

    }

    private fun convierteAObjetoMap(lista: List<List<String>>): MutableList<Map<String, String>> {
        val listaMapa: MutableList<Map<String, String>> = mutableListOf()
        for (data in lista) {
            if (data.isNotEmpty() && data.size == 9) {
                val dataMapa = mapOf(
                    "code_Product" to data[0],
                    "name_Product" to data[1],
                    "description_Product" to data[2],
                    "category_Product" to data[3],
                    "price_Product" to data[4],
                    "model_Product" to data[5],
                    "price_Vending_Product" to data[6],
                    "tracemark_Product" to data[7],
                    "count_Product" to data[8],
                )
                listaMapa.add(dataMapa)
            }
        }
        return listaMapa
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
        var dataLocalState: List<List<String>>
        return coroutineScope {

            dataLocalState = queryDblocal.getAllProducts()
            println("Este es la base local  : $dataLocalState")

            return@coroutineScope dataLocalState.isEmpty()
        }

    }


    private suspend fun insertaInDbLocal(data: MutableList<List<String>>) {
        coroutineScope {
            async { queryDblocal.insertAllProducts(data) }
        }.await()
    }

    private suspend fun sacarIntrusos(posiblesIntrusos: MutableList<List<String>>): List<List<String>> {

        val intrusos = queryDblocal.compararIntrusos(posiblesIntrusos)
        if (intrusos.isNotEmpty()) {
            queryFireStore.deleteDataFirebase("productos",convierteAObjetoMap(intrusos) , codigoProductos)
            println("estos son los intrusos --> $intrusos <--")
        }
        return emptyList()
    }

    private suspend fun capturarDatosDB() = queryDblocal.getAllProducts()

    private suspend fun caputarDatosFirebaseEnSnapshot() =
        queryFireStore.getAllDataFirebase("productos")


    private fun <E> List<E>.add(element: MutableList<String?>) {

    }
}
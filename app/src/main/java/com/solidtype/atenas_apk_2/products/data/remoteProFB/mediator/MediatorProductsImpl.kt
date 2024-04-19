package com.solidtype.atenas_apk_2.products.data.remoteProFB.mediator

import com.google.firebase.firestore.QuerySnapshot
import com.solidtype.atenas_apk_2.core.remote.dataCloud.DataCloud
import com.solidtype.atenas_apk_2.products.data.remoteProFB.interfaces.QueryDBlocal
import com.solidtype.atenas_apk_2.products.data.remoteProFB.interfaces.MediatorProducts
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.withContext

import javax.inject.Inject


/**
 * @constructor:  private val queryDblocal: QueryDBlocal, private val queryFireStore: QuerysFirstore
 * @funcion: Esta es la clase mediadora para la sincronizacion entre firestore y roomdabase. Esta clase esta dirigida para
 * el feature Productos en espesifico. Solo necesitas llamar el metodo async. y listo.
 *
 */
class MediatorProductsImpl @Inject constructor(
    private val queryDblocal: QueryDBlocal,
    private val queryDataService: DataCloud

): MediatorProducts {
    private val codigoProductos = "code_Product"
    private val ColletionName = "productos"

    /**
     * @param: List<List<String>>, MutableList<List<String>>
     * @return: Boolean
     * @funcion: Maneja la logica de insercion a base de datos local. Verifica si esta basia la ddbb local y si contiene datos
     * la base de datos de Firestore. Y luego puede agregar todos los datos desde el remote a db local.
     *
     */
    private suspend fun logicaDeInsercionLocal(
        baseLocal: List<List<String>>, listaDeFireStore: MutableList<List<String>>
    ): Boolean {
        var confirmar = false
        if (baseLocal.isEmpty() && listaDeFireStore.isNotEmpty()) {
            coroutineScope {
                val response = async {
                    try {
                        insertaInDbLocal(listaDeFireStore)
                        confirmar = true
                    } catch (e: Exception) {
                        println("Mediador asyscPro:No se inserto en db local: $e <--")
                    }
                }
                response.await()
            }
        }
        return confirmar
    }

    /**
     * @param: listaDeFireStore, baseLocal
     * @return: Boolean
     * @funcion: Si la base de datos de firebae se encuentra basia y la db local contiene datos, pues los inserta en Firestore.
     * haciendo un mirror.
     */
    private suspend fun logicaInsertarAFireStore(
        listaDeFireStore: MutableList<List<String>>, baseLocal: List<List<String>>
    ): Boolean {
        var confirmar = false
        if (listaDeFireStore.isEmpty() && baseLocal.isNotEmpty()) {
            try {
                queryDataService.insertAllToCloud(
                    ColletionName, convierteAObjetoMap(baseLocal), codigoProductos
                )
                confirmar = true
            } catch (e: Exception) {
                println("Problemas de insercion a Firestor: $e")
            }
        }
        return confirmar
    }

    /**
     * @param: baseLocal: List<List<String>>,
     *         listaDeFireStore: MutableList<List<String>>
     *@return: Bollean
     * @funcion: Se evaluan los datos que si existen en db local y no en remote (FireStore), entonces se procede a subir los datos no existentes en el remote.
     *
     */

    suspend fun listosParaSubirAFirestore(
        baseLocal: List<List<String>>, listaDeFireStore: MutableList<List<String>>
    ): Boolean {
        var confirmar = false

        println("Contiene datos: Local : $baseLocal")
        println("Contiene datos: Firestore : $listaDeFireStore")
        val listosParaSubir = queryDblocal.compararLocalParriba(listaDeFireStore)
        //Evaluamos los cambios producidos en base local para reflejarlos enla firestore.
        if (listosParaSubir.isNotEmpty()) {
            confirmar = true

            println("Listos para subir $listosParaSubir")
            try {
                queryDataService.insertAllToCloud(
                    "productos", convierteAObjetoMap(listosParaSubir), codigoProductos
                )
            } catch (e: Exception) {
                return false
            }
        }
        return confirmar
    }

    /**
     * @param: listaDeFireStore: MutableList<List<String>>
     * @return: Boolean
     * @funcion: analiza los datos existentes en fireStore y no en db local, entendiendo que la base local es la referencia de confianza.
     * pues los datos (Intrusos) no existentes en la db local, los elimina de firebase, haciendo el mirror.
     */

    private suspend fun logicadeIntrusos(listaDeFireStore: MutableList<List<String>>): Boolean {
        var confirmar = false
        val sacarIntruso = sacarIntrusos(listaDeFireStore)
        if (sacarIntruso.isNotEmpty()) {
            confirmar = true
            println("Saca estos instrusos: $sacarIntruso")
            coroutineScope {
                withContext(Dispatchers.Default) {
                    val result = async {
                        queryDataService.deleteDataInCloud(
                            ColletionName, convierteAObjetoMap(sacarIntruso), codigoProductos
                        )
                    }
                    println("Este es el resultado de tu result: ${result.await()}")
                }
            }
        }
        return confirmar
    }

    /**
     * @return: Unit
     * @funcion: Es donde sucede toda la logica del proceso de sincronizacion. En esta funcion se manejan muchos algoritmos
     * con corrutinas, favor manajar en el hilo Default para evitar bloqueos del hilo main.
     */
    override suspend operator fun invoke() {
        val querySnapshotDesdeFireStore = caputarDatosFirebaseEnSnapshot()
        val listaDeFireStore = querySnapshotToList(querySnapshotDesdeFireStore!!)
        println("Esta es la lista de productos actual de firebase --> $listaDeFireStore <--")

        val baseLocal = capturarDatosDB()

        //Verificamos que la base de datos local este vacia, y que la de firebase no este vacia
        if (logicaDeInsercionLocal(baseLocal, listaDeFireStore)) {
            println("Se estan insertando datos en base local...")

        }
        //Evaluamos que firestor este vacia y si datos locales contiene datos , pues insertamos.
        else if (logicaInsertarAFireStore(listaDeFireStore, baseLocal)) {
            println("Se estan insertando en Firebase...")

        } else {
            //si no se cumplen los demas esenarios es porque ambas tienen datos entonces.
            // analizamos datos
            if (listosParaSubirAFirestore(baseLocal, listaDeFireStore)) {
                println("Sincronizando desde local a FireStore")

            } else {
                //si no hay datos para subir, eliminamos intrusos.
                logicadeIntrusos(listaDeFireStore)
                println("Analizando comportamiento de intrusos")
            }  //si no se cumplen los demas esenarios es porque ambas tienen datos entonces.


        }
    }

    /**
     * @param: ista: List<List<String>>
     *@return: MutableList<Map<String, String>>
     * @funcion:convierte una lista de listas en un objeto conforme al las tablas de productos, vease: ProductEntity
     */
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


    /**
     * @param: query: QuerySnapshot
     * @return: MutableList<List<String>>
     * @funcion: De uso interno, para convertir los query de QuerySnapshot en listas mutables.
     */
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


    /**
     * @param: data: MutableList<List<String>>
     * @return: Unit.
     * @funcion: inserta los datos en db local.
     */
    private suspend fun insertaInDbLocal(data: MutableList<List<String>>) {
        coroutineScope {
            async { queryDblocal.insertAllProducts(data) }
        }.await()
    }

    /**
     * @param: posiblesIntrusos: MutableList<List<String>>
     * @return: List<List<String>>
     * @funcion: ejecuta la logica de eliminar los datos que no son ya relevantes para la db local. O
     * Osea, aquellos que fueron eliminados fuera de linea. De esta manera los puede eliminar en FireStore.
     */
    private suspend fun sacarIntrusos(posiblesIntrusos: MutableList<List<String>>): List<List<String>> {

        val intrusos = queryDblocal.compararIntrusos(posiblesIntrusos)
        if (intrusos.isNotEmpty()) {
            queryDataService.deleteDataInCloud(
                ColletionName, convierteAObjetoMap(intrusos), codigoProductos
            )
            println("estos son los intrusos --> $intrusos <--")
        }
        return emptyList()
    }


    /**
     * @return:  List<List<String>>
     * @funcion: captura los datos de la base local y los debuelve en formato de lista de listas.
     */
    private suspend fun capturarDatosDB() = queryDblocal.getAllProducts()

    /**
     * @return  QuerySnapshot?
     * @funcion: captura los datos del documento de productos desde firestore y los debuelve en un formato QuerySnapshot
     */

    private suspend fun caputarDatosFirebaseEnSnapshot(): QuerySnapshot? {
        return try {
            coroutineScope {
                async { queryDataService.getallData(ColletionName) }
            }.await()

        } catch (e: Exception) {
            null
        }
    }

}

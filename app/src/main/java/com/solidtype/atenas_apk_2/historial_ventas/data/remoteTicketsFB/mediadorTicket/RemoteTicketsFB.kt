package com.solidtype.atenas_apk_2.historial_ventas.data.remoteTicketsFB.mediadorTicket

import android.util.Log
import com.google.firebase.firestore.QuerySnapshot
import com.solidtype.atenas_apk_2.historial_ventas.data.remoteTicketsFB.QueryDBTicket.QueryDBticket
import com.solidtype.atenas_apk_2.products.data.remoteProFB.QuerysFirstore
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.withContext
import javax.inject.Inject

class RemoteTicketsFB @Inject constructor(
    private val queryDBticket: QueryDBticket,
    private val querYFIreStore:QuerysFirstore
) {

    private val codigoTickets = "Codigo"
    private val collectionName = "Tickets"



    suspend fun asycTickets() {
        Log.e("Entre","Entre a la funcion Tickets async")
        val querySnapshotDesdeFireStore = caputarDatosFirebaseEnSnapshot()
        val listaDeFireStore = querySnapshotToList(querySnapshotDesdeFireStore!!)

        val baseLocal = capturarDatosDB()
        Log.d("TEstSnapToListTickets","Esta es la lista de SnapShot a List lo convertimos T$listaDeFireStore")
        Log.d("TEstSnapToListTickets","Estos son los datos de la base local Tickets $baseLocal")
        println("Esta es la lista de productos actual de firebase --> $listaDeFireStore <--")


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
                querYFIreStore.insertToFirebase(
                    collectionName, convierteAObjetoMap(baseLocal), codigoTickets
                )
                confirmar = true
            } catch (e: Exception) {
                println("Problemas de insercion a Firestor: $e")
            }
        }
        return confirmar
    }


    suspend fun listosParaSubirAFirestore(
        baseLocal: List<List<String>>, listaDeFireStore: MutableList<List<String>>
    ): Boolean {
        var confirmar = false

        println("Contiene datos: Local : $baseLocal")
        println("Contiene datos: Firestore : $listaDeFireStore")
        val listosParaSubir = queryDBticket.compararLocalParriba(listaDeFireStore)
        //Evaluamos los cambios producidos en base local para reflejarlos enla firestore.
        if (listosParaSubir.isNotEmpty()) {
            confirmar = true

            println("Listos para subir $listosParaSubir")
            try{
                querYFIreStore.insertToFirebase(
                    collectionName, convierteAObjetoMap(listosParaSubir), codigoTickets
                )
            }catch (e: Exception){
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
                        querYFIreStore.deleteDataFirebase(
                            "productos", convierteAObjetoMap(sacarIntruso), codigoTickets
                        )
                    }
                    println("Este es el resultado de tu result: ${result.await()}")
                }
            }
        }
        return confirmar
    }


    /**
     * @param: ista: List<List<String>>
     *@return: MutableList<Map<String, String>>
     * @funcion:convierte una lista de listas en un objeto conforme al las tablas de productos, vease: ProductEntity
     */
    private fun convierteAObjetoMap(lista: List<List<String>>): MutableList<Map<String, String>> {
        val listaMapa: MutableList<Map<String, String>> = mutableListOf()
        for (data in lista) {
            if (data.isNotEmpty() && data.size == 16) {
                val dataMapa = mapOf(
                    "Codigo" to data[0],
                    "NombreCliente" to data[1],
                    "Modelo" to data[2],
                    "Telefono" to data[3],
                    "FaltaEquipo" to data[4],
                    "EstadoEquipo" to data[5],
                    "Marca" to data[6],
                    "Email" to data[7],
                    "Restante" to data[8],
                    "Abono" to data[9],
                    "Nota" to data[10],
                    "Precio" to data[11],
                    "Servicio" to data[12],
                    "Categoria" to data[13],
                    "FechaIni" to data[14],
                    "FechaFinal" to data[15],
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
            documentos.add(it["Codigo"].toString())
            documentos.add(it["NombreCliente"].toString())
            documentos.add(it["Modelo"].toString())
            documentos.add(it["Telefono"].toString())
            documentos.add(it["FaltaEquipo"].toString())
            documentos.add(it["EstadoEquipo"].toString())
            documentos.add(it["Marca"].toString())
            documentos.add(it["Email"].toString())
            documentos.add(it["Restante"].toString())
            documentos.add(it["Abono"].toString())
            documentos.add(it["Nota"].toString())
            documentos.add(it["Precio"].toString())
            documentos.add(it["Servicio"].toString())
            documentos.add(it["Categoria"].toString())
            documentos.add(it["FechaIni"].toString()) // Aqui el fallo (Habia una discrepancia)
            documentos.add(it["FechaFinal"].toString())
            listaDeLista.add(documentos)
        }
        println("Esta es la lista de SnapShot a List lo convertimos$listaDeLista")
        Log.d("TEstSnapToList","Esta es la lista de SnapShot a List lo convertimos$listaDeLista")
        return listaDeLista

    }

    /**
     * @param: data: MutableList<List<String>>
     * @return: Unit.
     * @funcion: inserta los datos en db local.
     */
    private suspend fun insertaInDbLocal(data: MutableList<List<String>>) {
        coroutineScope {
            async { queryDBticket.insertAllProducts(data) }
        }.await()
    }

    /**
     * @param: posiblesIntrusos: MutableList<List<String>>
     * @return: List<List<String>>
     * @funcion: ejecuta la logica de eliminar los datos que no son ya relevantes para la db local. O
     * Osea, aquellos que fueron eliminados fuera de linea. De esta manera los puede eliminar en FireStore.
     */
    private suspend fun sacarIntrusos(posiblesIntrusos: MutableList<List<String>>): List<List<String>> {

        val intrusos = queryDBticket.compararIntrusos(posiblesIntrusos)
        if (intrusos.isNotEmpty()) {
            querYFIreStore.deleteDataFirebase(
                collectionName, convierteAObjetoMap(intrusos), codigoTickets
            )
            println("estos son los intrusos --> $intrusos <--")
        }
        return emptyList()
    }

    /**
     * @return:  List<List<String>>
     * @funcion: captura los datos de la base local y los debuelve en formato de lista de listas.
     */
    private suspend fun capturarDatosDB() = queryDBticket.getAllProducts()
    /**
     * @return  QuerySnapshot?
     * @funcion: captura los datos del documento de productos desde firestore y los debuelve en un formato QuerySnapshot
     */

    private suspend fun caputarDatosFirebaseEnSnapshot() = querYFIreStore.getAllDataFirebase(collectionName)


}

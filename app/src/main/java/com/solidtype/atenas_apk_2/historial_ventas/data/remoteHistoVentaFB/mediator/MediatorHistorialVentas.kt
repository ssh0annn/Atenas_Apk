package com.solidtype.atenas_apk_2.historial_ventas.data.remoteHistoVentaFB.mediator

import android.util.Log
import com.google.firebase.firestore.QuerySnapshot
import com.solidtype.atenas_apk_2.historial_ventas.data.remoteHistoVentaFB.QueryDBHistorialVenta
import com.solidtype.atenas_apk_2.products.data.remoteProFB.QuerysFirstore
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.withContext

class MediatorHistorialVentas @Inject constructor(
    private val queriesFireStore: QuerysFirstore,
    private val queryDBlocal: QueryDBHistorialVenta
) {
    private val codigoHistorial = "codigo"

    /**
     * @return: Unit
     * @funcion: Es donde sucede toda la logica del proceso de sincronizacion. En esta funcion se manejan muchos algoritmos
     * con corrutinas, favor manajar en el hilo Default para evitar bloqueos del hilo main.
     */

    suspend fun aysncHistorial(){
        Log.e("Entre","Entre a la funcion")
        val querySnapshotDesdeFireStore = caputarDatosFirebaseEnSnapshot()
        val listaDeFireStore = querySnapshotToList(querySnapshotDesdeFireStore!!)
        val baseLocal = capturarDatosDB()


        //Verificamos que la base de datos local este vacia, y que la de firebase no este vacia
        if (logicaDeInsercionLocal(baseLocal, listaDeFireStore)) {
            println("Se estan insertando datos en base local historial ventas...")

        }
        //Evaluamos que firestor este vacia y si datos locales contiene datos , pues insertamos.
        else if (logicaInsertarAFireStore(listaDeFireStore, baseLocal)) {
            println("Se estan insertando en Firebase historial ventas...")

        } else {
            // analizamos datos para
            if (listosParaSubirAFirestore(baseLocal, listaDeFireStore)) {
                println("Sincronizando desde local a FireStore historial ventas")

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

    private suspend fun logicaDeInsercionLocal(baseLocal: List<List<String>>, listaDeFireStore: MutableList<List<String>>): Boolean {
        var confirmar = false
        if (baseLocal.isEmpty() && listaDeFireStore.isNotEmpty()) {
            coroutineScope {
                val response = async {
                    try {
                        insertaInDbLocal(listaDeFireStore)
                    } catch (e: Exception) {
                        println("Mediador asyscHistorial:No se inserto en db local: $e <--")
                    }
                }
                response.await()
            }
            confirmar = true
        }
        return confirmar
    }

    /**
     * @param: listaDeFireStore, baseLocal
     * @return: Boolean
     * @funcion: Si la base de datos de firebae se encuentra basia y la db local contiene datos, pues los inserta en Firestore.
     * haciendo un mirror.
     */
    private suspend fun logicaInsertarAFireStore(listaDeFireStore: MutableList<List<String>>, baseLocal: List<List<String>>): Boolean {
        var confirmar = false
        println("datos historia en la funcion logicaInsertarAFireStore  antes de insertarse en firebase $baseLocal")
        if (listaDeFireStore.isEmpty() && baseLocal.isNotEmpty()) {
            try {
                queriesFireStore.insertToFirebase(
                    "historial_ventas", convierteAObjetoMap(baseLocal), codigoHistorial
                )
            } catch (e: Exception) {
                println("Problemas de insercion a Firestore historial ventas historial ventas: $e")
            }
            confirmar = true
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

    suspend fun listosParaSubirAFirestore(baseLocal: List<List<String>>, listaDeFireStore: MutableList<List<String>>): Boolean {
        var confirmar = false

        println("Contiene datos: Local historial ventas : $baseLocal")
        println("Contiene datos: Firestore historial ventas : $listaDeFireStore")
        val listosParaSubir = queryDBlocal.compararLocalParriba(listaDeFireStore)
        //Evaluamos los cambios producidos en base local para reflejarlos enla firestore.
        if (listosParaSubir.isNotEmpty()) {
            confirmar = true
            println("Listos para subir $listosParaSubir")
            queriesFireStore.insertToFirebase(
                "historial_ventas", convierteAObjetoMap(listosParaSubir), codigoHistorial
            )
        }
        return confirmar

    }


    /**
     * @param: ista: List<List<String>>
     *@return: MutableList<Map<String, String>>
     * @funcion:convierte una lista de listas en un objeto conforme al las tablas de Historial_ventas, vease: Historial_Ventas
     */
    private fun convierteAObjetoMap(lista: List<List<String>>): MutableList<Map<String, String>> {
        val listaMapa: MutableList<Map<String, String>> = mutableListOf()
        for (data in lista) {
            if (data.isNotEmpty() && data.size == 12) {
                val dataMapa = mapOf(
                    "Codigo" to data[0],
                    "Nombre" to data[1],
                    "Descripcion" to data[2],
                    "Cantidad" to data[3],
                    "Categoria" to data[4],
                    "Modelo" to data[5],
                    "Marca" to data[6],
                    "Precio" to data[7],
                    "TipoVenta" to data[8],
                    "Total" to data[9],
                    "FechaFin" to data[10],
                    "FechaIni" to data[11],
                )
                listaMapa.add(dataMapa)
            }
        }
        println("datos de historial despues de convertirlos a map $lista")
        return listaMapa
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
            println("Saca estos instrusos historial ventas: $sacarIntruso")
            coroutineScope {
                withContext(Dispatchers.Default) {
                    val result = async {
                        queriesFireStore.deleteDataFirebase(
                            "historial_ventas", convierteAObjetoMap(sacarIntruso), codigoHistorial
                        )
                    }
                    println("Este es el resultado de tu result: ${result.await()}")
                }
            }
        }
        return confirmar
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
            documentos.add(it["Nombre"].toString())
            documentos.add(it["Descripcion"].toString())
            documentos.add(it["Cantidad"].toString())
            documentos.add(it["Categoria"].toString())
            documentos.add(it["Modelo"].toString())
            documentos.add(it["Marca"].toString())
            documentos.add(it["Precio"].toString())
            documentos.add(it["TipoVenta"].toString())
            documentos.add(it["Total"].toString())
            documentos.add(it["FechaFin"].toString())
            documentos.add(it["FechaIni"].toString())
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
            async { queryDBlocal.insertAllHistorialVentas(data) }
        }.await()
    }


    /**
     * @param: posiblesIntrusos: MutableList<List<String>>
     * @return: List<List<String>>
     * @funcion: ejecuta la logica de eliminar los datos que no son ya relevantes para la db local. O
     * Osea, aquellos que fueron eliminados fuera de linea. De esta manera los puede eliminar en FireStore.
     */
    private suspend fun sacarIntrusos(posiblesIntrusos: MutableList<List<String>>): List<List<String>> {

        val intrusos = queryDBlocal.compararIntrusos(posiblesIntrusos)
        if (intrusos.isNotEmpty()) {
            queriesFireStore.deleteDataFirebase(
                "historial_ventas", convierteAObjetoMap(intrusos), codigoHistorial
            )
            println("estos son los intrusos --> $intrusos <--")
        }
        return emptyList()
    }


    /**
     * @return:  List<List<String>>
     * @funcion: captura los datos de la base local y los debuelve en formato de lista de listas.
     */
    private suspend fun capturarDatosDB() = queryDBlocal.getAllHistorial()

    /**
     * @return  QuerySnapshot?
     * @funcion: captura los datos del documento de Historial desde firestore y los debuelve en un formato QuerySnapshot
     */

    private suspend fun caputarDatosFirebaseEnSnapshot() = queriesFireStore.getAllDataFirebase("historial_ventas")



}
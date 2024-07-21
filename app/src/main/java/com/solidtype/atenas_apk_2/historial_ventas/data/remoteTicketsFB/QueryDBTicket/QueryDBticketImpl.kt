package com.solidtype.atenas_apk_2.historial_ventas.data.remoteTicketsFB.QueryDBTicket

import com.solidtype.atenas_apk_2.gestion_tickets.data.ticketDao
import com.solidtype.atenas_apk_2.historial_ventas.data.remoteTicketsFB.interfaces.QueryDBticket
import com.solidtype.atenas_apk_2.util.toLocalDate
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import javax.inject.Inject

class QueryDBticketImpl @Inject constructor(
    private val dao:ticketDao
): QueryDBticket {
        /**
         * @param: List<String>
         * @return: Data Object
         * @throws: Exception si no es compatible los datos con el objeto espesificado.
         * @funcionamiento: Es para uso interno, para convertir una lista de strings en datos adecuados para el objeto HistorialTicketEntidad
         * Favor verificar el formato de este objeto antes de someter la lista.
         * Los elementos deben ser igual a 16.
         */
//        private fun entityConvert(it: List<String>): HistorialTicketEntidad {
//            if (it.size == 16) {
//                try {
//                    return HistorialTicketEntidad(
//                        Codigo = it[0].toInt(),
//                        NombreCliente = it[1],
//                        NumeroFactura = it[2].toInt(),
//                        Modelo = it[3],
//                        Telefono = it[4].toInt(),
//                        FaltaEquipo = it[5],
//                        EstadoEquipo = it[6],
//                        Marca = it[7],
//                        Email = it[8],
//                        Restante = it[9].toDouble(),
//                        Abono = it[10].toDouble(),
//                        Nota =it[11],
//                        Precio = it[12].toDouble(),
//                        Servicio = it[13],
//                        Categoria = it[14],
//                        FechaFinal = it[15].toLocalDate(),
//                        FechaInicial = it[16].toLocalDate()
//
//                    )
//                } catch (e: Exception) {
//                    println("Este es la razon lista: $it, size ${it.size}")
//                    throw Exception("El tipo de la lista no es compatible con la Entity HistorialTicketEntidad $e")
//
//                }
//            }
//            throw Exception("El tamaño de la lista entregada no es compatible")
//        }

        /**
         * @param  List<HistorialVentaEntidad>
         * @return List<List<String>>
         * @funcionamiento Funcion de uso interno para convertir una entidad de tipo HistorialTicketEntidad en una lista de lista de Strings.
         * Favor ver la data class HistorialTicketEntidad.
         */
//        private fun entityToListString(data: List<HistorialTicketEntidad>): List<List<String>> {
//            val mutableListData: MutableList<List<String>> = mutableListOf()
//            if (data.isNotEmpty()) {
//                data.forEach {
//                    val mutableList = mutableListOf<String>()
//                    mutableList.add(it.Codigo.toString())
//                    mutableList.add(it.NombreCliente)
//                    mutableList.add(it.NumeroFactura.toString())
//                    mutableList.add(it.Modelo)
//                    mutableList.add(it.Telefono.toString())
//                    mutableList.add(it.FaltaEquipo)
//                    mutableList.add(it.EstadoEquipo.toString())
//                    mutableList.add(it.Marca)
//                    mutableList.add(it.Email)
//                    mutableList.add(it.Restante.toString())
//                    mutableList.add(it.Abono.toString())
//                    mutableList.add(it.Nota)
//                    mutableList.add(it.Precio.toString())
//                    mutableList.add(it.Servicio)
//                    mutableList.add(it.Categoria)
//                    mutableList.add(it.FechaInicial.toString())
//                    mutableList.add(it.FechaFinal.toString())
//                    mutableListData.add(mutableList)
//                }
//            }
//            return mutableListData
//
//        }

    /**
     * @return: List<List<String>>
     * @funcionamiento: captura todo los productos de la base de dato local espesificcamente de la tabla Productos.
     * Favor ver el objeto ProductEntity para mas informacion.
     *
     */
    override suspend fun getAllTicekts(): List<List<String>> {
//        var mutableListData: List<List<String>> = emptyList()
//        var listaDeEntity = emptyList<HistorialTicketEntidad>()
//        coroutineScope {
//            val response = async { listaDeEntity = dao.getAllTicketsNormal() }
//            response.await()
//            if (listaDeEntity.isNotEmpty()) {
//                mutableListData = entityToListString(listaDeEntity)
//            }
//        }
        return emptyList()
    }

    /**
     * @param: MutableList<List<String>>
     * @return: Unit
     * @throws: El tipo de la lista no es compatible con la Entity producto
     * @funcionamiento: Inserta productos en base de datos local si la lista es compatible con el formato para ProductEntity,
     * Esta funcion integra un hilo interno. Favor llamar desde una funcion suspendida.
     */
    override suspend fun insertAllTickets(dataToInsert: MutableList<List<String>>) {
//        val lista: MutableList<HistorialTicketEntidad> = mutableListOf()
//        dataToInsert.forEach {
//            try {
//                lista.add(entityConvert(it))
//            } catch (e: Exception) {
//                throw Exception("El tipo de la lista no es compatible con la Entity producto $e")
//            }
//        }
//        coroutineScope {
//            println("Aqui veamos la lista: $lista y siez ${lista.size}")
//            val response = async { dao.insertAllTickets(lista) }
//            response.await()
//        }
    }

    /**
     * @param: MutableList<List<String>>
     * @return: List<List<String>>
     * @funcion: recibe una lista de listas mutables, para luego comparar con las base de datos locales de los posibles datos diferentes
     * tomando la base de datos local como referencia de "Single true of trust". Luego debuelve en una lista los datos no iguales.
     */
    override suspend fun compararIntrusos(listIntrusos: MutableList<List<String>>): List<List<String>> {
//        val listaFirebaseMediatorproducts: MutableList<HistorialTicketEntidad> = mutableListOf()
//        val local = datosLocales()
//        listIntrusos.forEach {
//            val intrusosConvetido = entityConvert(it)
//            listaFirebaseMediatorproducts.add(intrusosConvetido)
//        }
//
//        val productosToDeleteInFirestore =
//            listaFirebaseMediatorproducts.filterNot { firestoreproductos ->
//                local.any { it.Codigo == firestoreproductos.Codigo }
//            }
        return emptyList()
    }

    /**
     * @param: MutableList<List<String>>
     * @return: List<List<String>>
     * @funcion: recibe una lista de listas mutables, para luego comparar con las base de datos locales de los posibles datos diferentes
     * tomando la base la lista entrante como referencia. Luego debuelve en una lista los datos no iguales, los cuales no se encuentran
     * en base de dato local.
     */
    override suspend fun compararLocalParriba(listIntrusos: List<List<String>>): List<List<String>> {
//        val listaFirebaseMediatorproducts: MutableList<HistorialTicketEntidad> = mutableListOf()
//        val local = datosLocales()
//        listIntrusos.forEach {
//            val intrusosConvetido = entityConvert(it)
//            listaFirebaseMediatorproducts.add(intrusosConvetido)
//        }
//        val listaNoMutable: List<HistorialTicketEntidad> = listaFirebaseMediatorproducts
//        val productToAddInFirebase = local.filterNot { firestoreproductos ->
//            listaNoMutable.any {
//                it == firestoreproductos
//            }
//        }
        return emptyList()
    }


    /**
     * @return  List<ProductEntity>
     * @funcion: captura todos los datos de la tabla Productos y los debuelve en una lista de objetos ProductEntity.
     */
//    private suspend fun datosLocales(): List<HistorialTicketEntidad> {
//        return coroutineScope {
//            val listProduct = async { dao.getAllTicketsNormal() }
//            return@coroutineScope listProduct.await()
//
//        }
//    }

}
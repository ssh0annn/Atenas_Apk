package com.solidtype.atenas_apk_2.historial_ventas.data.remoteHistoVentaFB.QueryDBHistorial

import android.util.Log
import com.solidtype.atenas_apk_2.historial_ventas.data.local.dao.actualizacion.ventaDao
import com.solidtype.atenas_apk_2.historial_ventas.data.remoteHistoVentaFB.intefaces.QueryDBHistorialVentas

import javax.inject.Inject

class QueryDBHistorialVentasVentaImpl @Inject constructor(
    private val dao: ventaDao
): QueryDBHistorialVentas {

    /**
     * @param: List<String>
     * @return: Data Object
     * @throws: Exception si no es compatible los datos con el objeto espesificado.
     * @funcionamiento: Es para uso interno, para convertir una lista de strings en datos adecuados para el objeto HistorialVentaEntidad
     * Favor verificar el formato de este objeto antes de someter la lista.
     * Los elementos deben ser igual a 12.
     */
//    private fun entityConvert(it: List<String>): HistorialVentaEntidad {
//        if (it.size == 13) {
//            try {
//                return HistorialVentaEntidad(
//                    Codigo = it[0].toInt(),
//                    Nombre = it[1],
//                    NumeroFactura = it[2].toInt(),
//                    Descripcion = it[3],
//                    Imei = it[4],
//                    Cantidad = it[5].toInt(),
//                    Categoria = it[6],
//                    Modelo = it[7],
//                    Marca = it[8],
//                    Precio = it[9].toDouble(),
//                    TipoVenta = it[10],
//                    Total = it[11].toDouble(),
//                    FechaIni = it[12].toIsoDate().toLocalDate()
//                )
//            } catch (e: Exception) {
//                println("Este es la razon lista: $it, size ${it.size}")
//                throw Exception("El tipo de la lista no es compatible con la Entity HistorialVentaEntidad $e")
//
//            }
//        }
//        throw Exception("El tamaño de la lista entregada no es compatible")
//    }


    /**
     * @param  List<HistorialVentaEntidad>
     * @return List<List<String>>
     * @funcionamiento Funcion de uso interno para convertir una entidad de tipo HistorialVentaEntidad en una lista de lista de Strings.
     * Favor ver la data class HistorialVentaEntidad.
     */
//    private fun entityToListString(data: List<HistorialVentaEntidad>): List<List<String>> {
//        val mutableListData: MutableList<List<String>> = mutableListOf()
//        if (data.isNotEmpty()) {
//            data.forEach {
//                val mutableList = mutableListOf<String>()
//                mutableList.add(it.Codigo.toString())
//                mutableList.add(it.Nombre)
//                mutableList.add(it.NumeroFactura.toString())
//                mutableList.add(it.Descripcion)
//                mutableList.add(it.Imei)
//                mutableList.add(it.Cantidad.toString())
//                mutableList.add(it.Categoria)
//                mutableList.add(it.Modelo)
//                mutableList.add(it.Marca)
//                mutableList.add(it.Precio.toString())
//                mutableList.add(it.TipoVenta)
//                mutableList.add(it.Total.toString())
//                mutableList.add(it.FechaIni.toString())
//                mutableListData.add(mutableList)
//            }
//        }
//        return mutableListData
//
//    }

    /**
     * @return: List<List<String>>
     * @funcionamiento: captura todo los productos de la base de dato local espesificcamente de la tabla HistorialVentaEntidad.
     * Favor ver el objeto ProductEntity para mas informacion.
     *
     */

    override suspend fun getallDataHistorial(): List<List<String>> {
        var mutableListData: List<List<String>> = emptyList()
        //var listaDeEntity = emptyList<HistorialVentaEntidad>()
//        coroutineScope {
//            val response = async { listaDeEntity = dao.getHistorialNormal() }
//            response.await()
//            if (listaDeEntity.isNotEmpty()) {
//                mutableListData = entityToListString(listaDeEntity)
//            }
//        }
        Log.d("TestOrdenBaseDatosLocal","funcion: getAllHistorial()  Orden del objeto :Z$mutableListData")
        return mutableListData
    }

    /**
     * @param: MutableList<List<String>>
     * @return: Unit
     * @throws: El tipo de la lista no es compatible con la Entity producto
     * @funcionamiento: Inserta productos en base de datos local si la lista es compatible con el formato para ProductEntity,
     * Esta funcion integra un hilo interno. Favor llamar desde una funcion suspendida.
     */
    override suspend fun insertAllHistoral(dataToInsert: MutableList<List<String>>) {
        //val lista: MutableList<HistorialVentaEntidad> = mutableListOf()
        dataToInsert.forEach {
            try {
               // lista.add(entityConvert(it))
            } catch (e: Exception) {
                throw Exception("El tipo de la lista no es compatible con la Entity producto $e")
            }
        }
//        coroutineScope {
//            println("Aqui veamos la lista: $lista y siez ${lista.size}")
//            Log.e("EntreHitorial","DATOS DE FIRESTORE A DB LOCAL SON: $lista")
//
//            val response = async { dao.insertAllHistorialVenta(lista) }
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
//        val listaFirebaseMediatorproducts: MutableList<HistorialVentaEntidad> = mutableListOf()
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
//        val listaFirebaseMediatorproducts: MutableList<HistorialVentaEntidad> = mutableListOf()
//        val local = datosLocales()
//        listIntrusos.forEach {
//            val intrusosConvetido = entityConvert(it)
//            listaFirebaseMediatorproducts.add(intrusosConvetido)
//        }
//        val listaNoMutable: List<HistorialVentaEntidad> = listaFirebaseMediatorproducts
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
//    private suspend fun datosLocales(): List<HistorialVentaEntidad> {
//        return coroutineScope {
//            val listProduct = async { dao.getHistorialNormal() }
//            return@coroutineScope listProduct.await()
//
//        }
//    }


}

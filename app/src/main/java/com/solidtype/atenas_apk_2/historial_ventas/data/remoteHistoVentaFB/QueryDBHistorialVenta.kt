package com.solidtype.atenas_apk_2.historial_ventas.data.remoteHistoVentaFB

import android.util.Log
import com.solidtype.atenas_apk_2.historial_ventas.data.local.dao.HistorialVentaDAO
import com.solidtype.atenas_apk_2.historial_ventas.domain.model.HistorialVentaEntidad
import com.solidtype.atenas_apk_2.products.domain.model.ProductEntity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import javax.inject.Inject

class QueryDBHistorialVenta @Inject constructor(
    private val dao: HistorialVentaDAO
) {

    /**
     * @param: List<String>
     * @return: Data Object
     * @throws: Exception si no es compatible los datos con el objeto espesificado.
     * @funcionamiento: Es para uso interno, para convertir una lista de strings en datos adecuados para el objeto HistorialVentaEntidad
     * Favor verificar el formato de este objeto antes de someter la lista.
     * Los elementos deben ser igual a 12.
     */
    private fun entityConvert(it: List<String>): HistorialVentaEntidad {
        if (it.size == 12) {
            try {

                return HistorialVentaEntidad(
                    Codigo = it[0].toInt(),
                    Nombre = it[1],
                    Descripcion = it[2],
                    Cantidad = it[3].toInt(),
                    Categoria = it[4],
                    Modelo = it[5],
                    Marca = it[6],
                    Precio = it[7].toDouble(),
                    TipoVenta = it[8],
                    Total = it[9].toDouble(),
                    FechaIni = it[10],
                    FechaFin = it[11]

                )
            } catch (e: Exception) {
                println("Este es la razon lista: $it, size ${it.size}")
                throw Exception("El tipo de la lista no es compatible con la Entity HistorialVentaEntidad $e")

            }
        }
        throw Exception("El tamaño de la lista entregada no es compatible")
    }


    /**
     * @param  List<HistorialVentaEntidad>
     * @return List<List<String>>
     * @funcionamiento Funcion de uso interno para convertir una entidad de tipo HistorialVentaEntidad en una lista de lista de Strings.
     * Favor ver la data class HistorialVentaEntidad.
     */
    private fun entityToListString(data: List<HistorialVentaEntidad>): List<List<String>> {
        val mutableListData: MutableList<List<String>> = mutableListOf()
        if (data.isNotEmpty()) {
            data.forEach {
                val mutableList = mutableListOf<String>()
                mutableList.add(it.Codigo.toString())
                mutableList.add(it.Nombre)
                mutableList.add(it.Descripcion)
                mutableList.add(it.Cantidad.toString())
                mutableList.add(it.Categoria)
                mutableList.add(it.Modelo)
                mutableList.add(it.Marca)
                mutableList.add(it.Precio.toString())
                mutableList.add(it.TipoVenta)
                mutableList.add(it.Total.toString())
                mutableList.add(it.FechaFin)
                mutableList.add(it.FechaIni)
                mutableListData.add(mutableList)
            }
        }
        return mutableListData

    }

    /**
     * @return: List<List<String>>
     * @funcionamiento: captura todo los productos de la base de dato local espesificcamente de la tabla HistorialVentaEntidad.
     * Favor ver el objeto ProductEntity para mas informacion.
     *
     */
    suspend fun getAllHistorial(): List<List<String>> {
        var mutableListData: List<List<String>> = emptyList()
        var listaDeEntity = emptyList<HistorialVentaEntidad>()
        coroutineScope {
            val response = async { listaDeEntity = dao.getHistorialNormal() }
            response.await()
            if (listaDeEntity.isNotEmpty()) {
                mutableListData = entityToListString(listaDeEntity)
            }
        }
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
    suspend fun insertAllHistoral(dataToInsert: MutableList<List<String>>) {
        val lista: MutableList<HistorialVentaEntidad> = mutableListOf()
        dataToInsert.forEach {
            try {
                lista.add(entityConvert(it))
            } catch (e: Exception) {
                throw Exception("El tipo de la lista no es compatible con la Entity producto $e")
            }
        }
        coroutineScope {
            println("Aqui veamos la lista: $lista y siez ${lista.size}")
            val response = async { dao.insertAllHistorialVenta(lista) }
            response.await()
        }
    }

    /**
     * @param: MutableList<List<String>>
     * @return: List<List<String>>
     * @funcion: recibe una lista de listas mutables, para luego comparar con las base de datos locales de los posibles datos diferentes
     * tomando la base de datos local como referencia de "Single true of trust". Luego debuelve en una lista los datos no iguales.
     */
    suspend fun compararIntrusos(listIntrusos: MutableList<List<String>>): List<List<String>> {
        val listaFirebaseMediatorproducts: MutableList<HistorialVentaEntidad> = mutableListOf()
        val local = datosLocales()
        listIntrusos.forEach {
            val intrusosConvetido = entityConvert(it)
            listaFirebaseMediatorproducts.add(intrusosConvetido)
        }

        val productosToDeleteInFirestore =
            listaFirebaseMediatorproducts.filterNot { FireStoreHistorial ->
                local.any { it.Codigo == FireStoreHistorial.Codigo }
            }
        return entityToListString(productosToDeleteInFirestore)
    }

    /**
     * @param: MutableList<List<String>>
     * @return: List<List<String>>
     * @funcion: recibe una lista de listas mutables, para luego comparar con las base de datos locales de los posibles datos diferentes
     * tomando la base la lista entrante como referencia. Luego debuelve en una lista los datos no iguales, los cuales no se encuentran
     * en base de dato local.
     */
    suspend fun compararLocalParriba(listIntrusos: List<List<String>>): List<List<String>> {
        val listaFirebaseMediatorHistorial: MutableList<HistorialVentaEntidad> = mutableListOf()
        val local = datosLocales()


        Log.d("dataHistorialToUpFirestore","Funcion compararLocalParriba()")
        Log.d("dataHistorialToUpFirestore : Local","Datos locales Nuevo para compara con firebase $local")
        Log.d("dataHistorialToUpFirestore","Datos para actualizar en Firebase $listIntrusos")
        listIntrusos.forEach {
            val intrusosConvetido = entityConvert(it)
            listaFirebaseMediatorHistorial.add(intrusosConvetido)
        }
        val listaNoMutable: List<HistorialVentaEntidad> = listaFirebaseMediatorHistorial
        val productToAddInFirebase = local.filterNot { firestoreproductos ->
            listaNoMutable.any {
                it == firestoreproductos
            }
        }
        Log.d("DatoToUpFireStore", "Estos son: ${entityToListString(productToAddInFirebase)}")
        return entityToListString(productToAddInFirebase)
    }

    /**
     * @return  List<ProductEntity>
     * @funcion: captura todos los datos de la tabla Productos y los debuelve en una lista de objetos ProductEntity.
     */
    private suspend fun datosLocales(): List<HistorialVentaEntidad> {
        return coroutineScope {
            val listProduct = async { dao.getHistorialNormal() }
            return@coroutineScope listProduct.await()

        }
    }


}

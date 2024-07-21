package com.solidtype.atenas_apk_2.products.data.remote.remoteProFB.dataDb.DataDbProducts


import com.solidtype.atenas_apk_2.products.data.local.dao.inventarioDao
import com.solidtype.atenas_apk_2.products.data.remote.remoteProFB.interfaces.QueryDBlocal
import com.solidtype.atenas_apk_2.products.domain.model.actualizacion.inventario
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.first
import javax.inject.Inject

/**
 * @constructor:private val dao: ProductDao
 * @funcionamiento: Los parametros del constructor los recibe de daggerHilt.
 * Esta clase esta atada a la data class ProductEntity y a la tabla Productos de RoomDatabase.
 */
class QueryDBlocalImpl @Inject constructor(
    private val dao: inventarioDao
): QueryDBlocal {
    /**
     * @param: List<String>
     * @return: Data Object
     * @throws: Exception si no es compatible los datos con el objeto espesificado.
     * @funcionamiento: Es para uso interno, para convertir una lista de strings en datos adecuados para el objeto ProductEntity
     * Favor verificar el formato de este objeto antes de someter la lista.
     * Los elementos deben ser igual a 9.
     */

    private fun entityConvert(it: List<String>): inventario? {
        if (it.size == 9) {
            try {
                //Reparar

                return null
            } catch (e: Exception) {
                println("Este es la razon lista: $it, size ${it.size}")
                throw Exception("El tipo de la lista no es compatible con la Entity producto $e")

            }
        }
        throw Exception("El tamaño de la lista entregada no es compatible")
    }

    /**
     * @param  List<ProductEntity>
     * @return List<List<String>>
     * @funcionamiento Funcion de uso interno para convertir una entidad de tipo ProductEntity en una lista de lista de Strings.
     * Favor ver la data class ProductEntity.
     */
    private fun entityToListString(data: List<inventario>): List<List<String>> {
        val mutableListData: MutableList<List<String>> = mutableListOf()
        if (data.isNotEmpty()) {
            data.forEach {
                val mutableList = mutableListOf<String>()

                mutableListData.add(mutableList)
            }

        }
        return mutableListData

    }

    /**
     * @return: List<List<String>>
     * @funcionamiento: captura todo los productos de la base de dato local espesificcamente de la tabla Productos.
     * Favor ver el objeto ProductEntity para mas informacion.
     *
     */
    override suspend fun getAllProducts(): List<List<String>> {
        var mutableListData: List<List<String>> = emptyList()
        var listaDeEntity = emptyList<inventario>()
        coroutineScope {
            val response = async { dao.getInventario().collect{listaDeEntity = it} }
            response.await()
            if (listaDeEntity.isNotEmpty()) {
                mutableListData = entityToListString(listaDeEntity)
            }
        }
        return mutableListData
    }

    /**
     * @param: MutableList<List<String>>
     * @return: Unit
     * @throws: El tipo de la lista no es compatible con la Entity producto
     * @funcionamiento: Inserta productos en base de datos local si la lista es compatible con el formato para ProductEntity,
     * Esta funcion integra un hilo interno. Favor llamar desde una funcion suspendida.
     */
    override suspend fun insertAllProducts(dataToInsert: MutableList<List<String>>) {
        val lista: MutableList<inventario> = mutableListOf()
        dataToInsert.forEach {
            try {
                entityConvert(it)?.let { it1 -> lista.add(it1) }
            } catch (e: Exception) {
                throw Exception("El tipo de la lista no es compatible con la Entity producto $e")
            }
        }
        coroutineScope {
            println("Aqui veamos la lista: $lista y siez ${lista.size}")
            val response = async { dao.addInventarios(lista) }
            response.await()
        }
    }

    /**
     * @param: MutableList<List<String>>
     * @return: List<List<String>>
     * @funcion: recibe una lista de listas mutables, para luego comparar con las base de datos locales de los posibles datos diferentes
     * tomando la base de datos local como referencia de "Single true of trust". Luego debuelve en una lista los datos no iguales.
     */
    override suspend fun compararIntrusos(listIntrusos: MutableList<List<String>>): List<List<String>> {
        val listaFirebaseMediatorproducts: MutableList<inventario> = mutableListOf()
        val local = datosLocales()
        listIntrusos.forEach {
            val intrusosConvetido = entityConvert(it)
            if (intrusosConvetido != null) {
                listaFirebaseMediatorproducts.add(intrusosConvetido)
            }
        }

        val productosToDeleteInFirestore =
            listaFirebaseMediatorproducts.filterNot { firestoreproductos ->
                local.any { it.id_inventario == firestoreproductos.id_inventario }
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
    override suspend fun compararLocalParriba(listIntrusos: List<List<String>>): List<List<String>> {
        val listaFirebaseMediatorproducts: MutableList<inventario> = mutableListOf()
        val local = datosLocales()
        listIntrusos.forEach {
            val intrusosConvetido = entityConvert(it)
            if (intrusosConvetido != null) {
                listaFirebaseMediatorproducts.add(intrusosConvetido)
            }
        }
        val listaNoMutable: List<inventario> = listaFirebaseMediatorproducts
        val productToAddInFirebase = local.filterNot { firestoreproductos ->
            listaNoMutable.any {
                it == firestoreproductos
            }
        }
        return entityToListString(productToAddInFirebase)
    }

    /**
     * @return  List<ProductEntity>
     * @funcion: captura todos los datos de la tabla Productos y los debuelve en una lista de objetos ProductEntity.
     */
    private suspend fun datosLocales(): List<inventario> {
        return coroutineScope {
            val listProduct = async { dao.getInventario().first() }
            return@coroutineScope listProduct.await()

        }
    }
}




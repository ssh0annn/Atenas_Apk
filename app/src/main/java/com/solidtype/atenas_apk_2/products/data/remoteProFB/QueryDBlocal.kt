package com.solidtype.atenas_apk_2.products.data.remoteProFB

import com.solidtype.atenas_apk_2.products.data.local.dao.ProductDao
import com.solidtype.atenas_apk_2.products.domain.model.ProductEntity
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import javax.inject.Inject

/**
 * @constructor:private val dao: ProductDao
 * @funcionamiento: Los parametros del constructor los recibe de daggerHilt.
 * Esta clase esta atada a la data class ProductEntity y a la tabla Productos de RoomDatabase.
 */
class QueryDBlocal @Inject constructor(
    private val dao: ProductDao

) {
    /**
     * @param: List<String>
     * @return: Data Object
     * @throws: Exception si no es compatible los datos con el objeto espesificado.
     * @funcionamiento: Es para uso interno, para convertir una lista de strings en datos adecuados para el objeto ProductEntity
     * Favor verificar el formato de este objeto antes de someter la lista.
     * Los elementos deben ser igual a 9.
     */
    private fun entityConvert(it: List<String>): ProductEntity {
        if (it.size == 9) {
            try {

                return ProductEntity(
                    Code_Product = it[0].toInt(),
                    Name_Product = it[1],
                    Description_Product = it[2],
                    Category_Product = it[3],
                    Price_Product = it[4].toDouble(),
                    Model_Product = it[5],
                    Price_Vending_Product = it[6].toDouble(),
                    Tracemark_Product = it[7],
                    Count_Product = it[8].toInt(),
                )
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
    private fun entityToListString(data: List<ProductEntity>): List<List<String>> {
        val mutableListData: MutableList<List<String>> = mutableListOf()
        if (data.isNotEmpty()) {
            data.forEach {
                val mutableList = mutableListOf<String>()
                mutableList.add(it.Code_Product.toString())
                mutableList.add(it.Name_Product)//Aqui era el problema jajja
                mutableList.add(it.Description_Product)
                mutableList.add(it.Category_Product)
                mutableList.add(it.Price_Product.toString())
                mutableList.add(it.Model_Product)
                mutableList.add(it.Price_Vending_Product.toString())
                mutableList.add(it.Tracemark_Product)
                mutableList.add(it.Count_Product.toString())
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
    suspend fun getAllProducts(): List<List<String>> {
        var mutableListData: List<List<String>> = emptyList()
        var listaDeEntity = emptyList<ProductEntity>()
        coroutineScope {
            val response = async { listaDeEntity = dao.getProductss() }
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
    suspend fun insertAllProducts(dataToInsert: MutableList<List<String>>) {
        val lista: MutableList<ProductEntity> = mutableListOf()
        dataToInsert.forEach {
            try {
                lista.add(entityConvert(it))
            } catch (e: Exception) {
                throw Exception("El tipo de la lista no es compatible con la Entity producto $e")
            }
        }
        coroutineScope {
            println("Aqui veamos la lista: $lista y siez ${lista.size}")
            val response = async { dao.insertAllProducts(lista) }
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
        val listaFirebaseMediatorproducts: MutableList<ProductEntity> = mutableListOf()
        val local = datosLocales()
        listIntrusos.forEach {
            val intrusosConvetido = entityConvert(it)
            listaFirebaseMediatorproducts.add(intrusosConvetido)
        }
        val productosToDeleteInFirestore =
            listaFirebaseMediatorproducts.filterNot { firestoreproductos ->
                local.any { it.Code_Product == firestoreproductos.Code_Product }
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
        val listaFirebaseMediatorproducts: MutableList<ProductEntity> = mutableListOf()
        val local = datosLocales()
        listIntrusos.forEach {
            val intrusosConvetido = entityConvert(it)
            listaFirebaseMediatorproducts.add(intrusosConvetido)
        }
        val listaNoMutable: List<ProductEntity> = listaFirebaseMediatorproducts
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
    private suspend fun datosLocales(): List<ProductEntity> {
        return coroutineScope {
            val listProduct = async { dao.getProductss() }
            return@coroutineScope listProduct.await()

        }
    }
}




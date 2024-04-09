package com.solidtype.atenas_apk_2.products.data.remoteProFB

import com.solidtype.atenas_apk_2.products.data.local.ProductDataBase
import com.solidtype.atenas_apk_2.products.data.local.dao.ProductDao
import com.solidtype.atenas_apk_2.products.domain.model.ProductEntity
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import javax.inject.Inject

class QueryDBlocal @Inject constructor(
    private val dao: ProductDao,
    private val database: ProductDataBase
) {

    private fun entityConvert(it: List<String>): ProductEntity {

        if (it.size == 9) {
            try {
                val products = ProductEntity(
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

                return products
            } catch (e: Exception) {
                throw Exception("El tipo de la lista no es compatible con la Entity producto $e")

            }
        }
        throw Exception ("El tamaño de la lista enregada no es compatible")
    }


    private fun entityToListString(data: List<ProductEntity>): List<List<String>>{

        val mutableListData: MutableList<List<String>> = mutableListOf()

        if (data.isNotEmpty()) {
                data.forEach {
                    val mutableList = mutableListOf<String>()
                    mutableList.add(it.Code_Product.toString())
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
    suspend fun getAllProducts(): List<List<String>> {
        var mutableListData: List<List<String>> = emptyList()
        var listaDeEntity = emptyList<ProductEntity>()
        coroutineScope {
            val response = async { listaDeEntity= dao.getProductss() }
            response.await()
            if (listaDeEntity.isNotEmpty()) {
                mutableListData = entityToListString(listaDeEntity)
            }
        }

        return mutableListData
    }

    suspend fun insertAllProducts(dataToInsert: MutableList<List<String>>) {
        val lista: MutableList<ProductEntity> = mutableListOf()
        dataToInsert.forEach {
                try {
                    lista.add( entityConvert(it))
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

    suspend fun compararIntrusos(listIntrusos: MutableList<List<String>>): List<List<String>> {
        val listaFirebaseMediatorproducts: MutableList<ProductEntity> = mutableListOf()
        val local = localDate()
        listIntrusos.forEach {
            val intrusosConvetido = entityConvert(it)
            listaFirebaseMediatorproducts.add(intrusosConvetido)
        }
        val productosToDeleteInFirestore = listaFirebaseMediatorproducts.filterNot { firestoreproductos ->
            local.any { it.Code_Product == firestoreproductos.Code_Product }
        }
        return entityToListString(productosToDeleteInFirestore)
    }
    suspend fun compararLocalParriba(listIntrusos: List<List<String>>): List<List<String>> {
        println("La lista que recibo : $listIntrusos y tamamnio : ${listIntrusos[0].size}")
        val listaFirebaseMediatorproducts: MutableList<ProductEntity> = mutableListOf()
        val local = localDate()
        listIntrusos.forEach {
            val intrusosConvetido = entityConvert(it)
            listaFirebaseMediatorproducts.add(intrusosConvetido)
        }
        val listaNoMutable:List<ProductEntity> = listaFirebaseMediatorproducts
        val productToAddInFirebase = local.filterNot { firestoreproductos ->
            listaNoMutable.any { it == firestoreproductos
            }
        }
        return entityToListString(productToAddInFirebase)
    }





    private suspend fun localDate(): List<ProductEntity>{
       return coroutineScope {
           val listProduct =async { dao.getProductss()}
           return@coroutineScope listProduct.await()

       }


    }


}




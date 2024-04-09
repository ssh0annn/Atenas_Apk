package com.solidtype.atenas_apk_2.products.data.remoteProFB

import android.util.Log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.serialization.json.Json
import javax.inject.Inject

class MediatorProducts @Inject constructor(
    private val queryDblocal : QueryDBlocal,
    private val queryFireStore : QuerysFirstore
) {

    suspend fun ayscPro(){
        /*
        val datosFirebaseFirestore = caputarDatosFirebase()
        val datoDS = stringToListOfList(datosFirebaseFirestore)

         */
        if (!verficarDataDBLocal()){
            /*
            insertaInDbLocal(datoDS)
             */
            println("La base de dato esta vacia")
        }else{

            //elimina
            /*
            val sacarIntruso = sacarIntrusos(datoDS)

            if (sacarIntruso.isNotEmpty()){
                queryFireStore.deleteDataFirebase("productos",sacarIntruso)
            }

             */

            //agregar datos a firebase
            val dataLocales = capturarDatosDB()
            queryFireStore.insertToFirebase("productos",dataLocales)

        }

    }

    private fun stringToListOfList(datos:String): MutableList<List<String>>{
        return Json.decodeFromString(datos)
    }


    private suspend fun verficarDataDBLocal (): Boolean{
       val dataLocalState =  queryDblocal.getAllProducts()
        if (dataLocalState.isEmpty()){
            return true
        }else{
            return false
        }
    }

    private suspend fun insertaInDbLocal (data: MutableList<List<String>>){
        queryDblocal.insertAllProducts(data)
    }

    private suspend fun sacarIntrusos (posiblesIntrusos : MutableList<List<String>>) : List<List<String>>{
        val intrusos  = queryDblocal.compararIntrusos(posiblesIntrusos)

        if (intrusos.isNotEmpty()){
            queryFireStore.deleteDataFirebase("productos",intrusos)
            println("estos son los intrusos --> $intrusos <--")
        }
        return emptyList()
    }

    private suspend fun capturarDatosDB() = queryDblocal.getAllProducts()

    private suspend fun caputarDatosFirebase() = queryFireStore.getAllDataFirebase("productos")


}
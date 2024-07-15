package com.solidtype.atenas_apk_2.gestion_proveedores.data.ddbb

import com.solidtype.atenas_apk_2.gestion_proveedores.data.persona
import com.solidtype.atenas_apk_2.gestion_proveedores.data.personaDao
import com.solidtype.atenas_apk_2.gestion_proveedores.data.remote.mediadorPersonaImpl
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import javax.inject.Inject

class
personaDBImpl @Inject constructor(
    private val personaDB: personaDao
)  {

     suspend fun getAllPersona(): List<persona> {
         return withContext(Dispatchers.Default) {
             personaDB.getPersona()
         }
    }

    suspend fun getFLowAllPersona(): Flow<List<persona>> {
        return withContext(Dispatchers.Default) {
            personaDB.getPersonas()
        }
    }


    suspend fun insertPersonas(listaPersona: List<persona>){
        if (listaPersona.isNotEmpty()) {
            withContext(Dispatchers.Default) {
                personaDB.addPersonas(listaPersona)
            }
        } else {
            println("$listaPersona <-- la lista de persona está vacía")
        }

    }

    suspend fun insertOrUpdatePersona(persona: persona) {
        withContext(Dispatchers.IO) {
            personaDB.addPersona(persona)
        }
    }


    suspend fun deletePersona(persona: persona) {
        withContext(Dispatchers.IO) {
            personaDB.deletePersona(persona)
        }
    }





}
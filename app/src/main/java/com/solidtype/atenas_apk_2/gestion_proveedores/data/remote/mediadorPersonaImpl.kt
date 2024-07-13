package com.solidtype.atenas_apk_2.gestion_proveedores.data.remote

import com.solidtype.atenas_apk_2.core.remote.dataCloud.DataCloudImpl
import com.solidtype.atenas_apk_2.gestion_proveedores.data.ddbb.personaDBImpl
import com.solidtype.atenas_apk_2.gestion_proveedores.data.persona
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.forEach
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.apache.logging.log4j.ThreadContext.init
import javax.inject.Inject

class mediadorPersonaImpl @Inject constructor(
    private val personaDB: personaDBImpl,
    private val dataCloudImpl: DataCloudImpl
) {
        val subCollection = "Persona"



    suspend fun syncPersona(){
            println("syncPersona entre a la funcion")
            println()
            val listaPersonaFirebase = dataCloudImpl.getSubColletionPersona(subCollection)
            println("datos de personas que vienen de firebase $listaPersonaFirebase <--------------------------------")
            println()
            val listaPersonaDbLocal = personaDB.getAllPersona()
            println("datos de personas que vienen de DBlocal $listaPersonaDbLocal <--------------------------------")
            println()

            if (listaPersonaFirebase.isNotEmpty() && listaPersonaDbLocal.isEmpty()){
                personaDB.insertPersonas(listaPersonaFirebase)
            }
            else if (listaPersonaFirebase.isEmpty() && listaPersonaDbLocal.isNotEmpty() ){
                dataCloudImpl.insertarPersona(listaPersonaDbLocal,subCollection)
            }
            else {

            }
    }




        //esta function se encarga de escuchar los datos de firestore y inserta y editar los  cambios en la local
          fun observeDataCloudChanges() {
            CoroutineScope(Dispatchers.IO).launch {
                dataCloudImpl.getSubCollectionPersonaRealtime(subCollection).collect { personas ->
                    syncWithLocalDatabase(personas)
                }
            }
          }

    //esta function se encarga de escuchar los datos locales y inserta y editar los cambios en firestore

          fun observeLocalDatabaseChanges() {
            CoroutineScope(Dispatchers.IO).launch {
                personaDB.getFLowAllPersona().collect() { personas ->
                    syncWithFirestore(personas)
                }
            }
          }

        //esta funcion la usa el observador de firebase

        private suspend fun syncWithLocalDatabase(personas: List<persona>) {
                withContext(Dispatchers.IO) {
                    personaDB.insertPersonas(personas)
                }
        }


       // esta funcion la usa el observador de la base de datos local
        private suspend fun syncWithFirestore(personas: List<persona>) {
            personas.forEach { persona ->
                dataCloudImpl.updatePersonaFirestorePersona(persona, subCollection)
            }
        }




        suspend fun insertOrUpdatePersona(persona: persona) {
            withContext(Dispatchers.IO) {
                dataCloudImpl.updatePersonaFirestorePersona(persona, subCollection)
            }
        }

        suspend fun deletePersona(persona: persona) {
            withContext(Dispatchers.IO) {
                dataCloudImpl.deletePersonaFromFirestorePersona(persona, subCollection)
            }
        }









}
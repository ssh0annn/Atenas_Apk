package com.solidtype.atenas_apk_2.gestion_proveedores.domain.repository

import com.solidtype.atenas_apk_2.gestion_proveedores.data.persona
import kotlinx.coroutines.flow.Flow

interface ClienteProveedorRepository {

    fun mostrarPersonas() : Flow<List<persona>>
    fun getPersonas(tipo_Persona:String): Flow<List<persona>>

    suspend fun crearPersona(persona: persona)

    suspend fun eliminarPersona(cliente: persona)

    fun buscarPersonaTipo(tipo:String, any:String): Flow<List<persona>>


}
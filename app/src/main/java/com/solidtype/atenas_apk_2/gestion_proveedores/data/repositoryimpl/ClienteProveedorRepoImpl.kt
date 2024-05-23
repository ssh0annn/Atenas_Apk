package com.solidtype.atenas_apk_2.gestion_proveedores.data.repositoryimpl

import com.solidtype.atenas_apk_2.gestion_proveedores.data.personaDao
import com.solidtype.atenas_apk_2.gestion_proveedores.domain.modelo.persona
import com.solidtype.atenas_apk_2.gestion_proveedores.domain.repository.ClienteProveedorRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ClienteProveedorRepoImpl @Inject constructor(private val dao:personaDao) :ClienteProveedorRepository{
    override fun mostrarPersonas(): Flow<List<persona>> {
        return dao.getPersonas()
    }

    override fun getPersonas(tipo_Persona: String): Flow<List<persona>> {
         return dao.getPersonasTipo(tipo_Persona)
    }

    override suspend fun crearPersona(persona: persona) {
        dao.addPersona(persona)
    }

    override suspend fun eliminarPersona(cliente: persona) {
        dao.deletePersona(cliente)

    }

    override fun buscarPersonaTipo(tipo: String, any: String): Flow<List<persona>> {
         return dao.getPersonasTipo(tipo, any)
    }
}
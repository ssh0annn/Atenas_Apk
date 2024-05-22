package com.solidtype.atenas_apk_2.gestion_proveedores.domain.casos_usos

import com.solidtype.atenas_apk_2.gestion_proveedores.domain.casos_usos.util.toPersona
import com.solidtype.atenas_apk_2.gestion_proveedores.domain.repository.ClienteProveedorRepository
import javax.inject.Inject

class EliminarPersona @Inject constructor(private val repo: ClienteProveedorRepository) {

    suspend operator fun invoke(persona: Map<String, Any?>) = repo.eliminarPersona(
        persona.toPersona()
    )
}
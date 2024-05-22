package com.solidtype.atenas_apk_2.gestion_proveedores.domain.casos_usos

import com.solidtype.atenas_apk_2.gestion_proveedores.domain.casos_usos.util.toMap
import com.solidtype.atenas_apk_2.gestion_proveedores.domain.repository.ClienteProveedorRepository
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class MostrarPersonas @Inject constructor(private val repo: ClienteProveedorRepository) {

    operator fun invoke() {
        repo.mostrarPersonas().map { personas ->
            personas.map { persona ->
                persona.toMap()
            }
        }
    }
}
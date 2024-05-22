package com.solidtype.atenas_apk_2.gestion_proveedores.domain.casos_usos.casos_proveedores

import com.solidtype.atenas_apk_2.gestion_proveedores.domain.casos_usos.util.toMap
import com.solidtype.atenas_apk_2.gestion_proveedores.domain.repository.ClienteProveedorRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetProveedores @Inject constructor(private val repo: ClienteProveedorRepository) {
    operator fun invoke() : Flow<List<Map<String, Any?>>> {
        return repo.getPersonas("proveedor").map {
            proveedores ->
            proveedores.map { proveedor -> proveedor.toMap() }
        }
    }
}
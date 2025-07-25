package com.solidtype.atenas_apk_2.gestion_proveedores.domain.casos_usos.casos_proveedores

import com.solidtype.atenas_apk_2.gestion_proveedores.domain.casos_usos.util.toProveedor
import com.solidtype.atenas_apk_2.gestion_proveedores.domain.repository.ClienteProveedorRepository
import com.solidtype.atenas_apk_2.gestion_proveedores.presentation.cliente.modelo.Personastodas
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class ProveedoresTodos @Inject constructor(private val repo: ClienteProveedorRepository) {
    operator fun invoke() : Flow<List<Personastodas.Proveedor>> {
        return repo.getPersonas("proveedor").map {
                proveedores ->
            proveedores.filter { it.tipo_persona == "proveedor"}.map { proveedor -> proveedor.toProveedor() }
        }
    }
}
package com.solidtype.atenas_apk_2.gestion_proveedores.domain.casos_usos.casos_proveedores

import com.solidtype.atenas_apk_2.gestion_proveedores.domain.casos_usos.util.toMap
import com.solidtype.atenas_apk_2.gestion_proveedores.domain.casos_usos.util.toProveedor
import com.solidtype.atenas_apk_2.gestion_proveedores.domain.repository.ClienteProveedorRepository
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class BuscarProveedores @Inject constructor(private val repo: ClienteProveedorRepository) {

    operator fun invoke(any:String, switch:Boolean) =

        repo.buscarPersonaTipo("proveedor", any).map {
                proveedores ->
            proveedores.filter { it.estado == switch && it.tipo_persona == "proveedor" }.map { proveedor -> proveedor.toProveedor() }
        }
    }

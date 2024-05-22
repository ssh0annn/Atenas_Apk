package com.solidtype.atenas_apk_2.gestion_proveedores.domain.casos_usos.casos_proveedores

import com.solidtype.atenas_apk_2.gestion_proveedores.domain.casos_usos.util.toPersona
import com.solidtype.atenas_apk_2.gestion_proveedores.domain.repository.ClienteProveedorRepository
import javax.inject.Inject

class CrearProveedor @Inject constructor(private val repo: ClienteProveedorRepository) {

    suspend operator fun invoke(proveedor: Map<String, Any?>){

        repo.crearPersona(proveedor.toPersona(true))
    }

}
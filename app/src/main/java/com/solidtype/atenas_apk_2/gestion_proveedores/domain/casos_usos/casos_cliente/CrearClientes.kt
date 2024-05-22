package com.solidtype.atenas_apk_2.gestion_proveedores.domain.casos_usos.casos_cliente

import com.solidtype.atenas_apk_2.gestion_proveedores.domain.casos_usos.util.toPersona
import com.solidtype.atenas_apk_2.gestion_proveedores.domain.repository.ClienteProveedorRepository
import javax.inject.Inject

class CrearClientes @Inject constructor(private val repo: ClienteProveedorRepository) {

    suspend operator fun invoke(cliente: Map<String, Any?>){

        repo.crearPersona(cliente.toPersona(true))
    }
}
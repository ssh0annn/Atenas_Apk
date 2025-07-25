package com.solidtype.atenas_apk_2.gestion_proveedores.domain.casos_usos.casos_proveedores

import com.solidtype.atenas_apk_2.gestion_proveedores.domain.casos_usos.util.client_builder.PersonaDirector
import com.solidtype.atenas_apk_2.gestion_proveedores.domain.repository.ClienteProveedorRepository
import com.solidtype.atenas_apk_2.gestion_proveedores.presentation.cliente.modelo.Personastodas
import javax.inject.Inject

class EditarProveedores @Inject constructor(private val repo: ClienteProveedorRepository) {

    suspend operator fun invoke(proveedor: Personastodas.Proveedor) {
        repo.actualizarPersona(PersonaDirector.createPersona(proveedor))

    }
}
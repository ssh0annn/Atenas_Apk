package com.solidtype.atenas_apk_2.gestion_proveedores.domain.casos_usos.casos_cliente

import com.solidtype.atenas_apk_2.gestion_proveedores.domain.casos_usos.util.client_builder.PersonaDirector
import com.solidtype.atenas_apk_2.gestion_proveedores.domain.casos_usos.util.toPersona
import com.solidtype.atenas_apk_2.gestion_proveedores.domain.repository.ClienteProveedorRepository
import com.solidtype.atenas_apk_2.gestion_proveedores.presentation.cliente.modelo.Personastodas
import javax.inject.Inject

class CrearClientes @Inject constructor(private val repo: ClienteProveedorRepository) {

    suspend operator fun invoke(cliente:Personastodas.ClienteUI){
        cliente.id_cliente = System.currentTimeMillis()

        repo.crearPersona(PersonaDirector.createPersona(cliente))
    }
}

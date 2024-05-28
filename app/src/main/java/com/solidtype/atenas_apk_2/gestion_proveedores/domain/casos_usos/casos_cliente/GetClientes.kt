package com.solidtype.atenas_apk_2.gestion_proveedores.domain.casos_usos.casos_cliente

import com.solidtype.atenas_apk_2.gestion_proveedores.domain.casos_usos.util.toClienteUI
import com.solidtype.atenas_apk_2.gestion_proveedores.domain.casos_usos.util.toMap
import com.solidtype.atenas_apk_2.gestion_proveedores.domain.repository.ClienteProveedorRepository
import com.solidtype.atenas_apk_2.gestion_proveedores.presentation.cliente.modelo.Personastodas
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetClientes  @Inject constructor(private val repo: ClienteProveedorRepository) {
    operator fun invoke() : Flow<List<Personastodas.ClienteUI>> {
        return repo.getPersonas("cliente").map {
                clientes ->
            clientes.map { cliente -> cliente.toClienteUI() }
        }
    }
}
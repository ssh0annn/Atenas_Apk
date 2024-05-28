package com.solidtype.atenas_apk_2.gestion_proveedores.domain.casos_usos.casos_cliente

import com.solidtype.atenas_apk_2.gestion_proveedores.domain.casos_usos.util.toClienteUI
import com.solidtype.atenas_apk_2.gestion_proveedores.domain.casos_usos.util.toMap
import com.solidtype.atenas_apk_2.gestion_proveedores.domain.repository.ClienteProveedorRepository
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class BuscarClientes  @Inject constructor(private val repo: ClienteProveedorRepository) {

    operator fun invoke(any:String) =

        repo.buscarPersonaTipo("cliente", any).map {
                clientes ->
            clientes.map { cliente -> cliente.toClienteUI() }
        }
}
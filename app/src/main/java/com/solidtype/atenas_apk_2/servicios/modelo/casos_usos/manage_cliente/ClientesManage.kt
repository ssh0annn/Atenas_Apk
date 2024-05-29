package com.solidtype.atenas_apk_2.servicios.modelo.casos_usos.manage_cliente

import com.solidtype.atenas_apk_2.gestion_proveedores.domain.casos_usos.casos_cliente.CasosClientes
import com.solidtype.atenas_apk_2.gestion_proveedores.presentation.cliente.modelo.Personastodas
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ClientesManage @Inject constructor(private val casosCliente:CasosClientes){

    suspend fun agregarNuevoCliente(cliente: Personastodas.ClienteUI){
        casosCliente.crearClientes(cliente)

    }
    fun getListaClientes(): Flow<List<Personastodas.ClienteUI>> {
        return casosCliente.getClientes()
    }

    fun buscarClientes(any:String): Flow<List<Personastodas.ClienteUI>> {
        return casosCliente.buscarClientes(any)
    }
}
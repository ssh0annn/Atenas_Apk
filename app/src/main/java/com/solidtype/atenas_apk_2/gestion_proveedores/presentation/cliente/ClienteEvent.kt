package com.solidtype.atenas_apk_2.gestion_proveedores.presentation.cliente

import com.solidtype.atenas_apk_2.gestion_proveedores.presentation.cliente.modelo.Personastodas

sealed class ClienteEvent {
    data class BuscarClientes(val any: String) : ClienteEvent()

    data class BorrarClientes(val clientes: Personastodas.ClienteUI) : ClienteEvent()

    data class AgregarClientes(val clientes: Personastodas.ClienteUI) : ClienteEvent()

    data class EditarClientes(val clientes: Personastodas.ClienteUI) : ClienteEvent()
    object RestaurarClientes : ClienteEvent()

    object MostrarClientesEvent : ClienteEvent()
    object Switch : ClienteEvent()

    object LimpiarMensaje: ClienteEvent()
}
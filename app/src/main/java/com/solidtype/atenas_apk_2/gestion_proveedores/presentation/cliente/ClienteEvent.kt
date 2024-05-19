package com.solidtype.atenas_apk_2.gestion_proveedores.presentation.cliente

sealed class ClienteEvent {
    data class BuscarClientes(val any: String) : ClienteEvent()

    data class BorrarClientes(val clientes: Map<String, Any?>) : ClienteEvent()

    data class AgregarClientes(val clientes: Map<String, Any?>) : ClienteEvent()

    data class EditarClientes(val clientes: Map<String, Any?>) : ClienteEvent()
    object RestaurarClientes : ClienteEvent()

    object MostrarClientesEvent : ClienteEvent()
}
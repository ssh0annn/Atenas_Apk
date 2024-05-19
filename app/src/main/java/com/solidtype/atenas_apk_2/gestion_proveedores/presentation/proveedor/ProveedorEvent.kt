package com.solidtype.atenas_apk_2.gestion_proveedores.presentation.proveedor

import com.solidtype.atenas_apk_2.gestion_proveedores.presentation.cliente.ClienteEvent

sealed class ProveedorEvent {
    data class BuscarProveedor(val any: String) : ProveedorEvent()

    data class BorrarProveedor(val proveedors: Map<String, Any?>) : ProveedorEvent()

    data class AgregarProveedor(val proveedors: Map<String, Any?>) : ProveedorEvent()

    data class EditarProveedor(val proveedors: Map<String, Any?>) : ProveedorEvent()
    object RestaurarProveedor : ProveedorEvent()

    object MostrarProveedorEvent : ProveedorEvent()
}
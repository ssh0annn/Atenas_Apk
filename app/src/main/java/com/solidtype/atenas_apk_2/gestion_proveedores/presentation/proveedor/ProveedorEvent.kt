package com.solidtype.atenas_apk_2.gestion_proveedores.presentation.proveedor

import com.solidtype.atenas_apk_2.gestion_proveedores.presentation.cliente.ClienteEvent
import com.solidtype.atenas_apk_2.gestion_proveedores.presentation.cliente.modelo.Personastodas

sealed class ProveedorEvent {
    data class BuscarProveedor(val any: String) : ProveedorEvent()

    data class BorrarProveedor(val proveedors: Personastodas.Proveedor) : ProveedorEvent()

    data class AgregarProveedor(val proveedors: Personastodas.Proveedor) : ProveedorEvent()

    data class EditarProveedor(val proveedors: Personastodas.Proveedor) : ProveedorEvent()
    object RestaurarProveedor : ProveedorEvent()

    object MostrarProveedorEvent : ProveedorEvent()
    object Switch : ProveedorEvent()
    object LimpiarMensaje:ProveedorEvent()
}
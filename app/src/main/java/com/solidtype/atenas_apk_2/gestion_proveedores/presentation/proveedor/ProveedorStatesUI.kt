package com.solidtype.atenas_apk_2.gestion_proveedores.presentation.proveedor

import com.solidtype.atenas_apk_2.gestion_proveedores.presentation.cliente.modelo.Personastodas

data class ProveedorStatesUI(
    val proveedores: List<Personastodas.Proveedor> = emptyList(),
    val isLoading: Boolean = false,
    val error: String = ""
)

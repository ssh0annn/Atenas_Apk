package com.solidtype.atenas_apk_2.gestion_proveedores.presentation.proveedor

data class ProveedorStatesUI(
    val proveedores: List<Map<String, Any?>> = emptyList(),
    val isLoading: Boolean = false,
    val error: String = ""
)

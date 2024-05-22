package com.solidtype.atenas_apk_2.gestion_proveedores.presentation.cliente

data class ClienteStateUI(
    val clientes: List<Map<String, Any?>> = emptyList(),
    val isLoading: Boolean = false,
    val error: String = ""
)

package com.solidtype.atenas_apk_2.facturacion.presentation

data class FacturaUI(
    val isLoading: Boolean = false,
    val facturas: List<List<String>> = listOf(),
    val buscar: List<List<String>> = listOf(),
    val detalles: List<List<String>> = listOf(),
    val error: String=""

)
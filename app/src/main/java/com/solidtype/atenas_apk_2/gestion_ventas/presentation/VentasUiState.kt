package com.solidtype.atenas_apk_2.gestion_ventas.presentation

import com.solidtype.atenas_apk_2.gestion_proveedores.presentation.cliente.modelo.Personastodas
import com.solidtype.atenas_apk_2.gestion_usuarios.domain.modelo.usuario

data class VentasUiState(
    val listaClientes: List<Personastodas.ClienteUI?> = emptyList(),
    val usuario: usuario? = null,
    val isLoading:Boolean = false,
    val impuestos: Boolean = false,
    val abono: Double = 0.0,
    val subtotal: Double = 0.0,
    val total: Double = 0.0
)
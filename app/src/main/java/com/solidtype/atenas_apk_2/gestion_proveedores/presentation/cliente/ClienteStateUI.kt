package com.solidtype.atenas_apk_2.gestion_proveedores.presentation.cliente

import com.solidtype.atenas_apk_2.gestion_proveedores.presentation.cliente.modelo.Personastodas

data class ClienteStateUI(
    val clientes: List<Personastodas.ClienteUI> = emptyList(),
    val isLoading: Boolean = false,
    val mensaje: String = "",
    val switch:Boolean = false
)

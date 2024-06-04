package com.solidtype.atenas_apk_2.dispositivos.presentation

import com.solidtype.atenas_apk_2.dispositivos.model.Dispositivo

data class DispositivoUiState(
    val isLoading:Boolean = false,
    val dispositivos: List<Dispositivo> = emptyList(),

)

package com.solidtype.atenas_apk_2.gestion_facturar.domain.casos_usos.bluetooth.presentation

import android.bluetooth.BluetoothDevice


data class BluetoothUiState(
    val scannedDevices: List<BluetoothDevice> = emptyList(),
    val pairedDevices: List<BluetoothDevice> = emptyList(),
)
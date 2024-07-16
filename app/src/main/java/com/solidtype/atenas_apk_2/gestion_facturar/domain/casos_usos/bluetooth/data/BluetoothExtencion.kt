package com.solidtype.atenas_apk_2.gestion_facturar.domain.casos_usos.bluetooth.data

import android.annotation.SuppressLint
import android.bluetooth.BluetoothDevice
import com.solidtype.atenas_apk_2.gestion_facturar.domain.casos_usos.bluetooth.domain.BluetoothDeviceDomain

@SuppressLint("MissingPermission")
fun BluetoothDevice.toBluetoothDeviceDomain(): BluetoothDeviceDomain {
    return BluetoothDeviceDomain(
        name = name,
        address = address
    )
}
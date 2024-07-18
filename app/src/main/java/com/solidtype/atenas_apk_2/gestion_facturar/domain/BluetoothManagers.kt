package com.solidtype.atenas_apk_2.gestion_facturar.domain

interface BluetoothManagers {

    fun scannDevices()
    fun isPrinterConnected():Boolean
    fun permisosGranted():Boolean




}
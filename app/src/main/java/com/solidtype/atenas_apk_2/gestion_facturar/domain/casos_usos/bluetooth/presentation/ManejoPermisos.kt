package com.solidtype.atenas_apk_2.gestion_facturar.domain.casos_usos.bluetooth.presentation


import android.Manifest
import android.app.Activity
import android.bluetooth.BluetoothAdapter
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.provider.Settings
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.*

class ManejoPermisos(private val activity: ComponentActivity) {

    //rivate lateinit var requestPermissionLauncher: ActivityResultLauncher<Array<String>>

    private val permissions = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
        arrayOf(
            Manifest.permission.BLUETOOTH_SCAN,
            Manifest.permission.BLUETOOTH_CONNECT,
            Manifest.permission.ACCESS_FINE_LOCATION
        )
    } else {
        arrayOf(
            Manifest.permission.BLUETOOTH,
            Manifest.permission.BLUETOOTH_ADMIN,
            Manifest.permission.ACCESS_FINE_LOCATION
        )
    }
    private var perimistatus:Boolean = false

    init {
        registerLauncher()
    }

    private fun registerLauncher() {
       activity.registerForActivityResult(
            ActivityResultContracts.RequestMultiplePermissions()
        ) { permissions ->
            // Handle the result of the permission request
            val granted = permissions.entries.all { it.value }
            if (granted) {
                val requestCode = 1
                val discoverableIntent = Intent(BluetoothAdapter.ACTION_REQUEST_DISCOVERABLE).apply {
                    putExtra(BluetoothAdapter.EXTRA_DISCOVERABLE_DURATION, 30)
                }
                activity.startActivityForResult(discoverableIntent, requestCode)
                perimistatus = true

                println("Permisos de Bluetooth concedidos")

            } else {
                // Permissions denied, show a message to the user
                Toast.makeText(activity, "Debes aceptar los permisos para esta función", Toast.LENGTH_SHORT).show()
                perimistatus = false
            }
        }.launch(permissions)
    }

    fun requestPermissions():Boolean {
        return perimistatus
    }

    private fun openAppSettings(context: Context) {
        val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS).apply {
            data = Uri.fromParts("package", context.packageName, null)
        }
        context.startActivity(intent)
    }
}

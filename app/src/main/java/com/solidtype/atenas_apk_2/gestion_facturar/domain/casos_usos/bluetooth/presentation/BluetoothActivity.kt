package com.solidtype.atenas_apk_2.gestion_facturar.domain.casos_usos.bluetooth.presentation

import android.Manifest
import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothManager
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import com.solidtype.atenas_apk_2.gestion_facturar.domain.casos_usos.bluetooth.presentation.componente.DeviceScreen
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class BluetoothActivity : ComponentActivity() {
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

    private val requestPermissionLauncher = registerForActivityResult(
        ActivityResultContracts.RequestMultiplePermissions()
    ) { permissions ->
        // Handle the result of the permission request
        val granted = permissions.entries.all { it.value }
        if (granted) {
            isBluetoothEnabled
            val requestCode = 1;
            val discoverableIntent: Intent = Intent(BluetoothAdapter.ACTION_REQUEST_DISCOVERABLE).apply {
                putExtra(BluetoothAdapter.EXTRA_DISCOVERABLE_DURATION, 30)
            }
            startActivityForResult(discoverableIntent, requestCode)

            // Permissions granted, proceed with Bluetooth operations

//            val intent = Intent(
//                this,
//                MainActivity::class.java
//            )
//            startActivity(intent)
//            val bluetoothManager: BluetoothManager = getSystemService(BluetoothManager::class.java)
//            val bluetoothAdapter: BluetoothAdapter? = bluetoothManager.getAdapter()
//            if (bluetoothAdapter == null) {
//                // Device doesn't support Bluetooth
//                println("Esta nulo")
//            }
//            if (bluetoothAdapter?.isEnabled == false) {
//                println("No est abilitardo")
//
//            }else{
//                println("Esta abilitado!! ")
//            }

            println(" Permisos de bluetooth consedidos")
        } else {
            // Permissions denied, show a message to the user
            Toast.makeText(this, "Permisos de bluetooth denegados", Toast.LENGTH_SHORT).show()
            finishAndRemoveTask()


        }
    }
    private val bluetoothManager by lazy {
        applicationContext.getSystemService(BluetoothManager::class.java)
    }
    private val bluetoothAdapter by lazy {
        bluetoothManager?.adapter
    }

    private val isBluetoothEnabled: Boolean
        get() = bluetoothAdapter?.isEnabled == true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        requestPermissionLauncher.launch(permissions )
        setContent {

            Surface(
                color = MaterialTheme.colorScheme.background
            ) {
                DeviceScreen(  )
            }
        }

    }
}
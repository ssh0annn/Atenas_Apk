package com.solidtype.atenas_apk_2

import androidx.activity.ComponentActivity
import dagger.hilt.android.AndroidEntryPoint
import android.Manifest
import android.app.Activity
import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothManager
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.widget.Toast
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import androidx.core.content.ContextCompat.getSystemService
import androidx.core.content.ContextCompat.getSystemServiceName
import com.solidtype.atenas_apk_2.gestion_facturar.domain.BluetoothManagers
import kotlinx.coroutines.launch

@AndroidEntryPoint
class BluetoothScanner : ComponentActivity() {
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
            // Permissions granted, proceed with Bluetooth operations

//            val intent = Intent(
//                this,
//                MainActivity::class.java
//            )
//            startActivity(intent)
            val bluetoothManager: BluetoothManager = getSystemService(BluetoothManager::class.java)
            val bluetoothAdapter: BluetoothAdapter? = bluetoothManager.getAdapter()
            if (bluetoothAdapter == null) {
                // Device doesn't support Bluetooth
                println("Esta nulo")
            }
            if (bluetoothAdapter?.isEnabled == false) {
                println("No est abilitardo")

            }else{
                println("Esta abilitado!! ")
            }

            println(" Permisos de bluetooth consedidos")
        } else {
            // Permissions denied, show a message to the user
            Toast.makeText(this, "Permisos de bluetooth denegados", Toast.LENGTH_SHORT).show()
            finishAndRemoveTask()


        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestedOrientation = android.content.pm.ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE


        setContent {
            MyApp() {
                requestBluetoothPermissions()

            }
        }


    }

    private fun requestBluetoothPermissions() {
        requestPermissionLauncher.launch(permissions)

    }


}

@Composable
fun MyApp(requestPermissions: () -> Unit) {
    val context = LocalContext.current
    val coroutineScope = rememberCoroutineScope()




    Box(contentAlignment = Alignment.Center, modifier = Modifier
        .fillMaxSize()
        .padding()) {
        Button(onClick = {
            coroutineScope.launch {
                requestPermissions()
            }
        }) {
            Text(text = "Request Bluetooth Permissions")
        }

    }
    Spacer(modifier = Modifier.padding(5.dp))
    Box(contentAlignment = Alignment.Center, modifier = Modifier
        .fillMaxSize()){
        Button(onClick = {

        }) {
            Text(text = "ScaneaBluetooth")
        }

    }


}
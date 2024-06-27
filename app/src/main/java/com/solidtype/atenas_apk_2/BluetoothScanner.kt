package com.solidtype.atenas_apk_2

import androidx.activity.ComponentActivity
import dagger.hilt.android.AndroidEntryPoint
import android.Manifest
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.widget.Toast
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.core.content.ContextCompat
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

            val intent = Intent(
                this,
                MainActivity::class.java
            )
            startActivity(intent)

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

        requestBluetoothPermissions()

    }

    private fun requestBluetoothPermissions() {
        requestPermissionLauncher.launch(permissions)
    }


}

@Composable
fun MyApp(requestPermissions: () -> Unit) {
    val coroutineScope = rememberCoroutineScope()

    Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()) {
        Button(onClick = {
            coroutineScope.launch {
                requestPermissions()
            }
        }) {
            Text(text = "Request Bluetooth Permissions")
        }
    }
}
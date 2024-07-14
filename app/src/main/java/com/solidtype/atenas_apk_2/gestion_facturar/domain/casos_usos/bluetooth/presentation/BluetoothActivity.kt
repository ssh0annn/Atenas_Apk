package com.solidtype.atenas_apk_2.gestion_facturar.domain.casos_usos.bluetooth.presentation

import android.Manifest
import android.bluetooth.BluetoothAdapter
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.Settings
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import com.solidtype.atenas_apk_2.gestion_facturar.domain.casos_usos.bluetooth.presentation.componente.DeviceScreen
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class BluetoothActivity : ComponentActivity() {

private lateinit var bluetoothPermissionsHelper: ManejoPermisos
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bluetoothPermissionsHelper = ManejoPermisos(this)

        setContent {
            Surface(
                color = MaterialTheme.colorScheme.background
            ) {
                DeviceScreen(bluetoothPermissionsHelper=bluetoothPermissionsHelper)
            }
        }
    }

}

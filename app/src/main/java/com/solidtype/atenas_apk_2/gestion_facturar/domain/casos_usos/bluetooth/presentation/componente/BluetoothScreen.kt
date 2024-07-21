package com.solidtype.atenas_apk_2.gestion_facturar.domain.casos_usos.bluetooth.presentation.componente

import android.annotation.SuppressLint
import android.bluetooth.BluetoothClass
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.provider.Settings
import androidx.activity.ComponentActivity
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.solidtype.atenas_apk_2.gestion_facturar.domain.casos_usos.bluetooth.presentation.BluetoothViewModel
import com.solidtype.atenas_apk_2.gestion_facturar.domain.casos_usos.bluetooth.presentation.ManejoPermisos

@SuppressLint("MissingPermission")
@Composable
fun DeviceScreen(
    viewModel: BluetoothViewModel = hiltViewModel(), bluetoothPermissionsHelper:ManejoPermisos
) {


    val state by viewModel.state.collectAsStateWithLifecycle()

    if(!bluetoothPermissionsHelper.requestPermissions()){
        OpenAppSettingsWithDialog(true)
    }


    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        BluetoothDeviceList(
            pairedDevices = state.scannedDevices,
            scannedDevices = state.pairedDevices,
            onClick = { device ->
                val deviceName = device.name
                val deviceAddress = device.address
                val deviceClass = device.bluetoothClass

                println("Device Name: $deviceName")
                println("Device Address: $deviceAddress")
                println("Device Class: ${deviceClass.deviceClass}")

                // Agrega más casos según tus necesidades
                println("Device Type: Unknown ${deviceClass.deviceClass}")

                val majorDeviceClass = deviceClass.majorDeviceClass
                val minorDeviceClass = deviceClass.deviceClass

                println("Major Device Class: $majorDeviceClass")
                println("Minor Device Class: $minorDeviceClass")

                when (majorDeviceClass) {
                    BluetoothClass.Device.Major.COMPUTER -> println("Major Device Class: Computer")
                    BluetoothClass.Device.Major.PHONE -> println("Major Device Class: Phone")
                    BluetoothClass.Device.Major.AUDIO_VIDEO -> println("Major Device Class: Audio/Video")
                    BluetoothClass.Device.Major.IMAGING -> {
                        println("Impresora ${device.name}")
                    }

                    BluetoothClass.Device.Major.PERIPHERAL -> println("Major Device Class: Peripheral")
                    BluetoothClass.Device.Major.HEALTH -> println("Major Device Class: Health")
                    // Agrega más casos según tus necesidades
                    else -> println("Major Device Class: Unknown")
                }

                println("Aver $device")
            },
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
        )
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            Button(
                onClick = {
                    viewModel.startScan()
                    println("Scanning....")
                }
            ) {
                Text(text = "Start scan")

            }
            Button(onClick = {
                viewModel.stopScan()
                println("Stop Scanning")
            }) {
                Text(text = "Stop scan")
            }
        }
    }
}

@SuppressLint("MissingPermission")
@Composable
fun BluetoothDeviceList(
    pairedDevices: List<android.bluetooth.BluetoothDevice>,
    scannedDevices: List<android.bluetooth.BluetoothDevice>,
    onClick: (android.bluetooth.BluetoothDevice) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier
    ) {
        item {
            Text(
                text = "Paired Devices",
                fontWeight = FontWeight.Bold,
                fontSize = 24.sp,
                modifier = Modifier.padding(16.dp)
            )
        }
        items(pairedDevices) { device ->
            Text(
                text = device.name ?: "(No name)",
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { onClick(device) }
                    .padding(16.dp)
            )
        }

        item {
            Text(
                text = "Scanned Devices",
                fontWeight = FontWeight.Bold,
                fontSize = 24.sp,
                modifier = Modifier.padding(16.dp)
            )
        }
        items(scannedDevices) { device ->
            println("Devices $device")
            Text(
                text = device.name ?: "(No name)",
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { onClick(device) }
                    .padding(16.dp)
            )
        }
    }
}



@Composable
fun OpenAppSettingsWithDialog(permisos:Boolean) {
    var showDialog by remember { mutableStateOf(permisos) }
    val context = LocalContext.current

    if (showDialog) {
        AlertDialog(
            onDismissRequest = { showDialog = false },
            title = { Text("Permisos necesarios") },
            text = { Text("Para continuar, habilita los permisos necesarios en Configuración > Información de mi app > Permisos.") },
            confirmButton = {
                Button(onClick = {
                    showDialog = false
                    openAppSettings(context)
                }) {
                    Text("Abrir Configuración")
                }
            },
            dismissButton = {
                Button(onClick = { showDialog = false

                }) {
                    Text("Cancelar")
                }
            }
        )
    }

    // Llama a esta función en el punto apropiado, como al inicializar la pantalla o al hacer clic en un botón
}

fun openAppSettings(context: Context) {
    val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS).apply {
        data = Uri.fromParts("package", context.packageName, null)
    }
    context.startActivity(intent)
}
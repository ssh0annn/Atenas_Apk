package com.solidtype.ejemplos


import android.Manifest
import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothDevice
import android.bluetooth.BluetoothSocket
import android.content.pm.PackageManager
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.core.app.ActivityCompat
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.IOException
import java.io.OutputStream
import java.util.UUID

class ActivityEjemplo : ComponentActivity() {
    private lateinit var bluetoothDiscovery: BluetoothDiscovery
    private var selectedDevice: BluetoothDevice? = null
    private val bluetoothAdapter: BluetoothAdapter? = BluetoothAdapter.getDefaultAdapter()
    private var bluetoothSocket: BluetoothSocket? = null
    private var outputStream: OutputStream? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bluetoothDiscovery = BluetoothDiscovery(this)

        setContent {
            MyApp(
                startDiscovery = { bluetoothDiscovery.startDiscovery() },
                stopDiscovery = { bluetoothDiscovery.stopDiscovery() },
                getDevices = { bluetoothDiscovery.getDiscoveredDevices() },
                onDeviceSelected = { device ->
                    selectedDevice = device
                    connectToDevice(device)
                }
            )
        }
    }

    private fun connectToDevice(device: BluetoothDevice) {
        // Connect to the selected device
        lifecycleScope.launch {
            connectToPrinter(device)
        }
    }

    private suspend fun connectToPrinter(device: BluetoothDevice) {
        withContext(Dispatchers.IO) {
            try {
                if (ActivityCompat.checkSelfPermission(
                        this@ActivityEjemplo,
                        Manifest.permission.BLUETOOTH_CONNECT
                    ) != PackageManager.PERMISSION_GRANTED
                ) {
                    // TODO: Consider calling
                    //    ActivityCompat#requestPermissions
                    // here to request the missing permissions, and then overriding
                    //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                    //                                          int[] grantResults)
                    // to handle the case where the user grants the permission. See the documentation
                    // for ActivityCompat#requestPermissions for more details.
                    return@withContext
                }
                bluetoothSocket = device.createRfcommSocketToServiceRecord(MY_UUID)
                bluetoothSocket?.connect()
                outputStream = bluetoothSocket?.outputStream
            } catch (e: IOException) {
                e.printStackTrace()
                closeConnection()
            }
        }
    }

    private fun closeConnection() {
        try {
            outputStream?.close()
            bluetoothSocket?.close()
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }

    private fun printText(text: String) {
        try {
            outputStream?.write(text.toByteArray())
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }

    companion object {
        private val MY_UUID: UUID = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB")
    }
}

@Composable
fun MyApp(
    startDiscovery: () -> Unit,
    stopDiscovery: () -> Unit,
    getDevices: () -> List<BluetoothDevice>,
    onDeviceSelected: (BluetoothDevice) -> Unit
) {
    val coroutineScope = rememberCoroutineScope()
    val devices = remember { mutableStateListOf<BluetoothDevice>() }
    val connected = remember { mutableStateOf(false) }
    val context = LocalContext.current

    LaunchedEffect(Unit) {
        startDiscovery()
    }

    Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()) {
        if (!connected.value) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Button(onClick = {
                    coroutineScope.launch {
                        stopDiscovery()
                        devices.clear()
                        devices.addAll(getDevices())
                    }
                }) {
                    Text(text = "Stop Discovery and Show Devices")
                }

                Spacer(modifier = Modifier.height(16.dp))

                if (devices.isNotEmpty()) {
                    Text(text = "Select a device:")
                    devices.forEach { device ->
                        Button(onClick = {
                            onDeviceSelected(device)
                            connected.value = true
                        }) {
                            if (ActivityCompat.checkSelfPermission(
                                    context,
                                    Manifest.permission.BLUETOOTH_CONNECT
                                ) != PackageManager.PERMISSION_GRANTED
                            ) {
                                // TODO: Consider calling
                                //    ActivityCompat#requestPermissions
                                // here to request the missing permissions, and then overriding
                                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                                //                                          int[] grantResults)
                                // to handle the case where the user grants the permission. See the documentation
                                // for ActivityCompat#requestPermissions for more details.
                                return@Button
                            }
                            Text(text = device.name ?: device.address)
                        }
                    }
                }
            }
        } else {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(text = "Connected to device")
                Spacer(modifier = Modifier.height(16.dp))
                Button(onClick = {
                    coroutineScope.launch {
                        // Call your print function here
                    }
                }) {
                    Text(text = "Print")
                }
            }
        }
    }
}

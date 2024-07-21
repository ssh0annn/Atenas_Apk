package com.solidtype.atenas_apk_2.gestion_facturar.data

import android.Manifest
import android.app.Activity
import android.content.Context
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import android.content.Intent
import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothDevice
import android.bluetooth.BluetoothManager
import android.content.BroadcastReceiver
import android.content.IntentFilter
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.material3.Text
import androidx.compose.ui.input.pointer.PointerIcon.Companion.Text
import androidx.core.app.ActivityCompat
import com.solidtype.atenas_apk_2.gestion_facturar.domain.BluetoothManagers

class BluetoothDiscovery :ComponentActivity(){
    private val bluetoothAdapter: BluetoothAdapter? = BluetoothAdapter.getDefaultAdapter()
    private val discoveredDevices = mutableListOf<BluetoothDevice>()


    private val receiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context, intent: Intent) {
            val action: String? = intent.action
            if (BluetoothDevice.ACTION_FOUND == action) {
                val device: BluetoothDevice? = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE)
                device?.let { discoveredDevices.add(it) }
            }
        }
    }

    fun permisos() {
        val permissions = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
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

        val requestPermissionLauncher = registerForActivityResult(
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
                val bluetoothManager: BluetoothManager =
                    getSystemService(BluetoothManager::class.java)
                val bluetoothAdapter: BluetoothAdapter? = bluetoothManager.getAdapter()
                if (bluetoothAdapter == null) {
                    // Device doesn't support Bluetooth
                    println("Esta nulo")
                }
                if (bluetoothAdapter?.isEnabled == false) {
                    println("No est abilitardo")

                } else {
                    println("Esta abilitado!! ")
                }

                println(" Permisos de bluetooth consedidos")
            } else {
                // Permissions denied, show a message to the user
                Toast.makeText(this, "Permisos de bluetooth denegados", Toast.LENGTH_SHORT).show()
                finishAndRemoveTask()
            }

        }
    }

    fun stopDiscovery() {
        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.BLUETOOTH_SCAN
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return
        }
        bluetoothAdapter?.cancelDiscovery()
        this.unregisterReceiver(receiver)
    }

    fun getDiscoveredDevices(): List<BluetoothDevice> {
        return discoveredDevices
    }
}

class CasoBlueTooth @Inject constructor(@ApplicationContext private val context: Context) :
    BluetoothManagers{
    private val bluetoothAdapter: BluetoothAdapter? = BluetoothAdapter.getDefaultAdapter()
    private val discoveredDevices = mutableListOf<BluetoothDevice>()

    private val receiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context, intent: Intent) {
            val action: String? = intent.action
            if (BluetoothDevice.ACTION_FOUND == action) {
                val device: BluetoothDevice? = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE)
                device?.let { discoveredDevices.add(it) }
            }
        }
    }
    override fun scannDevices() {
        val filter = IntentFilter(BluetoothDevice.ACTION_FOUND)
        context.registerReceiver(receiver, filter)
        if (ActivityCompat.checkSelfPermission(
                context,
                Manifest.permission.BLUETOOTH_SCAN
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return
        }
        bluetoothAdapter?.startDiscovery()
    }

    override fun isPrinterConnected(): Boolean {
        TODO("Not yet implemented")
    }


    override fun permisosGranted(): Boolean {

        return true
    }
}
package com.solidtype.atenas_apk_2

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import com.solidtype.atenas_apk_2.products.presentation.inventory.InventarioViewModel
import com.solidtype.atenas_apk_2.products.presentation.inventory.InventoryScreen
import com.solidtype.atenas_apk_2.util.FireManeger
import com.solidtype.atenas_apk_2.util.YourViewModel

import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : ComponentActivity() {
 private val viewmodel by viewModels<InventarioViewModel> ()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            InventoryScreen()
           // FireManeger()
        }
    }

    @Deprecated("Deprecated in Java")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 2 && resultCode == Activity.RESULT_OK) {
            data?.data?.let { uri ->
                val filePath = getFilePathFromUri(uri)
                val content =contentResolver.openInputStream(uri)?.use{
                    it.readBytes()
                }
                println("Tamanio del archivo: ${content?.size}")

                if (filePath != "file") {
                    println("Ruta del archivo: $filePath")
                    viewmodel.importarExcel(uri)
                } else {
                    println("No se pudo obtener la ruta del archivo")
                }
            }
        }
    }

    private fun getFilePathFromUri(uri: Uri): String? {
        return if (uri.scheme != "file") {
            uri.path
        } else {

            null
        }
    }
}


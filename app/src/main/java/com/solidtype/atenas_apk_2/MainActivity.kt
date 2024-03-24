package com.solidtype.atenas_apk_2

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.hilt.navigation.compose.hiltViewModel
import com.solidtype.atenas_apk_2.Authentication.presentation.pantallas.Navigation
import com.solidtype.atenas_apk_2.products.presentation.inventory.componets.InventoryScreen
import com.solidtype.atenas_apk_2.util.FireManeger
import com.solidtype.atenas_apk_2.util.YourViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            InventoryScreen()
        }
    }

    @Deprecated("Deprecated in Java")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        val viewModel= YourViewModel ()
        if (requestCode == 1 && resultCode == Activity.RESULT_OK) {
            data?.data?.let { uri ->
                val filePath = getFilePathFromUri(uri)
                if (filePath != null) {
                    println("Ruta del archivo: $filePath")
                    viewModel.fileSelected(filePath)
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


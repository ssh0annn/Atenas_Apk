package com.solidtype.atenas_apk_2

import android.app.Activity
import android.app.UiModeManager
import android.content.Context
import android.content.Intent
import android.content.res.Configuration
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.core.app.ActivityCompat
import com.solidtype.atenas_apk_2.core.pantallas.Navigation
import com.solidtype.atenas_apk_2.products.presentation.inventory.InventarioViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlin.math.sqrt

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val viewmodel by viewModels<InventarioViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestedOrientation = android.content.pm.ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
        if (!isTablet()) {
            Toast.makeText(this, "Pantalla muy pequeña para esta aplicación", Toast.LENGTH_LONG).show()
            finishAndRemoveTask()
        }
        setContent {
            Navigation()
        }

    }

    @Deprecated("Deprecated in Java")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 2 && resultCode == Activity.RESULT_OK) {
            data?.data?.let { uri ->
                val filePath = getFilePathFromUri(uri)
                val content = contentResolver.openInputStream(uri)?.use {
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

    fun isTablet(): Boolean {
        println("""
            isTabletByConfiguration: ${isTabletByConfiguration()}
            isTabletByUiMode: ${isTabletByUiMode(this)}
            isTabletBySize: ${isTabletBySize(this)}
            
        """.trimIndent())
        return  isTabletByConfiguration() || isTabletByUiMode(this)
    }
    fun isTabletByConfiguration(): Boolean {
        val configuration = resources.configuration
        val screenLayout = configuration.screenLayout and Configuration.SCREENLAYOUT_SIZE_MASK
        println("""
            Configuration: $configuration
            screenLayout: $screenLayout
        """.trimIndent())
        return screenLayout == Configuration.SCREENLAYOUT_SIZE_LARGE ||
                screenLayout == Configuration.SCREENLAYOUT_SIZE_XLARGE
    }
    private fun isTabletByUiMode(context: Context): Boolean {
        val uiModeManager = context.getSystemService(Context.UI_MODE_SERVICE) as UiModeManager
        val currentModeType = uiModeManager.currentModeType
        return currentModeType == Configuration.UI_MODE_TYPE_TELEVISION ||
                (currentModeType == Configuration.UI_MODE_TYPE_NORMAL && isTabletBySize(context))
    }
    fun isTabletBySize(context: Context): Boolean {
        val displayMetrics = context.resources.displayMetrics
        val widthPixels = displayMetrics.widthPixels
        val heightPixels = displayMetrics.heightPixels
        val xdpi = displayMetrics.xdpi
        val ydpi = displayMetrics.ydpi
        val widthInches = widthPixels / xdpi
        val heightInches = heightPixels / ydpi
        val diagonalInches = sqrt(widthInches * widthInches + heightInches * heightInches)
        println("""
            heightInch: $heightInches
            diagonalInc: $diagonalInches
        """.trimIndent())
        return diagonalInches >= 7.0
    }
}
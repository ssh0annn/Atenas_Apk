package com.solidtype.atenas_apk_2.util

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext

@Composable
fun FireManeger() {
    //viewModel: InventarioViewModel = hiltViewModel()
    val context = LocalContext.current

    Button(onClick = {
        // Llama a una función en el ViewModel para manejar la selección del archivo
        showFilePicker(context)
    }) {
        Text("Seleccionar archivo")
    }
}

fun showFilePicker(context: Context) {
    val intent = Intent(Intent.ACTION_OPEN_DOCUMENT).apply {
        addCategory(Intent.CATEGORY_OPENABLE)
        type = "*/*"
        putExtra(Intent.EXTRA_MIME_TYPES, arrayOf("application/vnd.ms-excel", "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"))
    }
    (context as? Activity)?.startActivityForResult(intent, 2)
}

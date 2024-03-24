package com.solidtype.atenas_apk_2.util

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun FireManeger(viewModel: YourViewModel= hiltViewModel()) {
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

    (context as? Activity)?.startActivityForResult(intent, 1)


}

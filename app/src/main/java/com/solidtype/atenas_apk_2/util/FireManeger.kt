package com.solidtype.atenas_apk_2.util

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.constraintlayout.compose.ConstraintLayout
import com.google.firebase.auth.actionCodeSettings

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

@Composable
fun SnackbarDemo(snackbarhosting:SnackbarHostState){

    ConstraintLayout(modifier = Modifier.fillMaxSize()) {
        val snackbar = createRef()
        SnackbarHost(hostState=snackbarhosting,modifier= Modifier.constrainAs(snackbar){
            bottom.linkTo(parent.bottom)
            start.linkTo(parent.start)
            end.linkTo(parent.end)
        }, snackbar ={ Snackbar {
            actionCodeSettings { 
                TextButton(onClick = { snackbarhosting.currentSnackbarData?.dismiss() }) {
                    Text(text = "hide", style = TextStyle(color = Color.White) )
                    
                }
            }

        }}

            )
    }


}
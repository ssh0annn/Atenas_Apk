package com.solidtype.atenas_apk_2

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.google.android.gms.tasks.OnSuccessListener
import com.google.firebase.Firebase
import com.google.firebase.messaging.FirebaseMessaging
import com.solidtype.atenas_apk_2.products.presentation.inventory.InventarioViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val viewmodel by viewModels<InventarioViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent{
            ImeiBoton {


            }
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

    fun prueba(){
        FirebaseMessaging.getInstance().token.addOnCompleteListener { task ->
            if (!task.isSuccessful) {
               println("Fetching FCM registration token failed" + task.exception)
                return@addOnCompleteListener
            }

            // Get new FCM registration token
            val token = task.result

            // Log and toast
            println("InstanceID" + token)
        }


}

@Composable
fun ImeiBoton(imei: () -> Unit){

    Button(onClick = {imei()}) {
        Text(text = "Imprime IMEI")
    }

}

package com.solidtype.atenas_apk_2.gestion_facturar

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.solidtype.atenas_apk_2.R
import net.glxn.qrgen.android.QRCode


@Composable
fun App() {
    val context = LocalContext.current

    var text by remember { mutableStateOf("ID_Product: iPhone X, Precio: RD\$7,000") }
    val printer = Printer(context)
    printer.startService()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFDADADA)),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        //Spacer(modifier = Modifier.height(300.dp))
        TextField(
            value = text,
            onValueChange = { text = it },
            label = { Text("Mensaje a imprimir") }
        )
        Spacer(modifier = Modifier.height(30.dp))
        Button(onClick = {
            printer.scanBluetoothPrinter()
        }) {
            Text("Escanear Impresora")
        }
        Button(
            onClick = {
                val printed = printer.printText(
                    text = text,
                    size = printer.GRANDE,
                    align = printer.CENTRADA,
                    saltoLinea = 4
                )
                if (printed) {
                    Toast.makeText(context, "Texto impreso", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(context, "No se pudo imprimir", Toast.LENGTH_SHORT).show()
                }
            }
        ) {
            Text("Imprimir Texto")
        }
        Button(
            onClick = {
                val qr: Bitmap = QRCode.from(text).withSize(200, 200).bitmap()
                val printed = printer.printImage(
                    qr,
                    saltoLinea = 4,
                    align = printer.CENTRADA
                )
                if (printed) {
                    Toast.makeText(context, "QR impreso", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(context, "No se pudo imprimir", Toast.LENGTH_SHORT).show()
                }
            }
        ) {
            Text("Imprimir QR")
        }
        //Imagen del logo
        val logo = R.drawable.logo

        //Mostrar la imagen
        Image(
            painter = painterResource(id = logo),
            contentDescription = "Logo",
            modifier = Modifier
                .size(200.dp)
                .padding(16.dp)
        )
        //Botón para imprimir la imagen
        Button(
            onClick = {
                //convertir logo a bitmap 200x200
                val bm = BitmapFactory.decodeResource(context.resources, R.drawable.logo50png)
                val printed = printer.printImage(
                    bm,
                    saltoLinea = 6,
                    align = printer.CENTRADA
                )
                if (printed) {
                    Toast.makeText(context, "Logo impreso", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(context, "No se pudo imprimir", Toast.LENGTH_SHORT).show()
                }
            }
        ) {
            Text("Imprimir Logo")
        }
    }
}
package com.solidtype.atenas_apk_2.facturacion.presentation.facturacion

import android.annotation.SuppressLint
import android.util.Log
import android.widget.Toast
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.FactCheck
import androidx.compose.material3.DatePickerState
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.solidtype.atenas_apk_2.facturacion.presentation.componets.InputBlanco
import com.solidtype.atenas_apk_2.util.formatearFecha
import com.solidtype.atenas_apk_2.util.formatoParaUser
import com.solidtype.atenas_apk_2.util.ui.Components.Boton
import com.solidtype.atenas_apk_2.util.ui.Components.Carrito
import com.solidtype.atenas_apk_2.util.ui.Components.DatePickerDialogo
import com.solidtype.atenas_apk_2.util.ui.Components.SelecionarFecha

@SuppressLint("MutableCollectionMutableState")
@OptIn(ExperimentalMultiplatform::class, ExperimentalMaterial3Api::class)
@Composable
fun FacturacionScreen() {

    val context = LocalContext.current

    var noFactura by rememberSaveable { mutableStateOf("") }
    var cliente by rememberSaveable { mutableStateOf("") }

    val datePickerState: DatePickerState = rememberDatePickerState()
    var showDatePicker by rememberSaveable { mutableStateOf(false) }
    var fecha by rememberSaveable { mutableStateOf("") }

    val facturas = listOf(
        Factura(
            1, "2021-10-10", 100.0, "Efectivo", 1,
            listOf(
                Producto(1, 1, "iPhone X", 2, 50.0, 100.0, 0.0),
                Producto(2, 1, "iPhone 11", 2, 50.0, 100.0, 0.0)
            )
        ),
        Factura(
            2, "2021-10-10", 100.0, "Efectivo", 1,
            listOf(
                Producto(3, 2, "iPhone 12", 2, 50.0, 100.0, 0.0),
                Producto(4, 2, "iPhone 13", 2, 50.0, 100.0, 0.0)
            )
        ),
        Factura(
            3, "2021-10-10", 100.0, "Efectivo", 1,
            listOf(
                Producto(5, 3, "iPhone 14", 2, 50.0, 100.0, 0.0),
                Producto(6, 3, "iPhone 15", 3, 50.0, 150.0, 0.0),
                Producto(7, 3, "iPhone 16", 2, 35.0, 70.0, 0.0)
            )
        ),
        Factura(
            4, "2021-10-10", 100.0, "Efectivo", 1,
            listOf(
                Producto(8, 4, "iPhone 17", 2, 50.0, 100.0, 0.0),
                Producto(9, 4, "iPhone 18", 2, 50.0, 100.0, 0.0)
            )
        ),
        Factura(
            5, "2021-10-10", 100.0, "Efectivo", 1,
            listOf(
                Producto(10, 5, "iPhone 19", 2, 50.0, 100.0, 0.0),
                Producto(11, 5, "iPhone 20", 2, 50.0, 100.0, 0.0)
            )
        ),
        Factura(
            6, "2021-10-10", 100.0, "Efectivo", 1,
            listOf(
                Producto(12, 6, "iPhone 21", 2, 50.0, 100.0, 0.0),
                Producto(13, 6, "iPhone 22", 2, 50.0, 100.0, 0.0)
            )
        ),
        Factura(
            7, "2021-10-10", 100.0, "Efectivo", 1,
            listOf(
                Producto(14, 7, "iPhone 23", 2, 50.0, 100.0, 0.0),
                Producto(15, 7, "iPhone 24", 2, 50.0, 100.0, 0.0)
            )
        ),
        Factura(
            8, "2021-10-10", 100.0, "Efectivo", 1,
            listOf(
                Producto(16, 8, "iPhone 25", 2, 50.0, 100.0, 0.0),
                Producto(17, 8, "iPhone 26", 2, 50.0, 100.0, 0.0)
            )
        ),
        Factura(
            9, "2021-10-10", 100.0, "Efectivo", 1,
            listOf(
                Producto(18, 9, "iPhone 27", 2, 50.0, 100.0, 0.0),
                Producto(19, 9, "iPhone 28", 2, 50.0, 100.0, 0.0)
            )
        ),
        Factura(
            10, "2021-10-10", 100.0, "Efectivo", 1,
            listOf(
                Producto(20, 10, "iPhone 29", 2, 50.0, 100.0, 0.0),
                Producto(21, 10, "iPhone 30", 2, 50.0, 100.0, 0.0)
            )
        ),
        Factura(
            11, "2021-10-10", 100.0, "Efectivo", 1,
            listOf(
                Producto(22, 11, "iPhone 31", 2, 50.0, 100.0, 0.0),
                Producto(23, 11, "iPhone 32", 2, 50.0, 100.0, 0.0)
            )
        ),
        Factura(
            12, "2021-10-10", 100.0, "Efectivo", 1,
            listOf(
                Producto(24, 12, "iPhone 33", 2, 50.0, 100.0, 0.0),
                Producto(25, 12, "iPhone 34", 2, 50.0, 100.0, 0.0)
            )
        ),
        Factura(
            13, "2021-10-10", 100.0, "Efectivo", 1,
            listOf(
                Producto(26, 13, "iPhone 35", 2, 50.0, 100.0, 0.0),
                Producto(27, 13, "iPhone 36", 2, 50.0, 100.0, 0.0)
            )
        ),
        Factura(
            14, "2021-10-10", 100.0, "Efectivo", 1,
            listOf(
                Producto(28, 14, "iPhone 37", 2, 50.0, 100.0, 0.0),
                Producto(29, 14, "iPhone 38", 2, 50.0, 100.0, 0.0)
            )
        ),
        Factura(
            15, "2021-10-10", 100.0, "Efectivo", 1,
            listOf(
                Producto(30, 15, "iPhone 39", 2, 50.0, 100.0, 0.0),
                Producto(31, 15, "iPhone 40", 2, 50.0, 100.0, 0.0)
            )
        ),
    ) //viene del VM

    var desplegar by rememberSaveable { mutableStateOf(List(facturas.size) { false }) }

    Column(
        //To.do
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .background(
                Color(0xFFD7D7D9)
            ),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 100.dp, vertical = 20.dp)
        ) {//Contenedor
            Row {//Título
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.FactCheck,
                    contentDescription = "",
                    tint = Color(0xFF343341),
                    modifier = Modifier
                        .padding(top = 10.dp)
                        .size(40.dp)
                )
                Text(
                    text = "Facturas",
                    color = Color(0xFF343341),
                    fontSize = 30.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(10.dp)
                )
            }
            Column {//Entradas, Datos y Tap
                Spacer(modifier = Modifier.height(25.dp))
                Row {//Entradas
                    InputBlanco(
                        label = "No. Factura",
                        valor = noFactura,
                        derecho = true,
                        modifier = Modifier
                            .weight(10f)
                    ) {
                        noFactura = it
                    }
                    InputBlanco(
                        label = "Cliente",
                        valor = cliente,
                        derecho = true,
                        modifier = Modifier
                            .weight(5f)
                    ) {
                        cliente = it
                    }
                    SelecionarFecha(
                        "Fecha",
                        datePickerState.selectedDateMillis,
                        modifierPadre = Modifier
                            .padding(start = 20.dp)
                            .weight(5f),
                        modifierHijo = Modifier
                            .padding(top = 5.dp)
                            .width(200.dp)
                            .height(55.dp),
                        size = 16,
                    ) {
                        showDatePicker = true
                    }
                }
                Spacer(modifier = Modifier.height(10.dp))
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(300.dp)
                        .background(Color(0xFF343341), shape = RoundedCornerShape(20.dp))
                ) {
                    Column(
                        modifier = Modifier
                            .padding(10.dp)
                            .fillMaxSize()
                            .background(Color(0xFF737A8C), shape = RoundedCornerShape(20.dp))
                    ) {
                        Row(
                            modifier = Modifier
                                .padding(10.dp)
                                .fillMaxWidth()

                        ) {
                            Text(
                                text = "No. Factura",
                                modifier = Modifier.weight(1f),
                                color = Color(0xFFFFFFFF),
                                textAlign = TextAlign.Center
                            )
                            Text(
                                text = "Fecha",
                                modifier = Modifier.weight(1f),
                                color = Color(0xFFFFFFFF),
                                textAlign = TextAlign.Center
                            )
                            Text(
                                text = "Total",
                                modifier = Modifier.weight(1f),
                                color = Color(0xFFFFFFFF),
                                textAlign = TextAlign.Center
                            )
                            Text(
                                text = "Tipo de Pago",
                                modifier = Modifier.weight(1f),
                                color = Color(0xFFFFFFFF),
                                textAlign = TextAlign.Center
                            )
                            Text(
                                text = "ID",
                                modifier = Modifier.weight(1f),
                                color = Color(0xFFFFFFFF),
                                textAlign = TextAlign.Center
                            )
                        }
                        LazyColumn(
                            modifier = Modifier
                                .padding(start = 10.dp, end = 10.dp, bottom = 10.dp)
                                .fillMaxSize()
                                .background(Color(0xFF737A8C))
                        ) { //buscar componente para agregar filas de cards
                            itemsIndexed(facturas) { i, factura ->
                                Column {
                                    Row(
                                        modifier = Modifier
                                            .padding(bottom = when (desplegar[i]) {
                                                    true -> 0.dp
                                                    false -> 5.dp
                                                }
                                            )
                                            .fillMaxWidth()
                                            .height(40.dp)
                                            .clip(RoundedCornerShape(50.dp))
                                            .background(Color(0xFFFFFFFF))
                                            .clickable {
                                                desplegar = desplegar
                                                    .toMutableList()
                                                    .also {
                                                        it[i] = !it[i]
                                                    }
                                            },
                                        verticalAlignment = Alignment.CenterVertically
                                    ) {
                                        Text(
                                            text = factura.noFactura.toString(),
                                            modifier = Modifier.weight(1f),
                                            textAlign = TextAlign.Center
                                        )
                                        Text(
                                            text = factura.fecha.formatoParaUser(),
                                            modifier = Modifier.weight(1f),
                                            textAlign = TextAlign.Center
                                        )
                                        Text(
                                            text = factura.total.toString(),
                                            modifier = Modifier.weight(1f),
                                            textAlign = TextAlign.Center
                                        )
                                        Text(
                                            text = factura.tipoPago,
                                            modifier = Modifier.weight(1f),
                                            textAlign = TextAlign.Center
                                        )
                                        Text(
                                            text = factura.idVendedor.toString(),
                                            modifier = Modifier.weight(1f),
                                            textAlign = TextAlign.Center
                                        )
                                    }
                                    AnimatedVisibility(desplegar[i]) {
                                        Box(
                                            modifier = Modifier
                                                .fillMaxWidth()
                                                .padding(start = 20.dp, end = 20.dp, bottom = 10.dp)
                                                .background(Color(0xFF8285A5))
                                        ) {
                                            Column {
                                                Row {//Cabecera
                                                    Text(
                                                        text = "Cantidad",
                                                        modifier = Modifier.weight(1f),
                                                        textAlign = TextAlign.Center,
                                                        fontWeight = FontWeight.Bold
                                                    )
                                                    Text(
                                                        text = "Nombre",
                                                        modifier = Modifier.weight(1f),
                                                        textAlign = TextAlign.Center,
                                                        fontWeight = FontWeight.Bold
                                                    )
                                                    Text(
                                                        text = "Precio",
                                                        modifier = Modifier.weight(1f),
                                                        textAlign = TextAlign.Center,
                                                        fontWeight = FontWeight.Bold
                                                    )
                                                    Text(
                                                        text = "ITB",
                                                        modifier = Modifier.weight(1f),
                                                        textAlign = TextAlign.Center,
                                                        fontWeight = FontWeight.Bold
                                                    )
                                                    Text(
                                                        text = "Total",
                                                        modifier = Modifier.weight(1f),
                                                        textAlign = TextAlign.Center,
                                                        fontWeight = FontWeight.Bold
                                                    )
                                                }
                                                Divider()
                                                factura.productos.forEach {Producto -> //Cuerpo
                                                    Row(
                                                        modifier = Modifier
                                                            .fillMaxWidth()
                                                    ) {
                                                        Text(
                                                            text = Producto.cantidad.toString(),
                                                            modifier = Modifier.weight(1f),
                                                            textAlign = TextAlign.Center
                                                        )
                                                        Text(
                                                            text = Producto.nombre,
                                                            modifier = Modifier.weight(1f),
                                                            textAlign = TextAlign.Center
                                                        )
                                                        Text(
                                                            text = Producto.precio.toString(),
                                                            modifier = Modifier.weight(1f),
                                                            textAlign = TextAlign.Center
                                                        )
                                                        Text(
                                                            text = Producto.ITB.toString(),
                                                            modifier = Modifier.weight(1f),
                                                            textAlign = TextAlign.Center
                                                        )
                                                        Text(
                                                            text = Producto.total.toString(),
                                                            modifier = Modifier.weight(1f),
                                                            textAlign = TextAlign.Center
                                                        )
                                                    }
                                                }
                                                Divider()
                                                Row{ // Pie
                                                    Text(
                                                        text = "Total",
                                                        modifier = Modifier.weight(1f),
                                                        textAlign = TextAlign.Center,
                                                        fontWeight = FontWeight.Bold
                                                    )
                                                    for (@Suppress("NAME_SHADOWING") i in 0..2) {
                                                        Text(
                                                            text = "",
                                                            modifier = Modifier.weight(1f)
                                                        )
                                                    }
                                                    Text(
                                                        text = factura.productos.sumaTotal().toString(),
                                                        modifier = Modifier.weight(1f),
                                                        textAlign = TextAlign.Center,
                                                        fontWeight = FontWeight.Bold
                                                    )
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
                Box(
                    modifier = Modifier
                        .fillMaxWidth(),
                    contentAlignment = Alignment.CenterEnd
                ) { //botón Filtrar
                    Boton("Filtrar") {
                        if(fecha.isEmpty() || noFactura.isEmpty() || cliente.isEmpty()){
                            Toast.makeText(context, "Debe llenar todos los campos", Toast.LENGTH_SHORT).show()
                        }else{
                            /*viewModel.buscarFacturas(
                                formatoDDBB(fecha),
                                selectedCategoria
                            )*/
                        }
                    }
                }
                Box {//Tab Navigator
                    //TabNavigator()
                }
            }
        }
        DatePickerDialogo(
            showDatePicker = showDatePicker,
            datePickerState = datePickerState,
            onDismissRequest = {
                showDatePicker = false
            },
            onClick = {
                showDatePicker = false
                fecha = datePickerState.selectedDateMillis.formatearFecha()
            }
        )
    }
}

//Preview para Vortex T10M (T10MPROPLUS) Horizontal
@Preview(backgroundColor = 0xFFFFFFFF, showBackground = true, widthDp = 1080, heightDp = 560)
@Composable
fun FacturacionScreenPreview() {
    FacturacionScreen()
}

data class Factura(
    val noFactura: Int,
    val fecha: String,
    val total: Double,
    val tipoPago: String,
    val idVendedor: Int,

    val productos: List<Producto>
)

data class Producto(
    val idProducto: Int,
    val idFactura: Int,
    val nombre: String,
    val cantidad: Int,
    val precio: Double,
    val total: Double,
    val ITB: Double
)

fun List<Producto>.sumaTotal(): Double {
    var total = 0.0
    this.forEach { total += it.total }
    return total
}
package com.solidtype.atenas_apk_2.facturacion.presentation.facturacion

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.DatePickerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.solidtype.atenas_apk_2.facturacion.presentation.componets.Generals.Tabla
import com.solidtype.atenas_apk_2.facturacion.presentation.componets.Generals.Titulo
import com.solidtype.atenas_apk_2.facturacion.presentation.componets.InputBlanco
import com.solidtype.atenas_apk_2.facturacion.presentation.facturas
import com.solidtype.atenas_apk_2.util.formatearFecha
import com.solidtype.atenas_apk_2.util.ui.Components.Boton
import com.solidtype.atenas_apk_2.util.ui.Components.DatePickerDialogo
import com.solidtype.atenas_apk_2.util.ui.Components.NavPlato
import com.solidtype.atenas_apk_2.util.ui.Components.SelecionarFecha
import com.solidtype.atenas_apk_2.ui.theme.GrisClaro

@SuppressLint("MutableCollectionMutableState")
@OptIn(ExperimentalMultiplatform::class, ExperimentalMaterial3Api::class)
@Composable
fun FacturacionScreen() {

    /*val configuration = LocalConfiguration.current

       val screenHeight = configuration.screenHeightDp.dp
       val screenWidth = configuration.screenWidthDp.dp*/

    val context = LocalContext.current

    val datePickerState: DatePickerState = rememberDatePickerState()
    var showDatePicker by rememberSaveable { mutableStateOf(true) }

    var fecha by rememberSaveable { mutableStateOf("") }
    var noFactura by rememberSaveable { mutableStateOf("") }
    var cliente by rememberSaveable { mutableStateOf("") }

    Column(
        //To.do
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .background(
                color = GrisClaro
            ),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 100.dp, vertical = 20.dp)
        ) {//Contenedor
            Titulo()
            Column {//Entradas, Datos y Botón
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
                Tabla(facturas = facturas)
                Box(
                    modifier = Modifier
                        .fillMaxWidth(),
                    contentAlignment = Alignment.CenterEnd
                ) { //Botón "Filtrar"
                    Boton("Filtrar") {
                        if (fecha.isEmpty() || noFactura.isEmpty() || cliente.isEmpty()) {
                            Toast.makeText(
                                context,
                                "Debe llenar todos los campos",
                                Toast.LENGTH_SHORT
                            ).show()
                        } else {
                            /*viewModel.buscarFacturas(
                                noFactura,
                                formatoDDBB(fecha),
                                selectedCategoria
                            )*/
                        }
                    }
                }
            }
        }
    }
    NavPlato("Facturas")
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
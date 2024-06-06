package com.solidtype.atenas_apk_2.gestion_proveedores.presentation.cliente

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.FactCheck
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.solidtype.atenas_apk_2.gestion_proveedores.presentation.cliente.componets.TableClients
import com.solidtype.atenas_apk_2.gestion_proveedores.presentation.cliente.componets.listClients
import com.solidtype.atenas_apk_2.ui.theme.AzulGris

import com.solidtype.atenas_apk_2.ui.theme.GrisClaro
import com.solidtype.atenas_apk_2.util.ui.Components.Boton
import com.solidtype.atenas_apk_2.util.ui.Components.Buscador
import com.solidtype.atenas_apk_2.util.ui.Components.Dialogo
import com.solidtype.atenas_apk_2.util.ui.Components.InputDetalle
import com.solidtype.atenas_apk_2.util.ui.Components.Titulo


@OptIn(ExperimentalMaterial3Api::class, ExperimentalMultiplatform::class)
@SuppressLint("StateFlowValueCalledInComposition")
@Composable
fun ClienteScreen(
    
){
    val context = LocalContext.current
    val mostrarDialogo = rememberSaveable { mutableStateOf(false) }
    val editar = rememberSaveable { mutableStateOf(false) }

    //formulario cliente
    val nombre = rememberSaveable { mutableStateOf("") }
    val Tipodocumento = rememberSaveable { mutableStateOf("") }
    val Numdocumento = rememberSaveable { mutableStateOf("") }
    val Email = rememberSaveable { mutableStateOf("") }
    val Telefono = rememberSaveable { mutableStateOf("") }

    //LISTA SELECTOR
    val DocumetSelector = listOf("Cédula", "Pasaporte")
    //estado busqueda
    val Busqueda = rememberSaveable { mutableStateOf("") }

    val mostrarConfirmar = rememberSaveable { mutableStateOf(false) }



    Column(
        //All
        modifier = Modifier
            .fillMaxSize()
            .background(GrisClaro),
        horizontalAlignment = Alignment.CenterHorizontally,
    ){
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 50.dp, vertical = 25.dp)
        ) {//Contenedor
            Column {
                Titulo("Clientes", Icons.AutoMirrored.Outlined.FactCheck)

                Box (
                    modifier = Modifier.fillMaxWidth(),
                    contentAlignment = Alignment.BottomEnd
                ){
                    Buscador(busqueda = Busqueda.value) {
                        Busqueda.value = it
                    }
                }

                Spacer(modifier = Modifier.height(10.dp))

                TableClients(listClients, mostrarDialogo, editar,nombre,Tipodocumento,Numdocumento,Email,Telefono,mostrarConfirmar)

                Box(
                    modifier = Modifier.fillMaxWidth(),
                    contentAlignment = Alignment.BottomEnd
                ) {
                    Boton("Agregar"){
                        mostrarDialogo.value = true
                        editar.value = false

                        nombre.value = ""
                        Numdocumento.value = ""
                        Telefono.value = ""
                        Email.value = ""
                        Tipodocumento.value = ""

                    }
                }

                Spacer(modifier = Modifier.height(40.dp))
            }
        }
    }
    Dialogo(max = false,titulo = if (editar.value)  "Editar Cliente" else  "Nuevo Cliente", mostrar = mostrarDialogo.value, onCerrarDialogo = { mostrarDialogo.value = false }) {
        Column(
            modifier = Modifier
                .background(GrisClaro, RoundedCornerShape(20.dp))
                .width(400.dp)
                .height(300.dp)
                .padding(10.dp)
            ,
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            //cuerpo1
            Column(
                modifier = Modifier
                    .verticalScroll(rememberScrollState()),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {


                    InputDetalle(

                        label = "Nombre",
                        valor = nombre.value,
                    ) {
                        nombre.value = it
                    }

               /* Spacer(modifier = Modifier.height(10.dp))
                AutocompleteSelect(text = "Tipo de Documento", variableStr = Tipodocumento.value, items = DocumetSelector) {
                    Tipodocumento.value = it
                }*/
                Spacer(modifier = Modifier.height(10.dp))
                InputDetalle(
                    label = "Numero de documento",
                    valor = Numdocumento.value,
                ) {
                    Numdocumento.value = it
                }




                Spacer(modifier = Modifier.height(10.dp))
                InputDetalle(
                        label = "Email",
                        valor = Email.value,
                    ) {
                        Email.value = it
                    }

                Spacer(modifier = Modifier.height(10.dp))

                InputDetalle(
                        label = "Telefono",
                        valor = Telefono.value,
                    ) {
                        Telefono.value = it
                    }

                Spacer(modifier = Modifier.height(10.dp))

                if (editar.value)
                    Boton("Editar") {

                    }
                else
                Boton("Agregar") {

                }

            }
        }


    }

    Dialogo(
        titulo = "Confirma",
        mostrar = mostrarConfirmar.value,
        onCerrarDialogo = { mostrarConfirmar.value = false },
        max = false,
        sinBoton = true
    ) {
        Column(
            modifier = Modifier
                .width(400.dp)
                .padding(16.dp, 16.dp, 16.dp, 0.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "¿Estás seguro que deseas eliminar este cliente?",
                textAlign = TextAlign.Center,
                color = AzulGris,
                fontSize = 24.sp
            )
            Spacer(modifier = Modifier.height(20.dp))
            Row {
                Boton("Aceptar") {
                    try {



                        mostrarConfirmar.value = false

                        Toast.makeText(
                            context,
                            "Se eliminó el cliente",
                            Toast.LENGTH_LONG
                        ).show()
                    } catch (e: Exception) {
                        Toast.makeText(
                            context,
                            "No se pudo eliminar",
                            Toast.LENGTH_LONG
                        ).show()
                    }
                }
                Spacer(modifier = Modifier.width(16.dp))
                Boton("Cancelar") {
                    mostrarConfirmar.value = false
                }
            }
        }
    }

}






@Preview(
    backgroundColor = 0xFFFFFFFF, showBackground = true, widthDp = 1080, heightDp = 560
)
@Composable
fun previewTable(){
    MaterialTheme {
        ClienteScreen()
    }
}










package com.solidtype.atenas_apk_2.gestion_proveedores.presentation.proveedor

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
import androidx.compose.material.icons.filled.LocalShipping
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.solidtype.atenas_apk_2.gestion_proveedores.presentation.cliente.modelo.Personastodas
import com.solidtype.atenas_apk_2.gestion_proveedores.presentation.proveedor.componets.TableProviders
import com.solidtype.atenas_apk_2.ui.theme.AzulGris
import com.solidtype.atenas_apk_2.ui.theme.GrisClaro
import com.solidtype.atenas_apk_2.util.ui.Components.AutocompleteSelect
import com.solidtype.atenas_apk_2.util.ui.Components.Boton
import com.solidtype.atenas_apk_2.util.ui.Components.Buscador
import com.solidtype.atenas_apk_2.util.ui.Components.Dialogo
import com.solidtype.atenas_apk_2.util.ui.Components.InputDetalle
import com.solidtype.atenas_apk_2.util.ui.Components.MenuLateral
import com.solidtype.atenas_apk_2.util.ui.Components.Titulo

@OptIn(ExperimentalMultiplatform::class)
@SuppressLint("StateFlowValueCalledInComposition", "SuspiciousIndentation")
@Composable
fun ProveedorScreen(
    navController: NavController,
    viewModel: ProveedorViewModel = hiltViewModel()

){
    val context = LocalContext.current
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    val mostrarDialogo = rememberSaveable { mutableStateOf(false) }
    val editar = rememberSaveable { mutableStateOf(false) }

    //formulario proveedor
    val idProveedor = rememberSaveable { mutableStateOf("") }
    val nombre = rememberSaveable { mutableStateOf("") }
    val Tipodocumento = rememberSaveable { mutableStateOf("") }
    val Numdocumento = rememberSaveable { mutableStateOf("") }
    val Email = rememberSaveable { mutableStateOf("") }
    val Telefono = rememberSaveable { mutableStateOf("") }
    val Direccion = rememberSaveable { mutableStateOf("") }

    //LISTA SELECTOR
    val DocumetSelector = listOf("Cédula", "Pasaporte")
    //estado busqueda
    val Busqueda = rememberSaveable { mutableStateOf("") }

    val mostrarConfirmar = rememberSaveable { mutableStateOf(false) }

    if(Busqueda.value.isNotBlank()) {
        viewModel.onUserEvent(ProveedorEvent.BuscarProveedor(Busqueda.value))
    }else{
        viewModel.onUserEvent(ProveedorEvent.MostrarProveedorEvent)
    }

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
                Titulo("Proveedor", Icons.Default.LocalShipping)

                Box (
                    modifier = Modifier.fillMaxWidth(),
                    contentAlignment = Alignment.BottomEnd
                ){
                    Buscador(busqueda = Busqueda.value) {
                        Busqueda.value = it
                    }
                }

                Spacer(modifier = Modifier.height(10.dp))

                TableProviders(uiState.proveedores, mostrarDialogo, editar,nombre,Tipodocumento,Numdocumento,Email,Telefono,mostrarConfirmar,idProveedor)

                Box(
                    modifier = Modifier.fillMaxWidth(),
                    contentAlignment = Alignment.BottomEnd
                ) {
                    Boton("Agregar"){
                        mostrarDialogo.value = true
                        editar.value = false

                        idProveedor.value = ""
                        nombre.value = ""
                        Direccion.value = ""
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
    Dialogo(max = false,titulo = if (editar.value)  "Editar Proveedor" else  "Nuevo Proveedor", mostrar = mostrarDialogo.value, onCerrarDialogo = { mostrarDialogo.value = false }, clickable = true) {
        Column(
            modifier = Modifier
                .background(Color(0xFFEEEEEE), RoundedCornerShape(20.dp))
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

                Spacer(modifier = Modifier.height(15.dp))

                AutocompleteSelect(
                    text = "Tipo de Documento",
                    variableStr = Tipodocumento.value,
                    items = DocumetSelector,
                ) {
                    Tipodocumento.value = it
                }

                Spacer(modifier = Modifier.height(15.dp))

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

                InputDetalle(
                    label = "Dirección",
                    valor = Direccion.value,
                ) {
                    Direccion.value = it
                }

                Spacer(modifier = Modifier.height(10.dp))

                val camposCompletos = nombre.value.isNotEmpty() && Numdocumento.value.isNotEmpty() && Email.value.isNotEmpty() && Telefono.value.isNotEmpty()


                if (editar.value)
                    Boton("Editar") {

                        try {
                            if (!camposCompletos) {
                                throw Exception("Campos vacios.")
                            }
                            viewModel.onUserEvent(
                                ProveedorEvent.EditarProveedor(
                                    Personastodas.Proveedor(
                                        idProveedor.value.toLong(),
                                        nombre.value,
                                        Tipodocumento.value,
                                        Numdocumento.value,
                                        Direccion.value,
                                        Telefono.value,
                                        Email.value,
                                    )
                                )
                            )
                            mostrarDialogo.value = false
                            Toast.makeText(context, "El proveedor fue editado con exito", Toast.LENGTH_SHORT).show()

                        }catch (e: Exception) {
                            Toast.makeText(context, e.message, Toast.LENGTH_SHORT).show()
                        }

                    }
                else
                    Boton("Agregar", habilitar = camposCompletos) {
                        try {
                            if (!camposCompletos) {
                                throw Exception("Campos vacios.")
                            }
                            viewModel.onUserEvent(
                                ProveedorEvent.AgregarProveedor(
                                    Personastodas.Proveedor(
                                        0,
                                        nombre.value,
                                        Tipodocumento.value,
                                        Numdocumento.value,
                                        Direccion.value,
                                        Telefono.value,
                                        Email.value,
                                    )
                                )
                            )

                            nombre.value = ""
                            Numdocumento.value = ""
                            Email.value = ""
                            Telefono.value = ""
                        } catch (e: Exception) {
                            Toast.makeText(context, e.message, Toast.LENGTH_SHORT).show()
                        }

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
                text = "¿Estás seguro que deseas eliminar este proveedor?",
                textAlign = TextAlign.Center,
                color = AzulGris,
                fontSize = 24.sp
            )
            Spacer(modifier = Modifier.height(20.dp))
            Row {
                Boton("Aceptar") {
                    try {
                        mostrarConfirmar.value = false

                        viewModel.onUserEvent(
                            ProveedorEvent.BorrarProveedor(
                                Personastodas.Proveedor(
                                    idProveedor.value.toLong(),
                                    nombre.value,
                                    Tipodocumento.value,
                                    Numdocumento.value,
                                    Direccion.value,
                                    Telefono.value,
                                    Email.value,
                                )
                            )
                        )
                        Toast.makeText(
                            context,
                            "Se eliminó el proveedor",
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
    MenuLateral(navController)
}
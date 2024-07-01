package com.solidtype.atenas_apk_2.products.presentation.inventory

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Inventory2
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.solidtype.atenas_apk_2.authentication.actualizacion.domain.TipoUser
import com.solidtype.atenas_apk_2.authentication.actualizacion.presentation.TipoUserSingleton
import com.solidtype.atenas_apk_2.core.pantallas.Screens
import com.solidtype.atenas_apk_2.products.presentation.inventory.componets.AreaProductos
import com.solidtype.atenas_apk_2.products.presentation.inventory.componets.AvatarConBotones
import com.solidtype.atenas_apk_2.products.presentation.inventory.componets.Detalles
import com.solidtype.atenas_apk_2.products.presentation.inventory.componets.DialogoExcel
import com.solidtype.atenas_apk_2.util.ui.Components.MenuLateral
import com.solidtype.atenas_apk_2.ui.theme.GrisClaro
import com.solidtype.atenas_apk_2.util.ui.Components.Buscador
import com.solidtype.atenas_apk_2.util.ui.Components.SnackbarAnimado
import com.solidtype.atenas_apk_2.util.ui.Components.Titulo
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@SuppressLint("CoroutineCreationDuringComposition", "QueryPermissionsNeeded")
@Composable
fun InventoryScreen(
    navController: NavController,
    viewModel: InventarioViewModel = hiltViewModel()
) {

    val context = LocalContext.current

    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    val busqueda = rememberSaveable { mutableStateOf("") }
    val mostrar = rememberSaveable { mutableStateOf(false) }

    if (busqueda.value.isNotBlank())
        viewModel.onEvent(InventariosEvent.BuscarProducto(busqueda.value))
    else
        viewModel.onEvent(InventariosEvent.GetProductos)

    if (uiState.pathExcel!!.isNotBlank()) Toast.makeText(
        context,
        "Exportado: ${uiState.pathExcel}",
        Toast.LENGTH_LONG
    ).show()

    val idInventario = rememberSaveable { mutableStateOf("") }
    val idCatalogo = rememberSaveable { mutableStateOf("") }
    val idProveedor = rememberSaveable { mutableStateOf("") }
    val categoria = rememberSaveable { mutableStateOf("") }
    val nombre = rememberSaveable { mutableStateOf("") }
    val descripcion = rememberSaveable { mutableStateOf("") }
    val costo = rememberSaveable { mutableStateOf("") }
    val precio = rememberSaveable { mutableStateOf("") }
    val modelo = rememberSaveable { mutableStateOf("") }
    val marca = rememberSaveable { mutableStateOf("") }
    val cantidad = rememberSaveable { mutableStateOf("") }
    val impuesto = rememberSaveable { mutableStateOf("") }
    val estado = rememberSaveable { mutableStateOf("") }
    val provider = rememberSaveable { mutableStateOf("") }

    val showSnackbar = rememberSaveable { mutableStateOf(false) }

    val coroutineScope = rememberCoroutineScope()
    var snackbarJob: Job by remember { mutableStateOf(Job()) }

    val showSnackbarIni = rememberSaveable { mutableStateOf(false) }

    val listEstados = listOf("Activo", "No Activo")

    if(uiState.categoria.isEmpty()) viewModel.onEvent(InventariosEvent.GetCategorias)
    if(uiState.proveedores.isEmpty()) viewModel.onEvent(InventariosEvent.Getrpoveedores)

    if (TipoUserSingleton.tipoUser != TipoUser.ADMIN) {
        navController.navigate(Screens.Login.route)
    } else if (uiState.isLoading) {
        Box(
            Modifier.fillMaxSize()
        ) {
            CircularProgressIndicator(
                modifier = Modifier.align(Alignment.Center)
            )
        }
    } else {
        if (showSnackbarIni.value) {
            showSnackbarIni.value = false
            snackbarJob.cancel() //Cancela el job anterior si existe
            showSnackbar.value = true
            snackbarJob = coroutineScope.launch {
                delay(10000L)
                showSnackbar.value = false
            }
        }
        Column(
            //All
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .background(GrisClaro),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Column(
                //Contenedor
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 100.dp, vertical = 20.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Titulo("Inventario", Icons.Outlined.Inventory2)
                Spacer(modifier = Modifier.height(10.dp))
                Row {
                    Column {
                        Buscador(busqueda.value) { busqueda.value = it }
                        AreaProductos(
                            uiState,
                            idInventario,
                            idCatalogo,
                            idProveedor,
                            nombre,
                            marca,
                            modelo,
                            cantidad,
                            costo,
                            precio,
                            impuesto,
                            descripcion,
                            estado
                        )
                    }
                    Detalles(
                        viewModel,
                        categoria,
                        uiState.categoria,
                        nombre,
                        idInventario,
                        descripcion,
                        costo,
                        precio,
                        modelo,
                        marca,
                        cantidad,
                        idCatalogo,
                        idProveedor,
                        impuesto,
                        estado,
                        context,
                        provider,
                        uiState.proveedores,
                        listEstados
                    )
                }
                AvatarConBotones(context, viewModel, showSnackbarIni, mostrar)
            }
        }
        DialogoExcel(mostrar)
        MenuLateral(navController)
        SnackbarAnimado(showSnackbar.value, uiState.uriPath, context)
    }
}
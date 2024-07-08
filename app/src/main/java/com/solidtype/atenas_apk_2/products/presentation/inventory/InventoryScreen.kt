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
import com.solidtype.atenas_apk_2.gestion_proveedores.presentation.proveedor.componets.DialogoProveedor
import com.solidtype.atenas_apk_2.gestion_usuarios.presentation.components.DialogoCategoria
import com.solidtype.atenas_apk_2.gestion_usuarios.presentation.components.DialogoConfirmarCategoria
import com.solidtype.atenas_apk_2.products.presentation.inventory.componets.AreaProductos
import com.solidtype.atenas_apk_2.products.presentation.inventory.componets.Botones
import com.solidtype.atenas_apk_2.products.presentation.inventory.componets.DialogoConfirmarProducto
import com.solidtype.atenas_apk_2.products.presentation.inventory.componets.DialogoConfirmarProveedor
import com.solidtype.atenas_apk_2.products.presentation.inventory.componets.DialogoExcel
import com.solidtype.atenas_apk_2.products.presentation.inventory.componets.DialogoProducto
import com.solidtype.atenas_apk_2.products.presentation.inventory.componets.SwitchInactivos
import com.solidtype.atenas_apk_2.ui.theme.GrisClaro
import com.solidtype.atenas_apk_2.util.ui.Components.Buscador
import com.solidtype.atenas_apk_2.util.ui.Components.MenuLateral
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
    val mostrarEjemplar = rememberSaveable { mutableStateOf(false) }

    if (busqueda.value.isNotBlank())
        viewModel.onEvent(InventariosEvent.BuscarProducto(busqueda.value))
    else
        viewModel.onEvent(InventariosEvent.GetProductos)

    if (uiState.pathExcel!!.isNotBlank()) Toast.makeText(
        context,
        "Exportado: ${uiState.pathExcel}",
        Toast.LENGTH_LONG
    ).show()

    val mostrarProducto = rememberSaveable { mutableStateOf(false) }
    val mostrarConfirmarProducto = rememberSaveable { mutableStateOf(false) }
    val editar = rememberSaveable { mutableStateOf(false) }

    val idCategoriaText = rememberSaveable { mutableStateOf("") }
    val idProveedorText = rememberSaveable { mutableStateOf("") }

    val idInventario = rememberSaveable { mutableStateOf("") }
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

    val mostrarCategoria = rememberSaveable { mutableStateOf(false) }

    val idCategoria = rememberSaveable { mutableStateOf("") }
    val nombreCategoria = rememberSaveable { mutableStateOf("") }
    val descripcionCategoria = rememberSaveable { mutableStateOf("") }
    val estadoCategoria = rememberSaveable { mutableStateOf("") }
    val mostrarConfirmarCategoria = rememberSaveable { mutableStateOf(false) }

    val mostrarProveedor = rememberSaveable { mutableStateOf(false) }

    val idProveedor = rememberSaveable { mutableStateOf("") }
    val nombreProveedor = rememberSaveable { mutableStateOf("") }
    val tipoDocumentoProveedor = rememberSaveable { mutableStateOf("") }
    val documentoProveedor = rememberSaveable { mutableStateOf("") }
    val direccionProveedor = rememberSaveable { mutableStateOf("") }
    val telefonoProveedor = rememberSaveable { mutableStateOf("") }
    val emailProveedor = rememberSaveable { mutableStateOf("") }
    val mostrarConfirmarProveedor = rememberSaveable { mutableStateOf(false) }

    val showSnackbar = rememberSaveable { mutableStateOf(false) }

    val coroutineScope = rememberCoroutineScope()
    var snackbarJob: Job by remember { mutableStateOf(Job()) }

    val showSnackbarIni = rememberSaveable { mutableStateOf(false) }

    val listEstados = listOf("Activo", "No Activo")

    if (uiState.categoria.isEmpty()) viewModel.onEvent(InventariosEvent.GetCategorias)
    if (uiState.proveedores.isEmpty()) viewModel.onEvent(InventariosEvent.Getrpoveedores)

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
                    Buscador(busqueda.value) { busqueda.value = it }
                    //Switch para mostrar productos inactivos4
                    SwitchInactivos(uiState, viewModel)
                }
                AreaProductos(
                    uiState,
                    idInventario,
                    idCategoriaText,
                    idProveedorText,
                    nombre,
                    marca,
                    modelo,
                    cantidad,
                    costo,
                    precio,
                    impuesto,
                    descripcion,
                    estado,
                    mostrarProducto,
                    editar,
                    categoria,
                    provider,
                    mostrarConfirmarProducto
                )
                Botones(
                    context,
                    viewModel,
                    showSnackbarIni,
                    mostrarEjemplar,
                    mostrarProducto,
                    editar,
                    idInventario,
                    categoria,
                    nombre,
                    descripcion,
                    costo,
                    precio,
                    modelo,
                    marca,
                    cantidad,
                    impuesto,
                    estado,
                    provider
                )
            }
        }
        DialogoProducto(
            mostrarProducto,
            viewModel,
            categoria,
            uiState,
            nombre,
            idInventario,
            descripcion,
            costo,
            precio,
            modelo,
            marca,
            cantidad,
            idCategoriaText,
            idProveedorText,
            impuesto,
            estado,
            context,
            provider,
            listEstados,
            mostrarCategoria,
            mostrarProveedor,
            idCategoria,
            idProveedor
        )
        DialogoConfirmarProducto(
            mostrarConfirmarProducto,
            viewModel,
            idInventario,
            idCategoriaText,
            idProveedorText,
            nombre,
            marca,
            modelo,
            cantidad,
            costo,
            precio,
            impuesto,
            descripcion,
            estado,
            categoria,
            provider,
            context,
            mostrarConfirmarCategoria
        )
        DialogoExcel(mostrarEjemplar)
        DialogoCategoria(
            mostrarCategoria,
            idCategoria,
            nombreCategoria,
            descripcionCategoria,
            estadoCategoria,
            uiState,
            viewModel,
            context,
            mostrarConfirmarCategoria
        )
        DialogoConfirmarCategoria(
            mostrarConfirmarCategoria,
            viewModel,
            idCategoria,
            nombreCategoria,
            descripcionCategoria,
            estadoCategoria,
            context
        )
        DialogoProveedor(
            mostrarProveedor,
            nombreProveedor,
            tipoDocumentoProveedor,
            documentoProveedor,
            direccionProveedor,
            telefonoProveedor,
            emailProveedor,
            viewModel,
            context,
            uiState,
            mostrarConfirmarProveedor,
            idProveedor
        )
        DialogoConfirmarProveedor(
            mostrarConfirmarProveedor,
            viewModel,
            idProveedor,
            nombreProveedor,
            tipoDocumentoProveedor,
            documentoProveedor,
            direccionProveedor,
            telefonoProveedor,
            emailProveedor,
            context
        )
        MenuLateral(navController)
        SnackbarAnimado(showSnackbar.value, uiState.uriPath, context)
    }
}
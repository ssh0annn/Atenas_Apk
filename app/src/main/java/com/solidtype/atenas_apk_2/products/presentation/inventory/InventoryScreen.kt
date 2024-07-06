package com.solidtype.atenas_apk_2.products.presentation.inventory

import android.annotation.SuppressLint
import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Inventory2
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.solidtype.atenas_apk_2.authentication.actualizacion.domain.TipoUser
import com.solidtype.atenas_apk_2.authentication.actualizacion.presentation.TipoUserSingleton
import com.solidtype.atenas_apk_2.core.pantallas.Screens
import com.solidtype.atenas_apk_2.gestion_usuarios.presentation.components.DialogoCategoria
import com.solidtype.atenas_apk_2.gestion_usuarios.presentation.components.DialogoConfirmarCategoria
import com.solidtype.atenas_apk_2.products.domain.model.actualizacion.categoria
import com.solidtype.atenas_apk_2.products.presentation.inventory.componets.AreaProductos
import com.solidtype.atenas_apk_2.products.presentation.inventory.componets.AvatarConBotones
import com.solidtype.atenas_apk_2.products.presentation.inventory.componets.Detalles
import com.solidtype.atenas_apk_2.products.presentation.inventory.componets.DialogoExcel
import com.solidtype.atenas_apk_2.ui.theme.AzulGris
import com.solidtype.atenas_apk_2.ui.theme.Blanco
import com.solidtype.atenas_apk_2.ui.theme.GrisClaro
import com.solidtype.atenas_apk_2.ui.theme.GrisOscuro
import com.solidtype.atenas_apk_2.util.formatoActivo
import com.solidtype.atenas_apk_2.util.formatoActivoDDBB
import com.solidtype.atenas_apk_2.util.ui.Components.AutocompleteSelect
import com.solidtype.atenas_apk_2.util.ui.Components.BotonBlanco
import com.solidtype.atenas_apk_2.util.ui.Components.Buscador
import com.solidtype.atenas_apk_2.util.ui.Components.Dialogo
import com.solidtype.atenas_apk_2.util.ui.Components.InputDetalle
import com.solidtype.atenas_apk_2.util.ui.Components.MenuLateral
import com.solidtype.atenas_apk_2.util.ui.Components.SnackbarAnimado
import com.solidtype.atenas_apk_2.util.ui.Components.Titulo
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@OptIn(ExperimentalMultiplatform::class)
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

    val mostrarCategoria = rememberSaveable { mutableStateOf(false) }

    val idCategoria = rememberSaveable { mutableStateOf("") }
    val nombreCategoria = rememberSaveable { mutableStateOf("") }
    val descripcionCategoria = rememberSaveable { mutableStateOf("") }
    val estadoCategoria = rememberSaveable { mutableStateOf("") }
    val mostrarConfirmarCategoria = rememberSaveable { mutableStateOf(false) }

    val mostrarProveedores = rememberSaveable { mutableStateOf(false) }

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
                        uiState,
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
                        listEstados,
                        mostrarCategoria,
                    )
                }
                AvatarConBotones(context, viewModel, showSnackbarIni, mostrar)
            }
        }
        DialogoExcel(mostrar)
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

        MenuLateral(navController)
        SnackbarAnimado(showSnackbar.value, uiState.uriPath, context)
    }
}

@Composable
@OptIn(ExperimentalMultiplatform::class)
fun DialogoProveedor(
    mostrarProveedor: MutableState<Boolean>,
    idProveedor: MutableState<String>,
    nombreProveedor: MutableState<String>,
    descripcionProveedor: MutableState<String>,
    estadoProveedor: MutableState<String>,
    uiState: ProductosViewStates,
    viewModel: InventarioViewModel,
    context: Context,
    mostrarConfirmarProveedor: MutableState<Boolean>
) {
    Dialogo(
        "Gestor de Categoría",
        mostrarProveedor.value,
        { mostrarProveedor.value = false },
        false
    ) {
        Row {//Detalles y Lista
            Column( //Detalles
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .padding(20.dp)
                    .background(AzulGris, RoundedCornerShape(20.dp))
            ) {
                LazyColumn(
                    modifier = Modifier
                        .padding(10.dp)
                        .background(GrisOscuro, RoundedCornerShape(5.dp))
                ) {
                    item {
                        Column(
                            modifier = Modifier
                                .padding(10.dp)
                        ) {
                            InputDetalle("ID", idProveedor.value) { idProveedor.value = it }
                            InputDetalle(
                                "Proveedor",
                                nombreProveedor.value
                            ) { nombreProveedor.value = it }
                            InputDetalle("Descripción", descripcionProveedor.value) {
                                descripcionProveedor.value = it
                            }
                            Spacer(modifier = Modifier.height(5.dp))
                            AutocompleteSelect(
                                "Estado",
                                estadoProveedor.value,
                                listOf("Activo", "Inactivo")
                            ) { estadoProveedor.value = it }
                        }
                    }
                }
                Row {
                    BotonBlanco("Guardar") {
                        /*try {
                            if (idProveedor.value.isEmpty() || nombreProveedor.value.isEmpty() || descripcionProveedor.value.isEmpty() || estadoProveedor.value.isEmpty()) {
                                throw Exception("Campos vacios.")
                            }

                            if (uiState.categoria.find { it.id_categoria == idProveedor.value.toLong() } != null) {
                                viewModel.onEvent(
                                    InventariosEvent.ActualizarProveedor(
                                        categoria(
                                            id_categoria = idCategoria.value.toLong(),
                                            nombre = nombreCategoria.value,
                                            descripcion = descripcionCategoria.value,
                                            estado = estadoCategoria.value.formatoActivoDDBB()
                                        )
                                    )
                                )
                            } else {
                                viewModel.onEvent(
                                    InventariosEvent.AgregarCategorias(
                                        categoria(
                                            id_categoria = idCategoria.value.toLong(),
                                            nombre = nombreCategoria.value,
                                            descripcion = descripcionCategoria.value,
                                            estado = estadoCategoria.value.formatoActivoDDBB()
                                        )
                                    )
                                )
                            }

                            idCategoria.value = ""
                            nombreCategoria.value = ""
                            descripcionCategoria.value = ""
                            estadoCategoria.value = ""

                            Toast.makeText(
                                context,
                                "Categoría guardada",
                                Toast.LENGTH_LONG
                            ).show()
                        } catch (e: Exception) {
                            Toast.makeText(
                                context,
                                "error: ${e.message}",
                                Toast.LENGTH_LONG
                            ).show()
                        }*/
                    }
                    Spacer(modifier = Modifier.width(40.dp))
                    BotonBlanco("Inactivar") { mostrarConfirmarProveedor.value = true }
                }
                Spacer(modifier = Modifier.height(10.dp))
            }
            Column( //Lista
                modifier = Modifier
                    .padding(0.dp, 20.dp, 0.dp, 0.dp)
                    .background(AzulGris, RoundedCornerShape(20.dp))
            ) {
                LazyColumn(
                    modifier = Modifier
                        .heightIn(max = 355.dp)
                        .padding(10.dp)
                        .background(GrisOscuro, RoundedCornerShape(5.dp))
                ) {
                    item {
                        Column(
                            modifier = Modifier.padding(5.dp)
                        ) {
                            Row(
                                modifier = Modifier
                                    .padding(5.dp)
                                    .background(GrisOscuro, RoundedCornerShape(5.dp))
                            ) {
                                Text(
                                    text = "ID",
                                    modifier = Modifier.weight(1f),
                                    color = Blanco,
                                    textAlign = TextAlign.Center
                                )
                                Text(
                                    text = "Nombre",
                                    modifier = Modifier.weight(1f),
                                    color = Blanco,
                                    textAlign = TextAlign.Center
                                )
                                Text(
                                    text = "Descripción",
                                    modifier = Modifier.weight(1f),
                                    color = Blanco,
                                    textAlign = TextAlign.Center
                                )
                                Text(
                                    text = "Estado",
                                    modifier = Modifier.weight(1f),
                                    color = Blanco,
                                    textAlign = TextAlign.Center
                                )
                            }
                        }
                    }
                    if (uiState.categoria.isNotEmpty())
                        items(uiState.categoria) { categoriaIndex ->
                            Row(
                                modifier = Modifier
                                    .padding(
                                        start = 10.dp,
                                        end = 5.dp,
                                        top = 5.dp,
                                        bottom = 5.dp
                                    )
                                    .background(
                                        GrisClaro,
                                        RoundedCornerShape(10.dp)
                                    )
                                    .clickable {
                                        idProveedor.value = categoriaIndex.id_categoria.toString()
                                        nombreProveedor.value = categoriaIndex.nombre
                                        descripcionProveedor.value =
                                            categoriaIndex.descripcion.toString()
                                        estadoProveedor.value =
                                            categoriaIndex.estado.formatoActivo()
                                    },
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(
                                    text = categoriaIndex.id_categoria.toString(),
                                    modifier = Modifier
                                        .padding(5.dp, 5.dp, 0.dp, 5.dp)
                                        .weight(1f),
                                    textAlign = TextAlign.Center
                                )
                                Text(
                                    text = categoriaIndex.nombre,
                                    modifier = Modifier.weight(1f),
                                    textAlign = TextAlign.Center
                                )
                                Text(
                                    text = categoriaIndex.descripcion.toString(),
                                    modifier = Modifier.weight(1f),
                                    textAlign = TextAlign.Center
                                )
                                Text(
                                    text = categoriaIndex.estado.formatoActivo(),
                                    modifier = Modifier
                                        .padding(0.dp, 5.dp, 5.dp, 5.dp)
                                        .weight(1f),
                                    textAlign = TextAlign.Center
                                )
                            }
                        }
                }
            }
        }
    }
}
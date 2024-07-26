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
import com.solidtype.atenas_apk_2.gestion_proveedores.presentation.cliente.modelo.Personastodas
import com.solidtype.atenas_apk_2.products.domain.model.actualizacion.categoria
import com.solidtype.atenas_apk_2.products.domain.model.actualizacion.inventario
import com.solidtype.atenas_apk_2.products.presentation.inventory.componets.DialogoProveedor
import com.solidtype.atenas_apk_2.products.presentation.inventory.componets.DialogoCategoria
import com.solidtype.atenas_apk_2.products.presentation.inventory.componets.AreaProductos
import com.solidtype.atenas_apk_2.products.presentation.inventory.componets.Botones
import com.solidtype.atenas_apk_2.products.presentation.inventory.componets.DialogoExcel
import com.solidtype.atenas_apk_2.products.presentation.inventory.componets.DialogoProducto
import com.solidtype.atenas_apk_2.util.ui.components.SwitchInactivos
import com.solidtype.atenas_apk_2.ui.theme.GrisClaro
import com.solidtype.atenas_apk_2.util.formatoActivoDDBB
import com.solidtype.atenas_apk_2.util.ui.components.Buscador
import com.solidtype.atenas_apk_2.util.ui.components.DialogoConfirmacion
import com.solidtype.atenas_apk_2.util.ui.components.Loading
import com.solidtype.atenas_apk_2.util.ui.components.MenuLateralSingleton
import com.solidtype.atenas_apk_2.util.ui.components.SnackbarAnimado
import com.solidtype.atenas_apk_2.util.ui.components.Titulo
import com.solidtype.atenas_apk_2.util.ui.components.confirmar
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

    val mostrarDialogo = remember { mutableStateOf(false) }
    val confirmarMensaje = remember { mutableStateOf("") }
    val accionDeConfirmacion = remember { mutableStateOf({}) }

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
    val provider = rememberSaveable { mutableStateOf("") }

    val mostrarCategoria = rememberSaveable { mutableStateOf(false) }
    val idCategoria = rememberSaveable { mutableStateOf("") }
    val nombreCategoria = rememberSaveable { mutableStateOf("") }
    val descripcionCategoria = rememberSaveable { mutableStateOf("") }
    val estadoCategoria = rememberSaveable { mutableStateOf("Activo") }

    val mostrarProveedor = rememberSaveable { mutableStateOf(false) }
    val idProveedor = rememberSaveable { mutableStateOf("") }
    val nombreProveedor = rememberSaveable { mutableStateOf("") }
    val tipoDocumentoProveedor = rememberSaveable { mutableStateOf("") }
    val documentoProveedor = rememberSaveable { mutableStateOf("") }
    val direccionProveedor = rememberSaveable { mutableStateOf("") }
    val telefonoProveedor = rememberSaveable { mutableStateOf("") }
    val emailProveedor = rememberSaveable { mutableStateOf("") }

    val showSnackbar = rememberSaveable { mutableStateOf(false) }

    val coroutineScope = rememberCoroutineScope()
    var snackbarJob: Job by remember { mutableStateOf(Job()) }

    val showSnackbarIni = rememberSaveable { mutableStateOf(false) }

    if (uiState.messages.isNotEmpty()) {
        Toast.makeText(context, uiState.messages, Toast.LENGTH_LONG).show()
        viewModel.onEvent(InventariosEvent.LimpiarMensaje)
    }

    if (uiState.categoria.isEmpty()) viewModel.onEvent(InventariosEvent.GetCategorias)
    if (uiState.proveedores.isEmpty()) viewModel.onEvent(InventariosEvent.Getrpoveedores)

    if (TipoUserSingleton.tipoUser != TipoUser.ADMIN) {
        navController.navigate(Screens.Login.route)
    } else if (uiState.isLoading) {
        Box(
            Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Loading(true)
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
                    Box(modifier = Modifier.weight(3f)) {
                        Buscador(busqueda.value) { busqueda.value = it }
                    }
                    Box(
                        modifier = Modifier.weight(1f),
                        contentAlignment = Alignment.CenterEnd
                    ) {
                        SwitchInactivos(uiState.switch) {
                            viewModel.onEvent(InventariosEvent.Switch)
                        }
                    }
                }
                AreaProductos(uiState.products, uiState.proveedores, uiState.categoria, uiState.switch, idInventario, nombre, marca, modelo, cantidad, costo, precio, impuesto, descripcion, mostrarProducto, categoria, provider, idCategoria, idProveedor,
                    onRestaurarProducto = { producto ->
                        {
                            viewModel.onEvent(
                                InventariosEvent.AgregarProductos(
                                    inventario(
                                        id_inventario = producto.id_inventario,
                                        id_categoria = producto.id_categoria,
                                        id_proveedor = producto.id_proveedor,
                                        nombre = producto.nombre,
                                        marca = producto.marca,
                                        modelo = producto.modelo,
                                        cantidad = producto.cantidad,
                                        precio_compra = producto.precio_compra,
                                        precio_venta = producto.precio_venta,
                                        impuesto = producto.impuesto,
                                        descripcion = producto.descripcion,
                                        estado = true
                                    )
                                )
                            )
                            mostrarDialogo.value = false
                        }.confirmar(
                            mensaje = "¿Estás seguro que deseas restaurar el producto '${producto.nombre}'?",
                            showDialog = { mostrarDialogo.value = true },
                            setMessage = { confirmarMensaje.value = it },
                            setAction = { accionDeConfirmacion.value = it }
                        )
                    },
                    onEliminarProducto = { producto ->
                        {
                            viewModel.onEvent(
                                InventariosEvent.EliminarProductos(
                                    inventario(
                                        id_inventario = producto.id_inventario,
                                        id_categoria = producto.id_categoria,
                                        id_proveedor = producto.id_proveedor,
                                        nombre = producto.nombre,
                                        marca = producto.marca,
                                        modelo = producto.modelo,
                                        cantidad = producto.cantidad,
                                        precio_compra = producto.precio_compra,
                                        precio_venta = producto.precio_venta,
                                        impuesto = producto.impuesto,
                                        descripcion = producto.descripcion,
                                        estado = false
                                    )
                                )
                            )
                            mostrarDialogo.value = false
                        }.confirmar(
                            mensaje = "¿Estás seguro que deseas eliminar el producto '${producto.nombre}'?",
                            showDialog = { mostrarDialogo.value = true },
                            setMessage = { confirmarMensaje.value = it },
                            setAction = { accionDeConfirmacion.value = it }
                        )
                    }
                )
                Botones(context, mostrarEjemplar, mostrarProducto, idInventario, categoria, nombre, descripcion, costo, precio, modelo, marca, cantidad, impuesto, provider, idCategoria, idProveedor) {
                    Toast.makeText(context, "Espere un momento...", Toast.LENGTH_SHORT)
                        .show()
                    viewModel.exportarExcel()
                    showSnackbarIni.value = true
                }
            }
        }
        DialogoProducto(mostrarProducto, categoria, nombre, idInventario, descripcion, costo, precio, modelo, marca, cantidad, idCategoria, idProveedor, impuesto, provider, mostrarCategoria, mostrarProveedor, uiState.categoria, uiState.proveedores) {
            viewModel.onEvent(
                InventariosEvent.AgregarProductos(
                    inventario(
                        id_inventario = idInventario.value.toLong(),
                        id_categoria = idCategoria.value.toLong(),
                        id_proveedor = idProveedor.value.toLong(),
                        nombre = nombre.value,
                        marca = marca.value,
                        modelo = modelo.value,
                        cantidad = cantidad.value.toInt(),
                        precio_compra = costo.value.toDouble(),
                        precio_venta = precio.value.toDouble(),
                        impuesto = impuesto.value.toDouble(),
                        descripcion = descripcion.value,
                        estado = true
                    )
                )
            )
            idInventario.value = ""
            idCategoria.value = ""
            idProveedor.value = ""
            nombre.value = ""
            marca.value = ""
            modelo.value = ""
            cantidad.value = ""
            costo.value = ""
            precio.value = ""
            impuesto.value = ""
            descripcion.value = ""
        }
        DialogoCategoria(mostrarCategoria, idCategoria, nombreCategoria, descripcionCategoria, estadoCategoria, uiState.categoria,
            onGuardar = {
                try {
                    if (idCategoria.value.isEmpty() || nombreCategoria.value.isEmpty() || descripcionCategoria.value.isEmpty() || estadoCategoria.value.isEmpty()) {
                        throw Exception("Campos vacios.")
                    }

                    if (uiState.categoria.find { it.id_categoria == idCategoria.value.toLong() } != null) {
                        viewModel.onEvent(
                            InventariosEvent.ActualizarCategoria(
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
                    estadoCategoria.value = "Activo"
                } catch (_: Exception) { }
            },
            onInactivar = {
                {
                    viewModel.onEvent(
                        InventariosEvent.eliminarCategoria(
                            categoria(
                                idCategoria.value.toLong(),
                                nombreCategoria.value,
                                descripcionCategoria.value,
                                estadoCategoria.value.formatoActivoDDBB()
                            )
                        )
                    )

                    viewModel.onEvent(InventariosEvent.GetCategorias)

                    idCategoria.value = ""
                    nombreCategoria.value = ""
                    descripcionCategoria.value = ""
                    estadoCategoria.value = "Activo"

                    mostrarDialogo.value = false
                }.confirmar(
                    mensaje = "¿Estás seguro que deseas inactivar la categoría '${nombreCategoria.value}'?",
                    showDialog = { mostrarDialogo.value = true },
                    setMessage = { confirmarMensaje.value = it },
                    setAction = { accionDeConfirmacion.value = it }
                )
            }
        )
        DialogoProveedor(mostrarProveedor, nombreProveedor, tipoDocumentoProveedor, documentoProveedor, direccionProveedor, telefonoProveedor, emailProveedor, uiState.proveedores, idProveedor,
            onGuardar = {
                try {
                    if (nombreProveedor.value.isEmpty() || tipoDocumentoProveedor.value.isEmpty() || documentoProveedor.value.isEmpty() || direccionProveedor.value.isEmpty() || telefonoProveedor.value.isEmpty() || emailProveedor.value.isEmpty()) {
                        throw Exception("Campos vacios.")
                    }

                    viewModel.onEvent(
                        InventariosEvent.CrearProveedor(
                            Personastodas.Proveedor(
                                id_proveedor = 0,
                                nombre = nombreProveedor.value,
                                tipo_documento = tipoDocumentoProveedor.value,
                                documento = documentoProveedor.value,
                                direccion = direccionProveedor.value,
                                telefono = telefonoProveedor.value,
                                email = emailProveedor.value
                            )
                        )
                    )

                    nombreProveedor.value = ""
                    tipoDocumentoProveedor.value = ""
                    documentoProveedor.value = ""
                    direccionProveedor.value = ""
                    telefonoProveedor.value = ""
                    emailProveedor.value = ""
                } catch (_: Exception) { }
            },
            onEliminar = {
                {
                    viewModel.onEvent(
                        InventariosEvent.EliminarProveedor(
                            Personastodas.Proveedor(
                                id_proveedor = idProveedor.value.toLong(),
                                nombre = nombreProveedor.value,
                                tipo_documento = tipoDocumentoProveedor.value,
                                documento = documentoProveedor.value,
                                direccion = direccionProveedor.value,
                                telefono = telefonoProveedor.value,
                                email = emailProveedor.value
                            )
                        )
                    )

                    viewModel.onEvent(InventariosEvent.Getrpoveedores)

                    idProveedor.value = ""
                    nombreProveedor.value = ""
                    tipoDocumentoProveedor.value = ""
                    documentoProveedor.value = ""
                    direccionProveedor.value = ""
                    telefonoProveedor.value = ""
                    emailProveedor.value = ""

                    mostrarDialogo.value = false
                }.confirmar(
                    mensaje = "¿Estás seguro que deseas eliminar el provedor '${nombreProveedor.value}'?",
                    showDialog = { mostrarDialogo.value = true },
                    setMessage = { confirmarMensaje.value = it },
                    setAction = { accionDeConfirmacion.value = it }
                )
            }
        )
        DialogoConfirmacion(
            showDialog = mostrarDialogo,
            confirmMessage = confirmarMensaje,
            onConfirmAction = accionDeConfirmacion
        )
        MenuLateralSingleton.menuLateral(navController)
        SnackbarAnimado(showSnackbar.value, uiState.uriPath, context)
        DialogoExcel(mostrarEjemplar)
    }
}
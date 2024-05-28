package com.solidtype.atenas_apk_2.gestion_usuarios.presentation

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.solidtype.atenas_apk_2.core.pantallas.Screens
import com.solidtype.atenas_apk_2.products.presentation.inventory.componets.BotonIconCircular
import com.solidtype.atenas_apk_2.ui.theme.AzulGris
import com.solidtype.atenas_apk_2.ui.theme.Blanco
import com.solidtype.atenas_apk_2.ui.theme.GrisClaro
import com.solidtype.atenas_apk_2.ui.theme.GrisOscuro
import com.solidtype.atenas_apk_2.util.ui.Components.AutocompleteSelect
import com.solidtype.atenas_apk_2.util.ui.Components.Avatar
import com.solidtype.atenas_apk_2.util.ui.Components.Boton
import com.solidtype.atenas_apk_2.util.ui.Components.Buscador
import com.solidtype.atenas_apk_2.util.ui.Components.InputDetalle
import com.solidtype.atenas_apk_2.util.ui.Components.Titulo
import com.solidtype.atenas_apk_2.util.ui.Pantalla

@OptIn(ExperimentalMultiplatform::class)
@Composable
fun GestionUsuariosScreen(navController: NavController, viewModel: UsuariosViewmodel = hiltViewModel()) {

    val context = LocalContext.current

    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    val busqueda = rememberSaveable { mutableStateOf("") }

    val rol = rememberSaveable { mutableStateOf("") }
    val nombre = rememberSaveable { mutableStateOf("") }
    val apellido = rememberSaveable { mutableStateOf("") }
    val correo = rememberSaveable { mutableStateOf("") }
    val clave = rememberSaveable { mutableStateOf("") }
    val telefono = rememberSaveable { mutableStateOf("") }

    if (uiState.roles.isEmpty())
        viewModel.onUserEvent(UserEvent.GetRoles)

    //val prueba = viewModel.onUserEvent(UserEvent.MostrarUserEvent)

    if (false) {
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
                Titulo("Usuarios", Icons.Outlined.AccountCircle)
                Spacer(modifier = Modifier.height(10.dp))
                Row {
                    Column {
                        Buscador(busqueda.value) { busqueda.value = it }
                        Box(//AreaUsuarios
                            modifier = Modifier
                                .padding(start = 20.dp)
                                .width(Pantalla.ancho - 550.dp)
                                .height(Pantalla.alto - 250.dp)
                                .background(AzulGris, RoundedCornerShape(20.dp))
                        ) {
                            Box(
                                modifier = Modifier
                                    .padding(20.dp)
                                    .fillMaxHeight()
                                    .clip(RoundedCornerShape(20.dp))
                                    .background(AzulGris)
                            ) {
                                Column(
                                    modifier = Modifier
                                        .fillMaxSize()
                                        .clip(RoundedCornerShape(5.dp))
                                        .background(GrisOscuro)
                                ) {
                                    Row(
                                        modifier = Modifier
                                            .padding(10.dp)
                                            .fillMaxWidth()
                                            .clip(RoundedCornerShape(5.dp))
                                            .background(GrisOscuro)

                                    ) {
                                        Text(
                                            text = "Imagen",
                                            modifier = Modifier.weight(1f),
                                            color = Blanco,
                                            textAlign = TextAlign.Center
                                        ) // Aquí debería ir la imagen del producto
                                        Text(
                                            text = "Nombre",
                                            modifier = Modifier.weight(1f),
                                            color = Blanco,
                                            textAlign = TextAlign.Center
                                        )
                                        Text(
                                            text = "Correo",
                                            modifier = Modifier.weight(1f),
                                            color = Blanco,
                                            textAlign = TextAlign.Center
                                        )
                                        Text(
                                            text = "Teléfono",
                                            modifier = Modifier.weight(1f),
                                            color = Blanco,
                                            textAlign = TextAlign.Center
                                        )
                                        Text(
                                            text = "Rol",
                                            modifier = Modifier.weight(1f),
                                            color = Blanco,
                                            textAlign = TextAlign.Center
                                        )
                                    }
                                    LazyColumn(
                                        modifier = Modifier
                                            .padding(start = 0.dp, end = 10.dp, bottom = 10.dp)
                                            .fillMaxSize()
                                            .background(GrisOscuro, RoundedCornerShape(5.dp))
                                    ) { //buscar componente para agregar filas de cards
                                        if(uiState.usuarios.isNotEmpty())
                                        items(uiState.usuarios) { usuario ->
                                            Row(
                                                modifier = Modifier
                                                    .padding(
                                                        start = 20.dp,
                                                        end = 10.dp,
                                                        top = 10.dp,
                                                        bottom = 10.dp
                                                    )
                                                    .background(GrisClaro, RoundedCornerShape(10.dp))
                                                    .clickable {
                                                    },
                                                verticalAlignment = Alignment.CenterVertically
                                            ) {
                                                Box(
                                                    modifier = Modifier
                                                        .padding(5.dp)
                                                        .weight(1f),
                                                    contentAlignment = Alignment.Center
                                                ) {
                                                    Icon(
                                                        imageVector = Icons.Outlined.AccountCircle,
                                                        contentDescription = null,
                                                        tint = AzulGris,
                                                        modifier = Modifier
                                                            .size(50.dp)
                                                    )
                                                }// Aquí debería ir la imagen del Usuario
                                                Text(
                                                    text = usuario.nombre,
                                                    modifier = Modifier.weight(1f),
                                                    textAlign = TextAlign.Center
                                                )
                                                Text(
                                                    text = usuario.email,
                                                    modifier = Modifier.weight(1f),
                                                    textAlign = TextAlign.Center
                                                )
                                                Text(
                                                    text = usuario.telefono,
                                                    modifier = Modifier.weight(1f),
                                                    textAlign = TextAlign.Center
                                                )
                                                Text(
                                                    text = uiState.roles.find { it.id_roll_usuario == usuario.id_roll_usuario }?.nombre ?: "",
                                                    modifier = Modifier.weight(1f),
                                                    textAlign = TextAlign.Center
                                                )
                                            }
                                        }
                                    }
                                }
                            }
                        }//AreaUsuarios
                    }
                    Column(//Detalles
                        modifier = Modifier
                            .padding(top = 0.dp)
                            .height(Pantalla.alto - 185.dp)
                    ) {
                        Column(
                            modifier = Modifier
                                .padding(start = 10.dp)
                                .fillMaxSize()
                                .clip(RoundedCornerShape(20.dp))
                                .background(AzulGris)
                        ) {
                            LazyColumn(
                                modifier = Modifier
                                    .padding(10.dp, 10.dp, 10.dp, 5.dp)
                                    .fillMaxWidth()
                                    .height(Pantalla.alto - 270.dp)
                                    .clip(RoundedCornerShape(5.dp))
                                    .background(GrisOscuro)
                            ) {// Area de detalles = Imagen del producto, Categoría y Nombre
                                item {
                                    Column(
                                        modifier = Modifier.padding(10.dp, 10.dp, 10.dp, 0.dp)
                                    ) {
                                        InputDetalle("Nombre", nombre.value) { nombre.value = it }
                                        InputDetalle("Apellido", apellido.value) {
                                            apellido.value = it
                                        }
                                        InputDetalle("Correo", correo.value) { correo.value = it }
                                        InputDetalle("Clave", clave.value) { clave.value = it }
                                        InputDetalle("Teléfono", telefono.value) {
                                            telefono.value = it
                                        }
                                        AutocompleteSelect(
                                            "Roll",
                                            rol.value,
                                            if(uiState.roles.isNotEmpty()) uiState.roles.map { it.nombre } else listOf(""),
                                            onClickAgregar = {}
                                        ) { rol.value = it }
                                    }
                                }
                            }
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.Center
                            ) {//Botones de cerrar y guardar
                                BotonIconCircular(
                                    true,
                                    onClick = {//Boton X para borrar productos
                                        try {/*
                                            viewModel.eliminarProductos(
                                                ProductEntity(
                                                    codigo.toInt(),
                                                    nombre,
                                                    descripcion,
                                                    categoria,
                                                    costo.toDouble(),
                                                    modelo,
                                                    precio.toDouble(),
                                                    marca,
                                                    cantidad.toInt()
                                                )

                                            )
                                            */
                                            nombre.value = ""
                                        } catch (e: Exception) {
                                            Toast.makeText(
                                                context,
                                                "No se pudo eliminar",
                                                Toast.LENGTH_LONG
                                            ).show()
                                        }
                                    }
                                )
                                Spacer(modifier = Modifier.width(60.dp))
                                BotonIconCircular(false, onClick = {
                                    try {
                                        /*viewModel.crearProductos(
                                        id_categoria= idCatalogo.value.toLong(),
                                        id_proveedor= idProveedor.value.toLong(),
                                        nombre= nombre.value,
                                        marca= marca.value,
                                        modelo= modelo.value,
                                        cantidad= cantidad.value.toInt(),
                                        precio_compra= costo.value.toDouble(),
                                        precio_venta= precio.value.toDouble(),
                                        impuesto= impuesto.value.toDouble(),
                                        descripcion= descripcion.value,
                                        estado= if (estado.value == "Activo") true else false
                                    )*/
                                    } catch (e: Exception) {
                                        Toast.makeText(
                                            context,
                                            "error: campos invalidos",
                                            Toast.LENGTH_LONG
                                        )
                                            .show()
                                    }/*viewModel.onGuardarDetalles()*/
                                })
                            }
                        }
                    }//Detalles
                }
                Row(//Avatar y Botones
                    modifier = Modifier.padding(top = 10.dp)
                ) { //Avatar y Botones
                    if (true) { // si no hay imagen de perfil
                        Avatar()
                    } else {
                    }
                    Spacer(modifier = Modifier.width(400.dp))
                    Row {
                        Boton("Debería?") {

                        }
                    }
                }//Avatar y Botones
            }
        }
    }
}
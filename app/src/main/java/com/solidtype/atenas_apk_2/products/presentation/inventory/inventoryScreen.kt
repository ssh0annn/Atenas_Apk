package com.solidtype.atenas_apk_2.products.presentation.inventory

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.graphics.Color.parseColor
import android.net.Uri
import android.widget.Toast
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInVertically
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Snackbar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.solidtype.atenas_apk_2.util.ui.Components.BotonBlanco
import com.solidtype.atenas_apk_2.products.domain.model.ProductEntity
import com.solidtype.atenas_apk_2.products.presentation.inventory.componets.AutocompleteSelect
import com.solidtype.atenas_apk_2.util.ui.Components.Avatar
import com.solidtype.atenas_apk_2.util.ui.Components.Boton
import com.solidtype.atenas_apk_2.products.presentation.inventory.componets.BotonIconCircular
import com.solidtype.atenas_apk_2.products.presentation.inventory.componets.Buscador
import com.solidtype.atenas_apk_2.util.ui.Components.Carrito
import com.solidtype.atenas_apk_2.products.presentation.inventory.componets.Dialogo
import com.solidtype.atenas_apk_2.products.presentation.inventory.componets.InputDetalle
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

fun showFilePicker(context: Context) {

    val intent = Intent(Intent.ACTION_OPEN_DOCUMENT).apply {
        addCategory(Intent.CATEGORY_OPENABLE)
        type = "*/*"
        putExtra(
            Intent.EXTRA_MIME_TYPES, arrayOf(
                "application/vnd.ms-excel",
                "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"
            )
        )
    }

    (context as? Activity)?.startActivityForResult(intent, 2)
}


@SuppressLint("CoroutineCreationDuringComposition", "QueryPermissionsNeeded")
@OptIn(ExperimentalMultiplatform::class)
@Composable
fun InventoryScreen(
    navController: NavController,
    viewModel: InventarioViewModel = hiltViewModel()
) {
    //val logeado:Boolean by InventarioViewModel.logeado.observeAsState(initial = true)
    //val logeado = true;

    //val viewModel: InventarioViewModel = hiltViewModel() //Luego lo quito; solo para pruebas

    val context = LocalContext.current

    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    var busqueda by rememberSaveable { mutableStateOf("") }
    var mostrar by rememberSaveable { mutableStateOf(false) }

    if (busqueda.isNotBlank()) {
        viewModel.buscarProductos(busqueda)
    } else {
        viewModel.mostrarProductos()
    }

    if (uiState.pathExcel!!.isNotBlank()) {
        Toast.makeText(context, "Exportado: ${uiState.pathExcel}", Toast.LENGTH_LONG).show()
    }

    var codigo by rememberSaveable {
        mutableStateOf("")
    }
    var categoria by rememberSaveable {
        mutableStateOf("")
    }
    var nombre by rememberSaveable {
        mutableStateOf("")
    }
    var descripcion by rememberSaveable {
        mutableStateOf("")
    }
    var costo by rememberSaveable {
        mutableStateOf("")
    }
    var precio by rememberSaveable {
        mutableStateOf("")
    }
    var modelo by rememberSaveable {
        mutableStateOf("")
    }
    var marca by rememberSaveable {
        mutableStateOf("")
    }
    var cantidad by rememberSaveable {
        mutableStateOf("")
    }

    var showSnackbar by rememberSaveable { mutableStateOf(false) }

    val coroutineScope = rememberCoroutineScope()
    var snackbarJob: Job by remember { mutableStateOf(Job()) }

    var showSnackbarIni by rememberSaveable { mutableStateOf(false) }

    val productos = uiState.products

    val categoriaList = listOf(
        "Accesorios",
        "Celulares",
        "Laptops",
        "Tablets",
        "Otros"
    )//Esto debería venir del ViewModel

    val uri = uiState.uriPath

    if (false) {
        //nav.navigate(Screens.Login.route)
    } else if (uiState.isLoading) {
        Box(
            Modifier.fillMaxSize()
        ) {
            CircularProgressIndicator(
                modifier = Modifier.align(Alignment.Center)
            )
        }
    } else {
        if (showSnackbarIni) {
            showSnackbarIni = false
            snackbarJob.cancel() //Cancela el job anterior si existe
            showSnackbar = true
            snackbarJob = coroutineScope.launch {
                delay(10000L)
                showSnackbar = false
            }
        }
        LazyColumn(
            modifier = Modifier
                .fillMaxHeight()
                .fillMaxWidth(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            item {
                Row {//Título, Buscador, Area de Productos y Detalles
                    Column {//Título, Buscador y Area de Productos
                        Row(
                            modifier = Modifier
                                .padding(top = 20.dp, bottom = 20.dp)
                                .width(610.dp)
                        ) {//Título y Buscador
                            Text(
                                text = "Inventario",
                                modifier = Modifier.padding(top = 15.dp, end = 0.dp),
                                style = TextStyle(
                                    fontSize = 30.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = Color(parseColor("#343341"))
                                )
                            ) //Título
                            Buscador(
                                busqueda = busqueda,
                            ) { busqueda = it }
                        }
                        //Area de productos
                        Box(
                            modifier = Modifier
                                //.padding(start = 20.dp)
                                .width(600.dp)
                                .height(350.dp)
                                .clip(RoundedCornerShape(20.dp))
                                .background(Color(parseColor("#343341")))
                        ) {
                            Column(
                                modifier = Modifier
                                    .padding(top = 20.dp, bottom = 20.dp)
                                    .width(500.dp)
                                    .verticalScroll(rememberScrollState())
                            ) {//Título y Buscador
                                Text(
                                    text = "Inventario",
                                    modifier = Modifier.padding(top = 15.dp, end = 0.dp),
                                    style = TextStyle(
                                        fontSize = 30.sp,
                                        fontWeight = FontWeight.Bold,
                                        color = Color(parseColor("#343341"))
                                    )
                                ) //Título
                                Buscador(
                                    busqueda = busqueda,
                                ) { busqueda = it }
                            }
                            //Area de productos
                            Box(
                                modifier = Modifier
                                    //.padding(start = 20.dp)
                                    .width(600.dp)
                                    .height(350.dp)
                                    .clip(RoundedCornerShape(20.dp))
                                    .background(Color(parseColor("#343341")))
                            ) {
                                Column(
                                    modifier = Modifier
                                        .fillMaxSize()
                                        .clip(RoundedCornerShape(5.dp))
                                        .background(Color(parseColor("#737A8C")))
                                ) {
                                    Row(
                                        modifier = Modifier
                                            .padding(10.dp)
                                            .fillMaxWidth()
                                            .clip(RoundedCornerShape(5.dp))
                                            .background(Color(parseColor("#737A8C")))

                                    ) {
                                        Text(
                                            text = "Imagen",
                                            modifier = Modifier.weight(1f),
                                            color = Color(0xFFFFFFFF),
                                            textAlign = TextAlign.Center
                                        ) // Aquí debería ir la imagen del producto
                                        Text(
                                            text = "Código",
                                            modifier = Modifier.weight(1f),
                                            color = Color(0xFFFFFFFF),
                                            textAlign = TextAlign.Center
                                        )
                                        Text(
                                            text = "Producto",
                                            modifier = Modifier.weight(1f),
                                            color = Color(0xFFFFFFFF),
                                            textAlign = TextAlign.Center
                                        )
                                        Text(
                                            text = "Precio",
                                            modifier = Modifier.weight(1f),
                                            color = Color(0xFFFFFFFF),
                                            textAlign = TextAlign.Center
                                        )
                                        Text(
                                            text = "Cantidad",
                                            modifier = Modifier.weight(1f),
                                            color = Color(0xFFFFFFFF),
                                            textAlign = TextAlign.Center
                                        )
                                    }
                                    LazyColumn(
                                        modifier = Modifier
                                            .padding(start = 10.dp, end = 10.dp, bottom = 10.dp)
                                            .fillMaxSize()
                                            .clip(RoundedCornerShape(5.dp))
                                            .background(Color(parseColor("#737A8C")))
                                    ) { //buscar componente para agregar filas de cards
                                        item {
                                            /*productos.chunked(4)
                                                .forEach { row -> //chunked(4) = 4 productos por fila
                                                    Row {//productos es una lista de objetos
                                                        row.forEach { product ->
                                                            CardProduct(
                                                                product
                                                            ) { clicked ->
                                                                codigo = "${clicked.Code_Product}"
                                                                nombre = clicked.Name_Product
                                                                categoria = clicked.Category_Product
                                                                descripcion =
                                                                    clicked.Description_Product
                                                                costo = clicked.Price_Product.toString()
                                                                precio =
                                                                    clicked.Price_Vending_Product.toString()
                                                                modelo = clicked.Model_Product
                                                                marca = clicked.Tracemark_Product
                                                                cantidad =
                                                                    clicked.Count_Product.toString()
                                                            }
                                                        }
                                                }*/

                                        }
                                        items(productos) {
                                            Row(
                                                modifier = Modifier
                                                    .padding(10.dp)
                                                    .clip(RoundedCornerShape(10.dp))
                                                    .background(Color(0xFFD9D9D9))
                                                    .clickable {
                                                        codigo = it.Code_Product.toString()
                                                        nombre = it.Name_Product
                                                        categoria = it.Category_Product
                                                        descripcion = it.Description_Product
                                                        costo = it.Price_Product.toString()
                                                        precio = it.Price_Vending_Product.toString()
                                                        modelo = it.Model_Product
                                                        marca = it.Tracemark_Product
                                                        cantidad = it.Count_Product.toString()
                                                    },
                                                verticalAlignment = Alignment.CenterVertically
                                            ) {
                                                Box(
                                                    modifier = Modifier.padding(10.dp)
                                                ) {
                                                    Carrito(false)
                                                }// Aquí debería ir la imagen del producto
                                                Text(
                                                    text = it.Code_Product.toString(),
                                                    modifier = Modifier.weight(1f),
                                                    textAlign = TextAlign.Center
                                                )
                                                Text(
                                                    text = it.Name_Product,
                                                    modifier = Modifier.weight(1f),
                                                    textAlign = TextAlign.Center
                                                )
                                                Text(
                                                    text = it.Price_Product.toString(),
                                                    modifier = Modifier.weight(1f),
                                                    textAlign = TextAlign.Center
                                                )
                                                Text(
                                                    text = it.Count_Product.toString(),
                                                    modifier = Modifier.weight(1f),
                                                    textAlign = TextAlign.Center
                                                )
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                    Column(
                        modifier = Modifier
                            .padding(top = 25.dp)
                            .width(350.dp)
                    ) {//Detalles = Area de detalles y Botones
                        Column(
                            modifier = Modifier
                                .padding(start = 10.dp)
                                .width(350.dp)
                                .height(430.dp)
                                .clip(RoundedCornerShape(20.dp))
                                .background(Color(parseColor("#343341"))),
                        ) {
                            LazyColumn(
                                modifier = Modifier
                                    .padding(10.dp, 10.dp, 10.dp, 5.dp)
                                    .fillMaxWidth()
                                    .height(350.dp)
                                    .clip(RoundedCornerShape(5.dp))
                                    .background(Color(parseColor("#737A8C")))
                            ) {// Area de detalles = Imagen del producto, Categoría y Nombre
                                item {
                                    Row(
                                        modifier = Modifier.padding(10.dp, 10.dp, 10.dp, 0.dp)
                                    ) {
                                        //Image(painter = /*viewModel.imagenProducto.value*/, contentDescription = "Imagen del producto")
                                        Box(
                                            modifier = Modifier.padding(top = 30.dp, end = 10.dp)
                                        ) {
                                            Carrito(true) //aquí debería ir la imagen del producto
                                        }
                                        Column {// Categoría y Nombre
                                            // hay que crear un componente si se repetirá mucho el textEdit; aquí van dos para la Categoría y Nombre
                                            AutocompleteSelect(
                                                "Categoría",
                                                categoria,
                                                categoriaList,
                                                true
                                            ) {
                                                categoria = it
                                            }
                                            InputDetalle(
                                                "Nombre", true, nombre
                                            ) { nombre = it }
                                        }
                                    }
                                    Column(
                                        modifier = Modifier
                                            .padding(start = 10.dp)
                                    ) {// Codigo, Descripción, Precio y Cantidad
                                        InputDetalle("Código", false, codigo) { codigo = it }
                                        InputDetalle(
                                            "Descripción", false, descripcion
                                        ) { descripcion = it }
                                        InputDetalle("Costo", false, costo) { costo = it }
                                        InputDetalle("Precio de Venta", false, precio) {
                                            precio = it
                                        }
                                        InputDetalle("Modelo", false, modelo) { modelo = it }
                                        InputDetalle("Marca", false, marca) { marca = it }
                                        InputDetalle("Cantidad", false, cantidad) {
                                            cantidad = it
                                        }
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
                                        try {
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
                                            codigo = ""
                                            categoria = ""
                                            nombre = ""
                                            descripcion = ""
                                            costo = ""
                                            precio = ""
                                            modelo = ""
                                            marca = ""
                                            cantidad = ""
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
                                        viewModel.crearProductos(
                                            Code_Product = codigo,
                                            Name_Product = nombre,
                                            Description_Product = descripcion,
                                            Category_Product = categoria,
                                            Price_Product = costo,
                                            Model_Product = modelo,
                                            Price_Vending_Product = precio,
                                            Tracemark_Product = marca,
                                            Count_Product = cantidad
                                        )
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
                    }
                }
                Row(
                    modifier = Modifier.padding(top = 10.dp)
                ) { //Avatar y Botones
                    //Avatar
                    if (true) { // si no hay imagen de perfil
                        Avatar()
                    } else {//Mostrar foto de perfil
                        //Image(painter = , contentDescription = )
                    }
                    Spacer(modifier = Modifier.width(400.dp))
                    Row {
                        //Botones para Importar, Exportar y Ver
                        Boton("Importar") {
                            showFilePicker(context)
                        }
                        Boton("Exportar") {
                            Toast.makeText(context, "Espere un momento...", Toast.LENGTH_SHORT)
                                .show()
                            viewModel.exportarExcel()
                            showSnackbarIni = true
                        }
                        Boton("Ejemplar") {
                            mostrar = true
                        }
                    }
                }
            }
        }
        Dialogo(mostrar = mostrar) {
            mostrar = false
        }
        AnimatedVisibility(
            visible = showSnackbar,
            enter = slideInVertically(
                initialOffsetY = { it },
                animationSpec = tween(500)
            )
        ) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.BottomCenter
            ) {
                Snackbar(
                    action = {
                        if(uri.isNotBlank()){
                            Row {
                                BotonBlanco("Compartir") {
                                    val fileUri = Uri.parse(uri)
                                    val shareIntent: Intent = Intent().apply {
                                        action = Intent.ACTION_SEND
                                        putExtra(Intent.EXTRA_STREAM, fileUri)
                                        type =
                                            "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"
                                    }
                                    val chooser =
                                        Intent.createChooser(shareIntent, "Compartir archivo")
                                    context.startActivity(chooser)
                                }
                                Spacer(modifier = Modifier.width(10.dp))
                                BotonBlanco("Ver") {
                                    val fileUri = Uri.parse(uri)
                                    val openIntent: Intent = Intent().apply {
                                        action = Intent.ACTION_VIEW
                                        setDataAndType(
                                            fileUri,
                                            "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"
                                        )
                                        flags = Intent.FLAG_GRANT_READ_URI_PERMISSION
                                    }
                                    val chooser = Intent.createChooser(openIntent, "Abrir archivo")
                                    if (openIntent.resolveActivity(context.packageManager) != null) {
                                        context.startActivity(chooser)
                                    } else {
                                        Toast.makeText(
                                            context,
                                            "No se encontró una aplicación para abrir este archivo. Favor de instalar una aplicación compatible con archivos de Excel.",
                                            Toast.LENGTH_SHORT
                                        ).show()
                                    }
                                }
                            }
                        }
                    },
                    modifier = Modifier.padding(8.dp),
                    containerColor = Color(0xFF343341)
                ) {
                    Text(
                        text = if (uri.isNotBlank()) "El archivo se guardó en: $uri" else "Hubo un error al exportar",
                        color =
                        if (uri.isNotBlank())
                            Color(0xFF77FF77)
                        else
                            Color(0xFFFF7777),
                    )
                }
            }
        }
    }
}



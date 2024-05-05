//Descomenta to.do si quieres usarlo (de arriba a abajo)

/*package com.solidtype.atenas_apk_2.products.presentation.inventory

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.util.Log
import android.widget.Toast
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.animation.slideInVertically
import androidx.compose.foundation.background
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Inventory2
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.DismissDirection
import androidx.compose.material3.DismissState
import androidx.compose.material3.DismissValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Snackbar
import androidx.compose.material3.SwipeToDismiss
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDismissState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.solidtype.atenas_apk_2.products.domain.model.ProductEntity
import com.solidtype.atenas_apk_2.products.presentation.inventory.componets.Buscador2
import com.solidtype.atenas_apk_2.products.presentation.inventory.componets.DialogoV2
import com.solidtype.atenas_apk_2.ui.theme.AzulGris
import com.solidtype.atenas_apk_2.ui.theme.Blanco
import com.solidtype.atenas_apk_2.ui.theme.GrisClaro
import com.solidtype.atenas_apk_2.util.ui.Components.Avatar
import com.solidtype.atenas_apk_2.util.ui.Components.Boton
import com.solidtype.atenas_apk_2.util.ui.Components.BotonBlanco
import com.solidtype.atenas_apk_2.util.ui.Components.Carrito
import com.solidtype.atenas_apk_2.util.ui.Pantalla
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun InventoryScreenV2(
    navController: NavController,
    viewModel: InventarioViewModel = hiltViewModel()
) {

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
/*
    var showConfirmSnackbar by rememberSaveable { mutableStateOf(false) }
    var lastDeletedItem by remember { mutableStateOf<ProductEntity?>(null) }
   var lastDeletedItemPosition by remember { mutableStateOf<Int?>(null) }
    val isRemoved = rememberSaveable { mutableStateOf(false) }
//    val isRemovedList = rememberSaveable { mutableStateOf(List(productos.size) { false }) }
    var isRemovedList by rememberSaveable(productos) { mutableStateOf(List(productos.size) { false }) }
    //var isRemovedList by rememberSaveable(productos) { mutableStateOf(List(productos.size) { mutableStateOf(false) }) }

    val state = rememberDismissState(
        confirmValueChange = { value ->
            if (value == DismissValue.DismissedToStart) { // Luego de deshacer, se mantiene en false?
                isRemoved.value = true
                true
            } else {
                false
            }
        }
    )

    LaunchedEffect(key1 = productos) {
        //isRemovedList = List(productos.size) { mutableStateOf(false) }
        isRemovedList = List(productos.size) { false }
    }*/

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
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 100.dp, vertical = 20.dp)
            ) {//Contenedor
                //Titulo
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Filled.Inventory2,
                        contentDescription = "Inventario",
                        tint = AzulGris,
                        modifier = Modifier
                            .size(40.dp)
                    )
                    Spacer(modifier = Modifier.width(10.dp))
                    Text(
                        text = "Inventario",
                        color = AzulGris,
                        fontSize = 28.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
                Spacer(modifier = Modifier.height(15.dp))
                //Buscador
                Buscador2(
                    busqueda = busqueda,
                ) { busqueda = it }
                //Tabla
                Box(
                    modifier = Modifier
                        .padding(top = 10.dp)
                        .fillMaxWidth()
                        .background(AzulGris, RoundedCornerShape(20.dp))
                ) {
                    Box(
                        modifier = Modifier
                            .padding(10.dp)
                            .fillMaxWidth()
                            .height(Pantalla.alto * 0.54f) // 54% de la pantalla
                            .clip(RoundedCornerShape(20.dp))
                            .background(Color(android.graphics.Color.parseColor("#343341")))
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxSize()
                                .clip(RoundedCornerShape(5.dp))
                                .background(Color(android.graphics.Color.parseColor("#737A8C")))
                        ) {
                            Row(
                                modifier = Modifier
                                    .padding(10.dp)
                                    .fillMaxWidth()
                                    .clip(RoundedCornerShape(5.dp))
                                    .background(Color(android.graphics.Color.parseColor("#737A8C")))

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
                                Text(
                                    text = "",
                                    modifier = Modifier.weight(0.4f),
                                    color = Color(0xFFFFFFFF),
                                    textAlign = TextAlign.Center
                                )
                            }
                            LazyColumn(
                                modifier = Modifier
                                    .padding(start = 10.dp, end = 10.dp, bottom = 10.dp)
                                    .fillMaxSize()
                                    .clip(RoundedCornerShape(5.dp))
                                    .background(Color(android.graphics.Color.parseColor("#737A8C")))
                            ) { //buscar componente para agregar filas de cards

                                /*items(
                                    items = programmingLanguages,
                                    key = { it }
                                ) { language ->
                                    SwipeToDeleteContainer(
                                        item = language,
                                        onDelete = {}
                                    ) { language ->
                                        Text(
                                            text = language,
                                            modifier = Modifier
                                                .fillMaxWidth()
                                                .background(
                                                    GrisOscuro)
                                                .padding(16.dp)
                                        )
                                    }
                                }*/


                                /*item {
                                productos.chunked(4)
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
                                    }
                            }*/

                                //--------------------------------

                                itemsIndexed(productos) {i, it ->

                                    /*SwipeToDeleteContainer(
                                        item = it,
                                        isRemoved = isRemovedList[i],
                                        state = state,
                                        onDelete = { item ->
                                            lastDeletedItem = item
                                            lastDeletedItemPosition = i
                                            viewModel.eliminarProductos(item)
                                            isRemovedList.toMutableList()[i] = true
                                            showConfirmSnackbar = true
                                        }
                                    ) {*/

                                    SwipeToDeleteContainer(
                                        item = it,
                                        onDelete = {

                                        }
                                    ) {
                                        Row(
                                            modifier = Modifier
                                                .padding(
                                                    vertical = 5.dp,
                                                    horizontal = 10.dp
                                                )
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
                                            verticalAlignment = Alignment.CenterVertically,
                                            horizontalArrangement = Arrangement.SpaceBetween
                                        ) {
                                            Box(
                                                modifier = Modifier
                                                    .weight(1f)
                                                    .padding(10.dp),
                                                contentAlignment = Alignment.Center
                                            ) {
                                                Carrito(chiquito = true)
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
                                            Icon(
                                                imageVector = Icons.Default.Menu,
                                                contentDescription = null,
                                                tint = AzulGris,
                                                modifier = Modifier
                                                    .padding(10.dp)
                                                    .size(30.dp)
                                                    .weight(0.4f)
                                            )
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
                //Botones
                Row(
                    modifier = Modifier.padding(top = 10.dp)
                ) { //Avatar y Botones
                    //Avatar
                    if (true) { // si no hay imagen de perfil
                        Avatar()
                    } else {//Mostrar foto de perfil
                        //Image(painter = , contentDescription = )
                    }
                    Spacer(
                        modifier = Modifier.width(
                            Pantalla.ancho * 0.14f
                        )
                    )
                    Row {
                        //Botones para Importar, Exportar y Ver
                        Boton("Importar") {
                            showFilePicker2(context)
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
                        Spacer(
                            modifier = Modifier.width(
                                Pantalla.ancho * 0.14f
                            )
                        )
                        Boton(icon = {
                            Icon(
                                imageVector = Icons.Filled.Add,
                                contentDescription = "Add",
                                tint = Color.White
                            )
                        }) { //Modal o Dialog para agregar producto
                            //showDialogEdit = true

                        }
                    }
                }
            }
        }
        DialogoV2(mostrar) {
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
                        if (uri.isNotBlank()) {
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

        /*AnimatedVisibility(showConfirmDialog) {
            AlertDialog(
                title = { Text("Confirmar eliminación") },
                text = {
                    Text(
                        "¿Estás seguro de que quieres eliminar este elemento?",
                        fontSize = 20.sp
                    )
                },
                onDismissRequest = { showConfirmDialog = false },
                confirmButton = {
                    Boton(
                        icon = {
                            Icon(
                                modifier = Modifier.size(30.dp),
                                imageVector = Icons.Filled.Check,
                                contentDescription = null,
                                tint = Color.White
                            )
                        }
                    ) {
                        showConfirmDialog = false
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
                },
                dismissButton = {
                    Boton(
                        icon = {
                            Icon(
                                modifier = Modifier.size(30.dp),
                                imageVector = Icons.Filled.Close,
                                contentDescription = null,
                                tint = Color.White
                            )
                        }
                    ) {
                        showConfirmDialog = false
                    }
                }
            )
        }*/

        /*AnimatedVisibility(
            visible = showConfirmSnackbar,
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
                        BotonBlanco("Deshacer") {
                            viewModel.crearProductos(
                                lastDeletedItem!!.Code_Product.toString(),
                                lastDeletedItem!!.Name_Product,
                                lastDeletedItem!!.Description_Product,
                                lastDeletedItem!!.Category_Product,
                                lastDeletedItem!!.Price_Product.toString(),
                                lastDeletedItem!!.Model_Product,
                                lastDeletedItem!!.Price_Vending_Product.toString(),
                                lastDeletedItem!!.Tracemark_Product,
                                lastDeletedItem!!.Count_Product.toString()
                            )
                            isRemovedList.toMutableList()[lastDeletedItemPosition!!] = false
//                            state.reset()
                            showConfirmSnackbar = false
//                            viewModel.mostrarProductos()
                            Log.i("Wow", "Se deshizo la eliminación")
                        }
                    },
                    modifier = Modifier.padding(8.dp),
                    containerColor = Color(0xFF343341)
                ) {
                    Text(
                        text = "El producto se eliminó correctamente",
                        color = Blanco
                    )
                }
            }
        }*/
    }
}

fun showFilePicker2(context: Context) {

    val intent = Intent(Intent.ACTION_OPEN_DOCUMENT).apply {
        addCategory(Intent.CATEGORY_OPENABLE)
        type = "*//*"
        putExtra(
            Intent.EXTRA_MIME_TYPES, arrayOf(
                "application/vnd.ms-excel",
                "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"
            )
        )
    }

    (context as? Activity)?.startActivityForResult(intent, 2)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun <T> SwipeToDeleteContainer(
    item: T,
    onDelete: (T) -> Unit,
    animationDuration: Int = 500,
    content: @Composable (T) -> Unit
) {
    var isRemoved by remember {
        mutableStateOf(false)
    }
    val state = rememberDismissState(
        confirmValueChange = { value ->
            if (value == DismissValue.DismissedToStart) {
                isRemoved = true
                true
            } else {
                false
            }
        }
    )

    LaunchedEffect(key1 = isRemoved) {
        if(isRemoved) {
            delay(animationDuration.toLong())
            onDelete(item)
        }
    }

    AnimatedVisibility(
        visible = !isRemoved,
        exit = shrinkVertically(
            animationSpec = tween(durationMillis = animationDuration),
            shrinkTowards = Alignment.Top
        ) + fadeOut()
    ) {
        SwipeToDismiss(
            state = state,
            background = {
                DeleteBackground(swipeDismissState = state)
            },
            dismissContent = { content(item) },
            directions = setOf(DismissDirection.EndToStart)
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DeleteBackground(
    swipeDismissState: DismissState
) {
    val color =
        if (swipeDismissState.dismissDirection == DismissDirection.EndToStart)
            Color(0xFFFF7777)
        else
            Color.Transparent

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color)
            .padding(8.dp),
        contentAlignment = Alignment.CenterEnd
    ) {
        Icon(
            modifier = Modifier
//                .size(50.dp)
                .padding(end = ((Pantalla.ancho * 0.5f) - 160.dp) * swipeDismissState.progress),
            imageVector = Icons.Outlined.Delete,
            contentDescription = null,
            tint = Blanco
        )
    }
}*/
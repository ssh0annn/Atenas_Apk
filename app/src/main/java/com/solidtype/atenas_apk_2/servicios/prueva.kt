package com.solidtype.atenas_apk_2.servicios



//import android.widget.Toast
//import androidx.compose.foundation.layout.Box
//import androidx.compose.foundation.layout.fillMaxWidth
//import androidx.compose.foundation.layout.padding
//import androidx.compose.material3.Button
//import androidx.compose.material3.DropdownMenuItem
//import androidx.compose.material3.ExperimentalMaterial3Api
//import androidx.compose.material3.ExposedDropdownMenuBox
//import androidx.compose.material3.ExposedDropdownMenuDefaults
//import androidx.compose.material3.Text
//import androidx.compose.material3.TextField
//import androidx.compose.runtime.Composable
//import androidx.compose.runtime.getValue
//import androidx.compose.runtime.mutableStateOf
//import androidx.compose.runtime.remember
//import androidx.compose.runtime.saveable.rememberSaveable
//import androidx.compose.runtime.setValue
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.platform.LocalContext
//import androidx.compose.ui.unit.dp
//
//
//
//@OptIn(ExperimentalMaterial3Api::class)
//@Composable
//fun Demo() {
//    val context = LocalContext.current
//    val coffeeDrinks = arrayOf("Americano", "Cappuccino", "Espresso", "Latte", "Mocha")
//    var expanded by remember { mutableStateOf(false) }
//    var selectedText by remember { mutableStateOf("") }
//
//    Box(
//        modifier = Modifier
//            .fillMaxWidth()
//            .padding(32.dp)
//    ) {
//        ExposedDropdownMenuBox(
//            expanded = expanded,
//            onExpandedChange = {
//                expanded = !expanded
//            }
//        ) {
//            TextField(
//                value = selectedText,
//                onValueChange = { selectedText = it },
//                label = { Text(text = "Start typing the name of the coffee") },
//                trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
//                modifier = Modifier.menuAnchor()
//            )
//
//            val filteredOptions =
//                coffeeDrinks.filter { it.contains(selectedText, ignoreCase = true) }
//            if (filteredOptions.isNotEmpty()) {
//                ExposedDropdownMenu(
//                    expanded = expanded,
//                    onDismissRequest = {
//                        // We shouldn't hide the menu when the user enters/removes any character
//                    }
//                ) {
//                    filteredOptions.forEach { item ->
//                        DropdownMenuItem(
//                            text = { Text(text = item) },
//                            onClick = {
//                                selectedText = item
//                                expanded = false
//                                Toast.makeText(context, item, Toast.LENGTH_SHORT).show()
//                            }
//                        )
//                    }
//                }
//            }
//        }
//    }
//}















//
//@Composable
//fun FirstScreen(onNavigate: (String,String) -> Unit) {
//    var descrp by rememberSaveable { mutableStateOf("aaaaa") }
//    var aa by rememberSaveable { mutableStateOf("sssss") }
//    Button(onClick = { onNavigate(descrp,aa) }) {
//        Text("Go to SecondScreen")
//    }
//}
//
//@Composable
//fun SecondScreen(descrp: String, aa:String) {
//    Text(text = descrp)
//    Text(text = aa)
//}
//
//@Composable
//fun dale(
//    viewmodel: ServiciosViewModel = hiltViewModel()
//) {
//
//    val state by viewmodel.uiStates.collectAsStateWithLifecycle()
//    var nombre by rememberSaveable { mutableStateOf("") }
//    var telefono by rememberSaveable { mutableStateOf("") }
//
//    if (state.listaClientes.isEmpty()) {
//        viewmodel.onEvent(ServiceEvent.GetClientes)
//    }
//
//    LazyColumn(modifier = Modifier.fillMaxSize()) {
//        items(state.listaClientes) { cliente ->
//
//            card(clienteUI = cliente!!) {
////                viewmodel.onEvent(ServiceEvent.prueva(cliente))
//                nombre = cliente.nombre.toString()
//                telefono = cliente.telefono.toString()
//            }
//
//
//        }
//    }
//
//    println(nombre + telefono)
//
//}

//@Composable
//fun card(clienteUI: Personastodas.ClienteUI, onclick: () -> Unit) {
//
//    Box(
//        modifier = Modifier.clickable(onClick = onclick)
//    ) {
//        Text(text = clienteUI.nombre.toString())
//    }


//}
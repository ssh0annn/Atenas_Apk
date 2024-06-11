package com.solidtype.atenas_apk_2.servicios.presentation.servicios

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.solidtype.atenas_apk_2.dispositivos.model.Dispositivo
import com.solidtype.atenas_apk_2.gestion_proveedores.presentation.cliente.ClienteEvent
import com.solidtype.atenas_apk_2.gestion_proveedores.presentation.cliente.modelo.Personastodas
import com.solidtype.atenas_apk_2.servicios.modelo.servicio
import com.solidtype.atenas_apk_2.servicios.presentation.modelo.FormaPagos
import com.solidtype.atenas_apk_2.ui.theme.AzulGris
import com.solidtype.atenas_apk_2.ui.theme.Blanco

@Composable
fun EjemploNey(viewModel: ServiciosViewModel = hiltViewModel()) {
    val state by viewModel.uiStates.collectAsStateWithLifecycle()
    var nuevoCliente by rememberSaveable { mutableStateOf(false) }
    var nuevoDispositivo by rememberSaveable { mutableStateOf(false) }
    var nuevoServicios by rememberSaveable { mutableStateOf(false) }
    var nuevoTicket by rememberSaveable { mutableStateOf(false) }
    var search by rememberSaveable {
        mutableStateOf("")
    }
    if(state.listaTickets.isEmpty()){
        viewModel.onTicket(OnTicket.GetTickets)
    }
    if(state.listaServicios.isEmpty()){
        viewModel.onServiceEvent(ServiceEvent.GetServicios)
    }
    if(state.listaClientes.isEmpty()){
        viewModel.onCliente(ClientEvents.GetClientes)
    }
    if(state.listaDispositivos.isEmpty()){
        viewModel.onDevice(DeviceEvent.GetDispositivos)
    }
    if (nuevoServicios){
        NuevoServicio {
            viewModel.onServiceEvent(ServiceEvent.CreateServicio(it))
            nuevoServicios = !nuevoServicios
        }

    }
   else if (nuevoTicket){
       TODO()

    }
   else if (nuevoDispositivo){
        NuevoDevice(){dispositivo -> viewModel.onDevice(DeviceEvent.CrearDispositivo(dispositivo))
            nuevoDispositivo = !nuevoDispositivo}

    }

   else if(nuevoCliente){
        ClienteForm(onSubmit ={cliente -> viewModel.onCliente(ClientEvents.CrearCliente(cliente))
        nuevoCliente = !nuevoCliente
        })
    }else{



    if (state.listaTickets.isNotEmpty()) {
        println("No esta bacio : ${state.listaTickets} <--")
    } else {
        println("Si esta bacio : ${state.listaTickets}  <---")
    }
    Column(modifier = Modifier
        .fillMaxSize()
        .verticalScroll(ScrollState(1))) {
        SelectorMio("Vendedor", search, listOf(state.usuario.toString()), true) {


        }
        SelectorMio("Dispositivo", search, state.listaDispositivos.let {
            it.map { persona ->
                persona?.nombre_comercial.toString()
            }
        }, false, onClickAgregar = {nuevoDispositivo = !nuevoDispositivo}) {
                selectedName ->
            val dispositivo = state.listaDispositivos.find { it?.nombre_comercial == selectedName }
            dispositivo?.let {
                viewModel.onDevice(DeviceEvent.DispositivoSelecionado(it))
                println("Esto fue lo que se seleciono: $it")
            }

        }
        SelectorMio(
            "Cliente", search,
            state.listaClientes.let {
                it.map { persona ->
                    persona?.nombre.toString()
                }
            },
            false,onClickAgregar = {nuevoCliente= !nuevoCliente}
        ) {
            selecion ->
            val cliente = state.listaClientes.find { it?.nombre == selecion }
            cliente?.let {
                viewModel.onCliente(ClientEvents.ClienteSelecionado(it))
            }

        }
        SelectorMio("Servicio", search, state.listaServicios.let {
                                                                 it.map { dato -> dato.nombre }
        }, false, onClickAgregar = {nuevoServicios = !nuevoServicios} ) {
            selecion ->
            val service = state.listaServicios.find { it.nombre == selecion }
            service?.let { viewModel.onServiceEvent(ServiceEvent.ServicioSelecionado(it)) }

        }
        SelectorMio("Forma de pago", search,
            FormaPagos.entries.map {
                it.toString()
        }, false) {
            selecionado ->
            when(selecionado){
                FormaPagos.CREDITO.toString() -> {
                    //viewModel.onPayment(PagosEvent)
                }
            }
        }


        Button(onClick = { /*TODO*/ }) {
            Text(text = "Realizar Pago")
        }
        Button(onClick = { /*TODO*/ }) {
            Text(text = "Datos del Ticket")
        }
        Button(onClick = { /*TODO*/ }) {
            Text(text = "Crear Tickets")
        }
        Button(onClick = { /*TODO*/ }) {
            Text(text = "datos del equipo")
        }
        Button(onClick = { /*TODO*/ }) {
            Text(text = "Forma de Pago")
        }
    }
    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SelectorMio(
    text: String,
    variableStr: String,
    items: List<String>,
    corto: Boolean = false,
    expanded: MutableState<Boolean> = rememberSaveable { mutableStateOf(false) },
    onClickAgregar: (() -> Unit)? = null,
    onSelectionChange: (String) -> Unit
) {
    var searchText: String by rememberSaveable { mutableStateOf(variableStr) }

    val keyboardController = LocalSoftwareKeyboardController.current

    val focusRequester = remember { FocusRequester() }

    ExposedDropdownMenuBox(
        expanded = expanded.value,
        onExpandedChange = {
            expanded.value = !expanded.value
        }
    ) {
        TextField(
            value = searchText,
            onValueChange = {
                searchText = it
                expanded.value = true
            },
            singleLine = true,
            textStyle = TextStyle(
                color = AzulGris,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            ), label = {
                Text(
                    text = text,
                    color = AzulGris,
                    fontSize = 15.sp,
                    fontWeight = FontWeight.ExtraBold
                )
            },
            trailingIcon = {
                ExposedDropdownMenuDefaults.TrailingIcon(
                    expanded = expanded.value
                )
            },
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = Blanco,
                unfocusedBorderColor = Blanco,
            ),
            modifier = Modifier
                .focusRequester(focusRequester)
                .onFocusChanged { focusState ->
                    if (!focusState.isFocused) {
                        expanded.value = false
                    }
                }
                .menuAnchor()
                .width(
                    when (corto) {
                        true -> 250.dp
                        false -> 300.dp
                    }
                )
                .background(Color(0xFFFFFFFF), RoundedCornerShape(15.dp))
                .fillMaxWidth()
                .wrapContentHeight()
                .clip(RoundedCornerShape(15.dp))
        )
        val filteredItems = items.filter { it.contains(searchText, ignoreCase = true) }
        if (filteredItems.isNotEmpty()) {
            ExposedDropdownMenu(
                expanded = expanded.value,
                onDismissRequest = {
                    // Nosotros no deberíamos ocultar el menú cuando el usuario ingresa o elimina algún carácter
                }
            ) {
                for (item in filteredItems) {
                    DropdownMenuItem(text = { Text(item) }, onClick = {
                        searchText = item
                        expanded.value = false
                        onSelectionChange(item)
                    })
                }
                if (onClickAgregar != null)
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(5.dp)
                            .clickable(onClick = {
                                onClickAgregar()
                                expanded.value = false
                                //Debería hacer un back
                                keyboardController?.hide()
                            }),
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            imageVector = Icons.Default.Add,
                            contentDescription = "Add",
                            tint = AzulGris
                        )
                        Text(
                            text = "Agregar",
                            color = AzulGris,
                            fontSize = 15.sp,
                            fontWeight = FontWeight.ExtraBold,
                            modifier = Modifier
                        )
                        Spacer(modifier = Modifier.width(10.dp))
                    }
            }
        }
    }
}

@Composable
fun <T> componente(data:T, onClick:() ->Unit ){


}

@Composable
fun ClienteForm(onSubmit: (Personastodas.ClienteUI) -> Unit) {
    var nombre by remember { mutableStateOf("") }
    var documento by remember { mutableStateOf("") }
    var telefono by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }


    Column(modifier = Modifier.padding(16.dp)) {
        TextField(
            value = nombre,
            onValueChange = { nombre = it },
            label = { Text("Nombre") },
            modifier = Modifier.fillMaxWidth()
        )


        Spacer(modifier = Modifier.height(8.dp))
        TextField(
            value = telefono,
            onValueChange = { telefono = it },
            label = { Text("Teléfono") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))
        TextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("Email") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = {
            val cliente = Personastodas.ClienteUI(nombre= nombre, documento = documento, telefono = telefono, email = email)
            onSubmit(cliente)
        }) {
            Text("Crear Cliente")
        }
    }
}
@Composable
fun NuevoDevice(onSubmit: (Dispositivo) -> Unit) {
    var nombre by remember { mutableStateOf("") }
    var modelo by remember { mutableStateOf("") }
    var marca by remember { mutableStateOf("") }


    Column(modifier = Modifier.padding(16.dp)) {
        TextField(
            value = nombre,
            onValueChange = { nombre = it },
            label = { Text("Nombre") },
            modifier = Modifier.fillMaxWidth()
        )


        Spacer(modifier = Modifier.height(8.dp))
        TextField(
            value = modelo,
            onValueChange = { modelo = it },
            label = { Text("Teléfono") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))
        TextField(
            value = marca,
            onValueChange = { marca = it },
            label = { Text("Email") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = {
            val dispositivo = Dispositivo(nombre_comercial = nombre, modelo = modelo, marca = marca)
            onSubmit(dispositivo)
        }) {
            Text("Crear Cliente")
        }
    }
}
@Composable
fun NuevoServicio(onSubmit: (servicio) -> Unit) {
    var nombre by remember { mutableStateOf("") }
    var modelo by remember { mutableStateOf("") }
    var marca by remember { mutableStateOf(true) }


    Column(modifier = Modifier.padding(16.dp)) {
        TextField(
            value = nombre,
            onValueChange = { nombre = it },
            label = { Text("Nombre") },
            modifier = Modifier.fillMaxWidth()
        )


        Spacer(modifier = Modifier.height(8.dp))
        TextField(
            value = modelo,
            onValueChange = { modelo = it },
            label = { Text("Teléfono") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))
        TextField(
            value = marca.toString(),
            onValueChange = { marca = true },
            label = { Text("Email") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = {
            val dispositivo = servicio(nombre = nombre, descripcion = modelo, estado = marca)
            onSubmit(dispositivo)
        }) {
            Text("Crear Cliente")
        }
    }
}

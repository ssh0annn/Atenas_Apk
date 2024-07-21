package com.solidtype.atenas_apk_2.servicios.presentation.servicios

import android.widget.Toast
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
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.SupervisedUserCircle
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.solidtype.atenas_apk_2.core.entidades.tipo_venta
import com.solidtype.atenas_apk_2.dispositivos.model.Dispositivo
import com.solidtype.atenas_apk_2.gestion_proveedores.presentation.cliente.modelo.Personastodas
import com.solidtype.atenas_apk_2.servicios.modelo.servicio
import com.solidtype.atenas_apk_2.servicios.presentation.modelo.FormaPagos
import com.solidtype.atenas_apk_2.ui.theme.AzulGris
import com.solidtype.atenas_apk_2.ui.theme.Blanco
import com.solidtype.atenas_apk_2.ui.theme.GrisOscuro
import com.solidtype.atenas_apk_2.ui.theme.Rojo



@OptIn(ExperimentalMultiplatform::class, ExperimentalMaterial3Api::class)
@Composable
fun selector(
    openDialog: MutableState<Boolean>,
    viewmodel: ServiciosViewModel = hiltViewModel(), listaSericios: List<servicio>,
    listaCliente: List<Personastodas.ClienteUI?>, listaDispositivos: List<Dispositivo?>
) {
    val palomo = LocalContext.current

    var search by rememberSaveable { mutableStateOf("") }
    var nuevoServicios by rememberSaveable { mutableStateOf(false) }
    var nuevoCliente by rememberSaveable { mutableStateOf(false) }
    var nuevoDispositivo by rememberSaveable { mutableStateOf(false) }
    val stateTicket by viewmodel.ticket.collectAsStateWithLifecycle()
    var back = Color(0xFF343341)

    val state by viewmodel.uiStates.collectAsStateWithLifecycle()
    val listacliente = state.listaClientes
    //modal
//    val openDialog = remember { mutableStateOf(false) }
    val open = remember { mutableStateOf(false) }
    val mostrar = remember { mutableStateOf(false) }
    val altura = remember { mutableStateOf(400.dp) }
    

    //Dato de pagos
    var tipoPago by rememberSaveable { mutableStateOf("") }

    //formulario cliente
    var nombre by rememberSaveable { mutableStateOf("") }
    var telefono by rememberSaveable { mutableStateOf("") }
    var email by rememberSaveable { mutableStateOf("") }
    var falla by rememberSaveable { mutableStateOf("") }
    var estado by rememberSaveable { mutableStateOf("") }
    var imei by rememberSaveable { mutableStateOf("") }
    var accesorio by rememberSaveable { mutableStateOf("") }
    var nota by rememberSaveable { mutableStateOf("") }

    //formulario servicio
    var dia by rememberSaveable { mutableStateOf("0") }
    var precio by rememberSaveable { mutableStateOf("0.0") }
    var descrp by rememberSaveable { mutableStateOf("") }
    val mostrar1 = remember { mutableStateOf(false) }

    //agregar dispositiviso
    var agra_marca by rememberSaveable { mutableStateOf("") }
    var agra_modelo by rememberSaveable { mutableStateOf("") }
    var nom_comercial by rememberSaveable { mutableStateOf("") }

    val context = LocalContext.current

    //modal cliente existente
    if (nuevoServicios){
        NuevoServicio {
            viewmodel.onServiceEvent(ServiceEvent.CreateServicio(it))
            nuevoServicios = !nuevoServicios
        }
    }
    else if(nuevoCliente){
        ClienteForm(onSubmit ={cliente -> viewmodel.onCliente(ClientEvents.CrearCliente(cliente))
            nuevoCliente = !nuevoCliente
        })
    }
    else if (nuevoDispositivo){
        NuevoDevice(){dispositivo -> viewmodel.onDevice(DeviceEvent.CrearDispositivo(dispositivo))
            nuevoDispositivo = !nuevoDispositivo}
    }
    if (mostrar1.value) {
        Box(
            modifier = Modifier.fillMaxWidth()
        ) {
            Box(
                modifier = Modifier.fillMaxWidth()
            ) {
                AlertDialog(
                    onDismissRequest = {
                        mostrar1.value = false
                    },
                    text = {

                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .verticalScroll(rememberScrollState())

                        ) {
                            Column(
                                modifier = Modifier.fillMaxWidth(),
                                verticalArrangement = Arrangement.Center,
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                //titulo
                                Spacer(modifier = Modifier.padding(top = 15.dp))
                                Text(
                                    text = "Cliente existentes",
                                    color = AzulGris,
                                    fontWeight = FontWeight.ExtraBold,
                                    fontSize = 35.sp,
                                )

                                //cuerpo-------------------------------------

                                Box(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .height(430.dp)
                                        .background(AzulGris, shape = RoundedCornerShape(20.dp))
                                ) {
                                    Column(
                                        modifier = Modifier
                                            .padding(10.dp)
                                            .fillMaxSize()
                                            .background(
                                                GrisOscuro,
                                                shape = RoundedCornerShape(20.dp)
                                            )
                                    ) {
                                        Row(
                                            modifier = Modifier
                                                .padding(10.dp)
                                                .fillMaxWidth()

                                        ) {

                                            Text(
                                                text = "Cliente",
                                                modifier = Modifier.weight(1f),
                                                color = Blanco,
                                                textAlign = TextAlign.Center,
                                                fontSize = 20.sp,
                                            )
                                            Text(
                                                text = "Telefono",
                                                modifier = Modifier.weight(1f),
                                                color = Blanco,
                                                textAlign = TextAlign.Center,
                                                fontSize = 20.sp,
                                            )
                                            Text(
                                                text = "Accion",
                                                modifier = Modifier.weight(1f),
                                                color = Blanco,
                                                textAlign = TextAlign.Center,
                                                fontSize = 20.sp,
                                            )
                                        }


                                        LazyColumn(
                                            modifier = Modifier
                                                .padding(start = 10.dp, end = 10.dp, bottom = 10.dp)
                                                .fillMaxSize()
                                                .background(GrisOscuro)
                                        ) { //buscar componente para agregar filas de cards
                                            itemsIndexed(listacliente) { i, clientes ->
                                                Column {
                                                    Row(
                                                        modifier = Modifier
                                                            .fillMaxWidth()
                                                            .height(40.dp)
                                                            .padding(3.dp)
                                                            .clip(RoundedCornerShape(50.dp))
                                                            .background(Blanco),
                                                        verticalAlignment = Alignment.CenterVertically
                                                    ) {
                                                        Text(
                                                            text = clientes?.nombre.toString(),
                                                            modifier = Modifier.weight(0.5f),
                                                            textAlign = TextAlign.Center
                                                        )
                                                        Text(
                                                            text = clientes?.telefono.toString(),
                                                            modifier = Modifier.weight(1f),
                                                            textAlign = TextAlign.Center

                                                        )
                                                        Box(
                                                            modifier = Modifier
                                                                .weight(0.5f)
                                                        ) {

                                                            card(clienteUI = clientes!!) {
                                                                nombre = clientes.nombre.toString()
                                                                telefono = clientes.telefono.toString()
                                                                email = clientes.email.toString()
                                                                openDialog.value = true
                                                                mostrar1.value = false
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    },

                    confirmButton = {
                        TextButton(modifier = Modifier
                            .background(
                                AzulGris, shape = RoundedCornerShape(20.dp)
                            )
                            .padding(5.dp),

                            onClick = {


                            }) {
                            Text("Guardar", color = Blanco)
                        }
                    },
                    dismissButton = {
                        TextButton(
                            modifier = Modifier
                                .background(Rojo, shape = RoundedCornerShape(20.dp))
                                .padding(5.dp),
                            onClick = {
                                mostrar1.value = false
                                openDialog.value = true
                            },
                        ) {
                            Text("Atras", color = Blanco)
                        }
                    },

                    modifier = Modifier
                        .width(800.dp)
                )
            }
        }
    }




    if (open.value) {
        Box(
            modifier = Modifier.fillMaxWidth()

        ) {
            Box(
                modifier = Modifier.fillMaxWidth()
            ) {
                AlertDialog(
                    onDismissRequest = {
                        open.value = false
                    },
                    text = {
                        Column (
                            modifier = Modifier
                                .padding(top = 5.dp)
                                .fillMaxWidth()
                                .verticalScroll(rememberScrollState()),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center
                        ){
                           Box {
                               Text(text ="Confirmar Datos",fontSize = 22.sp)
                           }
                        }
                    },


                    confirmButton = {
                        TextButton(modifier = Modifier
                            .background(
                                back, shape = RoundedCornerShape(20.dp)
                            )
                            .padding(5.dp),
                            onClick = {
                                viewmodel.onTicket(
                                    OnTicket.CrearTicket
                                )
                                dia = "0"
                                precio = "0.0"
                                descrp = ""
                                imei = ""
                                accesorio = ""
                                falla = ""
                                estado = ""
                                nota = ""
                                open.value = false
                                mostrar.value = false
                                openDialog.value = false
                            }) {
                            Text("Guardar", color = Blanco)
                        }
                    },

                    dismissButton = {
                        TextButton(
                            modifier = Modifier
                                .background(Rojo, shape = RoundedCornerShape(20.dp))
                                .padding(5.dp),
                            onClick = {
                                open.value = false
                                mostrar.value = true
                            },
                        ) {
                            Text("salir", color = Blanco)

                        }

                    },

                    modifier = Modifier
                        .width(800.dp)
                        .height(180.dp),
                )
            }
        }
    }


    //cuerpo del modal
    if (openDialog.value) {
        Box(
            modifier = Modifier.fillMaxWidth()

        ) {
            Box(
                modifier = Modifier.fillMaxWidth()
            ) {
                AlertDialog(
                    onDismissRequest = {
                        openDialog.value = false
                    },
                    text = {


                        Box(modifier = Modifier.clip(RoundedCornerShape(10.dp))) {
                            if (!mostrar.value) {
                                //seleccionar servicio  <----
                                Column() {
                                    Row() {
                                        Box(
                                            modifier = Modifier
                                                .width(220.dp)
                                                .padding(0.dp)
                                                .clip(RoundedCornerShape(10.dp))
                                        ) {
                                            SelectorMio("Seleccinar Servicio", search, state.listaServicios.let {
                                                it.map { dato -> dato.nombre
                                                }
                                            },false, onClickAgregar = {nuevoServicios = !nuevoServicios} ) {
                                                    selecion ->
                                                val service = state.listaServicios.find { it.nombre == selecion }
                                                service?.let {
                                                    viewmodel.onServiceEvent(ServiceEvent.ServicioSelecionado(it)) }
                                            }
                                        }

                                        Column(
                                            modifier = Modifier
                                                .padding(top = 5.dp)
                                                .fillMaxWidth()
                                                .verticalScroll(rememberScrollState()),
                                            horizontalAlignment = Alignment.CenterHorizontally,
                                            verticalArrangement = Arrangement.Center
                                        ) {

                                            Text(
                                                text = "Detalles",
                                                color = AzulGris,
                                                fontWeight = FontWeight.ExtraBold,
                                                fontSize = 30.sp,

                                                )
                                            Spacer(modifier = Modifier.padding(top = 10.dp))

                                            Row {
                                                Box {
                                                    NumericTextField(
                                                        label = "Dias",
                                                        valor = dia.toString(),

//                                        derecho = true,
//                                                        modifier = Modifier
                                                    ) {
                                                        dia = it
                                                    }
                                                }
                                                Box {
                                                    NumericTextField1(
                                                        label = "Precio", valor = precio,
                                                    ) {
                                                        precio = it
                                                    }
                                                }
                                            }
                                            Box() {
                                                Inputmed(
                                                    label = "Descripcion",
                                                    valor = descrp,
                                                    derecho = true,
                                                    modifier = Modifier
                                                ) {
                                                    descrp = it
                                                }
                                            }
                                            Spacer(modifier = Modifier .padding(top = 8.dp))
                                            Box(){
                                                SelectorMio("Vendedor", stateTicket.vendedor?.nombre ?: "", listOf(state.usuario).let {
                                                    it.map { user ->
                                                        user?.nombre.toString()
                                                    }

                                                }, true) {
                                                }
                                            }
                                        }

                                        Spacer(modifier = Modifier.padding(top = 30.dp))

                                    }
                                }
                                altura.value = 420.dp
                            } else {

                                //formulario cliente  <---
                                Box(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .verticalScroll(rememberScrollState())
                                ) {
                                    Column(
                                        modifier = Modifier.fillMaxWidth(),
                                        verticalArrangement = Arrangement.Center,
                                        horizontalAlignment = Alignment.CenterHorizontally
                                    ) {
                                        //titulo
                                        Spacer(modifier = Modifier.padding(top = 15.dp))
                                        Text(
                                            text = "Cliente",
                                            color = AzulGris,
                                            fontWeight = FontWeight.ExtraBold,
                                            fontSize = 35.sp,
                                        )

                                        //cuerpo1
                                        Row(
                                            modifier = Modifier.padding(top = 25.dp)
                                        ) {

                                            Box(modifier = Modifier
                                                .width(240.dp)
                                                .padding(0.dp)
                                                .clip(RoundedCornerShape(20.dp))) {
                                                //------------->selector cliente<-----------------
                                                SelectorMio(
                                                    "Seleccionar Cliente", search, state.listaClientes.let {
                                                        it.map { persona -> persona?.nombre.toString()
                                                        }
                                                    },
                                                    false,onClickAgregar = {nuevoCliente= !nuevoCliente}
                                                ) {
                                                        selecion ->
                                                    val cliente = state.listaClientes.find { it?.nombre == selecion }
                                                    cliente?.let {
                                                        viewmodel.onCliente(ClientEvents.ClienteSelecionado(it))
                                                    }
                                                }
                                            }
                                            Spacer(modifier = Modifier.padding(20.dp))
                                            Box(modifier = Modifier
                                                .width(240.dp)
                                                .padding(0.dp)
                                                .clip(RoundedCornerShape(20.dp))) {
                                                //------------------seleccion modeo------------------
                                                SelectorMio(" Seleccionar Disp", search, state.listaDispositivos.let {
                                                    it.map { persona ->
                                                        persona?.nombre_comercial.toString()

                                                    }
                                                }, false, onClickAgregar = {nuevoDispositivo = !nuevoDispositivo}) {
                                                        selectedName ->
                                                    val dispositivo = state.listaDispositivos.find { it?.nombre_comercial == selectedName }
                                                    dispositivo?.let {
                                                        viewmodel.onDevice(DeviceEvent.DispositivoSelecionado(it))
                                                        println("Esto fue lo que se seleciono: $it")
                                                    }

                                                }
                                            }
                                        }
                                        //cuerpo2
                                        Row(
                                            modifier = Modifier.padding(top = 5.dp)
                                        ) {
                                            Box() {
                                                NumericTextField3(
                                                    label = "Imei",
                                                    valor = imei,
                                                ) {
                                                    imei = it
                                                }
                                            }
                                            Spacer(modifier = Modifier.padding(20.dp))
                                            Box() {
                                                Input(
                                                    label = "Accesorios",
                                                    valor = accesorio,
                                                    derecho = true,
                                                    modifier = Modifier
                                                ) {
                                                    accesorio = it
                                                }
                                            }

                                        }
                                        //cuerpo3
                                        Row(
                                            modifier = Modifier.padding(top = 0.dp)
                                        ) {

                                            Box() {
                                                Inputlargo(
                                                    label = "Falla del equipo",
                                                    valor = falla,
                                                    derecho = true,
                                                    modifier = Modifier
                                                ) {
                                                    falla = it
                                                }
                                            }
                                        }
                                        //cuerpo4
                                        Row(
                                            modifier = Modifier.padding()
                                        ) {

                                            Box() {
                                                Inputlargo(
                                                    label = "Estado del equipo",
                                                    valor = estado,
                                                    derecho = true,
                                                    modifier = Modifier
                                                ) {
                                                    estado = it
                                                }
                                            }
                                        }
                                        //cuerpo5
                                        Row(
                                            modifier = Modifier.padding(top = 0.dp)
                                        ) {
                                            Box() {
                                                NumericTextField3(
                                                    label = "Abono",
                                                    valor = state.abono.toString(),
                                                ) {
                                                    viewmodel.onPayment(PagosEvent.Abono(
                                                        it.toDouble()
                                                    ))
                                                }


                                            }
                                            Spacer(modifier = Modifier.padding(20.dp))
                                            Box() {
                                                Input(
                                                    label = "Subtotal",
                                                    valor = stateTicket.datosFinance?.subtotal.toString(),
                                                    derecho = true,
                                                    
                                                    modifier = Modifier
                                                ) {

                                                }
                                            }

                                        }
                                        //cuerpo6
                                        Row(
                                            modifier = Modifier.padding()
                                        ) {

                                            Box() {
                                                Inputlargo(
                                                    label = "Nota",
                                                    valor = nota,
                                                    derecho = true,
                                                    modifier = Modifier.padding(top = 50.dp)
                                                ) {
                                                    nota = it
                                                }
                                            }
                                        }
                                        //cuerpo7
                                        Row(
                                            modifier = Modifier.padding(top = 0.dp)
                                        ) {


                                            Box() {
                                                Input(
                                                    label = "Total",
                                                    valor = stateTicket.datosFinance?.total.toString(),                                                   derecho = true,
                                                    modifier = Modifier

                                                ) {

                                                }
                                            }
                                            Spacer(modifier = Modifier.padding(20.dp))
                                            Box(modifier = Modifier
                                                .width(240.dp)
                                                .padding(top = 10.dp)
                                                .clip(RoundedCornerShape(20.dp))){
                                                SelectorMio("Forma de pago", search,
                                                    FormaPagos.entries.map {
                                                        it.toString()
                                                    }, false) {
                                                        selecionado ->
                                                            tipoPago = selecionado
                                                }
                                            }
                                        }
                                        Box(
                                            modifier = Modifier .padding(start = 150.dp)
                                        ){
                                            Row() {
                                                   Checkbox(
                                                       checked = state.impuestos,
                                                       onCheckedChange = {
                                                          viewmodel.onPayment(PagosEvent.Impuestos(
                                                              it
                                                          ))
                                                       })
                                                Box(modifier = Modifier .padding(top = 15.dp)){
                                                    Text(text = "Impuesto")
                                                }


                                            }
                                        }
                                    }
                                }
                                altura.value = 600.dp
                            }
                        }
                    },


                    confirmButton = {
                        if(precio == "0.0" || dia == "0" || descrp.isEmpty() ) {
                            back = Color(0xFF8285A5)
                        }else{
//                                    mostrar.value = true
                            back = Color(0xFF343341)
                        }
                        TextButton(modifier = Modifier
                            .background(
                                back, shape = RoundedCornerShape(20.dp)
                            )
                            .padding(5.dp),

                            onClick = {
                                    if(precio == "0.0" || dia == "0" || descrp.isEmpty()){
                                        Toast.makeText(context, "Campos Vacios", Toast.LENGTH_SHORT).show()
                                    }else{
                                        viewmodel.onPayment(
                                            PagosEvent.DatosDelPago(
                                                tipo_venta(
                                                    subtotal = precio.toDouble(),
                                                )
                                            )
                                        )
                                        mostrar.value = true
                                    }

                                    if(imei.isEmpty() || accesorio.isEmpty() || falla.isEmpty() || estado.isEmpty()  || nota.isEmpty() ){

                                    }else{
                                        viewmodel.onTicket(
                                            OnTicket.InforTicket(
                                                InfoTicket(
                                                    imei = imei,
                                                    falla = falla,
                                                    descripcion = descrp,
                                                    nota = nota,
                                                    assesorios = accesorio
                                                )
                                            )
                                        )
                                        open.value = true
//                                        viewmodel.onTicket(
//                                            OnTicket.CrearTicket(
//                                                ServicioTicket(
//                                                    cliente = stateTicket.cliente,
//                                                    dispositivo = stateTicket.dispositivo,
//                                                    vendedor = stateTicket.vendedor,
//                                                    detalles = stateTicket.detalles,
//                                                    servicio = stateTicket.servicio,
//                                                    datosFinance = stateTicket.datosFinance
//                                                )
//                                            )
//                                        )

                                    }



                                //--------------------------555
//                                if (!nota.isNullOrEmpty()) {
//                                    viewmodel.onEvent(
//                                        ServiceEvent.CrearTicket(
//                                            ticket(
//                                                id_vendedor = 1,
//                                                id_cliente = 1,
//                                                id_tipo_venta = 1,
//                                                id_dispositivo = 1,
//                                                imei = email,
//                                                falla = falla,
//                                                descripcion = descrp,
//                                                nota = nota,
//                                                assesorios = accesorios,
//                                                total = total.toDouble(),
//                                                abono = abono.toDouble(),
//                                                presupuesto = precio.toDouble(),
//                                                subtotal = sub.toDouble(),
//                                                impuesto = impuesto.toDouble(),
//                                                fecha_inicio = LocalDate.now(),
//                                                fecha_final = LocalDate.now(),
//                                                estado = true
//                                            )
//                                        )
//                                    )
//                                }


                            }) {
                            if (!mostrar.value) {
                                Text("Siguiente", color = Blanco)
                            } else {
                                Text("Guardar", color = Blanco)
                            }
                        }
                    },

                    dismissButton = {
                        TextButton(
                            modifier = Modifier
                                .background(Rojo, shape = RoundedCornerShape(20.dp))
                                .padding(5.dp),
                            onClick = {
                                if (!mostrar.value) {
                                    openDialog.value = false
                                } else {
                                    mostrar.value = false
                                }
                            },
                        ) {
                            if (!mostrar.value) {
                                Text("Salir", color = Blanco)

                            } else {
                                Text("Atras", color = Blanco)

                            }
                        }
                        // boton cliente------------------------------
                        if (!mostrar.value) {
                        } else {
                            Spacer(modifier = Modifier.padding(horizontal = 140.dp))
                            Icon(imageVector = Icons.Filled.SupervisedUserCircle,
                                contentDescription = "",
                                tint = AzulGris,
                                modifier = Modifier
                                    .padding(bottom = 0.dp)
                                    .size(60.dp)
                                    .clickable {
//                                        viewmodel.onEvent(ServiceEvent.GetClientes)
                                        openDialog.value = false
                                        mostrar1.value = true
                                    })
                        }
                    },

                    modifier = Modifier
                        .width(800.dp)
//                        .background(GrisOscuro)
                        .height(altura.value),
                )
            }
        }
    }

    //modal
    val openDialog1 = remember { mutableStateOf(false) }
    val openDialog2 = remember { mutableStateOf(false) }
    val altura1 = remember { mutableStateOf(300.dp) }

    var nuevoServicio by rememberSaveable { mutableStateOf("") }

    //cuerpo del modal agregar servicio
    if (openDialog1.value) {
        Box(
            modifier = Modifier.fillMaxWidth()

        ) {
            Box(
                modifier = Modifier.fillMaxWidth()
            ) {
                AlertDialog(
                    onDismissRequest = {
                        openDialog1.value = false
                    },
                    text = {

                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .verticalScroll(rememberScrollState())
                        ) {
                            Column(
                                modifier = Modifier.fillMaxWidth(),
                                verticalArrangement = Arrangement.Center,
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                //titulo
                                Spacer(modifier = Modifier.padding(top = 15.dp))
                                Text(
                                    text = "Nuevo Servicio",
                                    color = AzulGris,
                                    fontWeight = FontWeight.ExtraBold,
                                    fontSize = 35.sp,
                                )

                                //cuerpo1
                                Row(
                                    modifier = Modifier.padding(top = 25.dp)
                                ) {

                                    Box() {
                                        Input(
                                            label = "Servico",
                                            valor = stateTicket.servicio?.nombre.toString() ,
                                            derecho = true,
                                            modifier = Modifier
                                        ) {
//                                            nuevoServicio = it
                                        }
                                    }
                                }
                            }
                        }
                    },

                    confirmButton = {
                        TextButton(modifier = Modifier
                            .background(
                                AzulGris, shape = RoundedCornerShape(20.dp)
                            )
                            .padding(5.dp),

                            onClick = {
                                if (!nuevoServicio.isNullOrEmpty()) {
                                    viewmodel.onServiceEvent(
                                        ServiceEvent.CreateServicio(
                                            servicio(
                                                nombre = nuevoServicio,
                                                descripcion = nuevoServicio,
                                                estado = true
                                            )
                                        )
                                    )
                                    openDialog1.value = false
                                }

                            }) {
                            Text("Guardar", color = Blanco)
                        }
                    },
                    dismissButton = {
                        TextButton(
                            modifier = Modifier
                                .background(Rojo, shape = RoundedCornerShape(20.dp))
                                .padding(5.dp),
                            onClick = {
                                openDialog1.value = false
                            },
                        ) {
                            Text("Salir", color = Blanco)
                        }
                    },

                    modifier = Modifier
                        .width(800.dp)
//                        .background(GrisOscuro)
                        .height(altura1.value),
                )
            }
        }
    }
//modal agregar dipositivos
    if (openDialog2.value) {
        Box(
            modifier = Modifier.fillMaxWidth()

        ) {
            Box(
                modifier = Modifier.fillMaxWidth()
            ) {
                AlertDialog(
                    onDismissRequest = {
                        openDialog2.value = false
                    },
                    text = {

                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .verticalScroll(rememberScrollState())
                        ) {
                            Column(
                                modifier = Modifier.fillMaxWidth(),
                                verticalArrangement = Arrangement.Center,
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                //titulo
                                Spacer(modifier = Modifier.padding(top = 15.dp))
                                Text(
                                    text = "Nuevo Dipositivos",
                                    color = AzulGris,
                                    fontWeight = FontWeight.ExtraBold,
                                    fontSize = 35.sp,
                                )
                                Spacer(modifier = Modifier.padding(top = 15.dp))
                                //cuerpo1
                                    Row(){
                                    Box() {
                                        Input(
                                            label = "Marca",
                                            valor = agra_marca,
                                            derecho = true,
                                            modifier = Modifier
                                        ) {
                                            agra_marca = it
                                        }
                                    }
                                      Spacer(modifier = Modifier.padding(start = 30.dp))
                                    Box() {
                                        Input(
                                            label = "Modelo",
                                            valor = agra_modelo,
                                            derecho = true,
                                            modifier = Modifier
                                        ) {
                                            agra_modelo = it
                                        }
                                    }
                                }
                                Spacer(modifier = Modifier.padding(top = 10.dp))
                                Box() {
                                    Inputlargo(
                                        label = "Comercial",
                                        valor = nom_comercial,
                                        derecho = true,
                                        modifier = Modifier
                                    ) {
                                        nom_comercial = it
                                    }
                                }
                            }
                        }
                    },

                    confirmButton = {
                        TextButton(modifier = Modifier
                            .background(
                                AzulGris, shape = RoundedCornerShape(20.dp)
                            )
                            .padding(5.dp),

                            onClick = {
//                                if (!nom_comercial.isNullOrEmpty()) {
//                                    viewmodel.onEvent(
//                                        ServiceEvent.CrearDispositivo(
//                                            Dispositivo(
//                                                nombre_comercial = nom_comercial,
//                                                modelo = agra_modelo,
//                                                marca = agra_marca,
//                                            )
//                                        )
//                                    )
                                    openDialog2.value = false
//                                    Toast.makeText(
//                                       Text(text = "Guardado Existoxamente")
//                                    )
                           //  }

                            }) {
                            Text("Guardar", color = Blanco)
                        }
                    },
                    dismissButton = {
                        TextButton(
                            modifier = Modifier
                                .background(Rojo, shape = RoundedCornerShape(20.dp))
                                .padding(5.dp),
                            onClick = {
                                openDialog2.value = false
                            },
                        ) {
                            Text("Salir", color = Blanco)
                        }
                    },

                    modifier = Modifier
                        .width(800.dp)
//                        .background(GrisOscuro)
                        .height(400.dp),
                )
            }
        }
    }


//    Row(
//        modifier = Modifier
//            .fillMaxWidth()
//            .padding()
//    ) {
//
//
//        Icon(imageVector = Icons.Filled.AddCircle,
//            contentDescription = "",
//            tint = AzulGris,
//            modifier = Modifier
//                .padding(top = 0.dp)
//                .size(60.dp)
//                //abrir modal
//                .clickable {
//                    openDialog.value = true
//                })
//    }
    

}

@Composable
fun Bot (openDialog: MutableState<Boolean>){

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding()
    ) {
        Icon(imageVector = Icons.Filled.AddCircle,
            contentDescription = "",
            tint = AzulGris,
            modifier = Modifier
                .padding(top = 0.dp)
                .size(60.dp)
                .clickable {
                    openDialog.value = true
                }
        )
    }
}







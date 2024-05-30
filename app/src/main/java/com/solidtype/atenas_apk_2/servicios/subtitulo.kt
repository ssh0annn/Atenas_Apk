package com.solidtype.atenas_apk_2.servicios

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.FactCheck
import androidx.compose.material.icons.filled.Work
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.solidtype.atenas_apk_2.facturacion.presentation.componets.InputBlanco
import com.solidtype.atenas_apk_2.ui.theme.AzulGris
import org.apache.poi.xssf.usermodel.TextAlign
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.TextField
import androidx.compose.runtime.remember
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import com.solidtype.atenas_apk_2.ui.theme.Blanco
import com.solidtype.atenas_apk_2.ui.theme.GrisClaro
import com.solidtype.atenas_apk_2.ui.theme.GrisOscuro
import com.solidtype.atenas_apk_2.ui.theme.PurpleGrey80


@Composable
fun Tituloserv(){

    val drawerState = rememberDrawerState(DrawerValue.Closed)
    val scope = rememberCoroutineScope()





    Row {//Título
        Text(
            text = "Servicios",
            color = AzulGris,
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(10.dp)
        )
        Icon(

            imageVector = Icons.Filled.Work,
            contentDescription = "",
            tint = AzulGris,
            modifier = Modifier
                .padding(top = 10.dp)
                .size(40.dp)
        )





    }
}

@OptIn(ExperimentalMaterial3Api::class, ExperimentalMultiplatform::class)
@Composable
fun cuerpo2(){
    val context = LocalContext.current
    val coffeeDrinks = arrayOf("FRP", "Liberacion Red", "Reparacion Sofware", "Semi Factory", "Salto bloquo icloud","recuperacion contraseña", "reparacion Hardware", "otros")
    var expanded by remember { mutableStateOf(true) }
    var selectedText by remember { mutableStateOf(coffeeDrinks[0]) }
    var dia by rememberSaveable { mutableStateOf("") }
    var precio by rememberSaveable { mutableStateOf("") }
    var descrp by rememberSaveable { mutableStateOf("") }

    Column() {
        Row (){
                    Box(
                        modifier = Modifier
                            .width(220.dp)
                            .padding(0.dp)
                            .clip(RoundedCornerShape(10.dp))
                    ) {
                        ExposedDropdownMenuBox(
                            expanded = expanded,
                            onExpandedChange = {
                                expanded = !expanded
                            }
                        ) {
                            TextField(
                                value = selectedText,
                                onValueChange = {},
                                readOnly = true,
                                trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
                                modifier = Modifier.menuAnchor()
                            )

                            ExposedDropdownMenu(
                                modifier = Modifier
                                    .height(300.dp),
                                expanded = expanded,
                                onDismissRequest = { expanded = false }
                            ) {
                                coffeeDrinks.forEach { item ->
                                    DropdownMenuItem(
                                        text = { Text(text = item) },
                                        onClick = {
                                            selectedText = item
                                            expanded = false
                                            Toast.makeText(context, item, Toast.LENGTH_SHORT).show()
                                        }
                                    )
                                }
                            }
                        }
                    }

                        Column(
                            modifier = Modifier
                                .padding(top = 30.dp)
                                .fillMaxWidth()
                                .verticalScroll(rememberScrollState()),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center) {
                            
                            Text(
                                text = "Detalles",
                                color = AzulGris,
                                fontWeight = FontWeight.ExtraBold,
                                fontSize = 30.sp,
                                                                
                            )
                            Spacer(modifier = Modifier .padding(top = 10.dp))
                            Box (){
                                Inputmed(
                                    label = "Servicio",
                                    valor = selectedText,
                                    derecho = true,
                                    modifier = Modifier
                                ) {
                                }
                            }

                            Row {
                                Box {
                                    Inputpeq(
                                        label = "Dias",
                                        valor = dia,
//                                        derecho = true,
                                        modifier = Modifier
                                    ) {
                                    dia = it
                                    }
                                }
                                Box {
                                    Inputt(
                                        label = "Precio",
                                        valor = precio,
//                                        derecho = true,
                                        modifier = Modifier
                                    ) {
                                        precio = it
                                    }
                                }
                            }
                            Box (){
                                Inputmed(
                                    label = "Descripcion",
                                    valor = descrp,
                                    derecho = true,
                                    modifier = Modifier
                                ) {
                                    descrp = it
                                }
                            }
                        }
            
            Spacer(modifier = Modifier .padding(top = 20.dp))

        }
    }


}



@SuppressLint("MutableCollectionMutableState")
@OptIn(ExperimentalMultiplatform::class, ExperimentalMaterial3Api::class)
@Composable

fun cuerpo(

){

    //formulario cliente
    var nombre by rememberSaveable { mutableStateOf("") }
    var modelo by rememberSaveable { mutableStateOf("") }
    var telefono by rememberSaveable { mutableStateOf("") }
    var email by rememberSaveable { mutableStateOf("") }
    var falla by rememberSaveable { mutableStateOf("") }
    var estado by rememberSaveable { mutableStateOf("") }
    var marca by rememberSaveable { mutableStateOf("") }
    var abono by rememberSaveable { mutableStateOf("") }
    var nota by rememberSaveable { mutableStateOf("") }
    var restante by rememberSaveable { mutableStateOf("") }
    var total by rememberSaveable { mutableStateOf("") }


    Box (
        modifier = Modifier
            .fillMaxWidth()
            .verticalScroll(rememberScrollState())
//            .background(GrisOscuro)


            
    ){
        Column (
            modifier = Modifier
                .fillMaxWidth(),
                    verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ){
        //titulo
            Spacer(modifier = Modifier .padding(top = 15.dp))
        Text(
            text = "Cliente",
            color = AzulGris,
            fontWeight = FontWeight.ExtraBold,
            fontSize = 35.sp,
        )

        //cuerpo1
            Row (
                modifier = Modifier
                    .padding(top = 25.dp)
            ){

                Box (){
                    Input(
                        label = "Nombre",
                        valor = nombre,
                        derecho = true,
                        modifier = Modifier
                    ) {
                        nombre = it
                    }
                }
                Spacer(modifier = Modifier.padding(20.dp))
                Box (){
                    Input(
                        label = "Modelo",
                        valor = modelo,
                        derecho = true,
                        modifier = Modifier

                    ) {
                        modelo = it
                    }
                }
            }
            //cuerpo2
            Row (
                modifier = Modifier
                    .padding(top = 10.dp)
            ){

                Box (){
                    Input(
                        label = "Telefono",
                        valor = telefono,
                        derecho = true,
                        modifier = Modifier
                    ) {
                        telefono = it
                    }
                }
                Spacer(modifier = Modifier.padding(20.dp))
                Box (){
                    Input(
                        label = "Email",
                        valor = email,
                        derecho = true,
                        modifier = Modifier

                    ) {
                        email = it
                    }
                }
            }
            //cuerpo3
            Row (
                modifier = Modifier
                    .padding(top = 0.dp)
            ){

                Box (){
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
                Row (
                    modifier = Modifier
                        .padding()
                ){

                    Box (){
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
            Row (
                modifier = Modifier
                    .padding(top = 0.dp)
            ){

                Box (){
                    Input(
                        label = "Marca",
                        valor = marca,
                        derecho = true,
                        modifier = Modifier
                    ) {
                        marca = it
                    }
                }
                Spacer(modifier = Modifier.padding(20.dp))
                Box (){
                    Input(
                        label = "Abono",
                        valor = abono,
                        derecho = true,
                        modifier = Modifier

                    ) {
                        abono = it
                    }
                }
            }
            //cuerpo6
            Row (
                modifier = Modifier
                    .padding()
            ){

                Box (){
                    Inputlargo(
                        label = "Nota",
                        valor = nota,
                        derecho = true,
                        modifier = Modifier
                            .padding(top = 50.dp)
                    ) {
                        nota = it
                    }
                }
            }
            //cuerpo7
            Row (
                modifier = Modifier
                    .padding(top = 0.dp)
            ){

                Box (){
                    Input(
                        label = "Restante",
                        valor = restante,
                        derecho = true,
                        modifier = Modifier
                    ) {
                        restante = it
                    }
                }
                Spacer(modifier = Modifier.padding(20.dp))
                Box (){
                    Input(
                        label = "Total",
                        valor = total,
                        derecho = true,
                        modifier = Modifier

                    ) {
                        total = it
                    }
                }
            }
        }
    }
}

@SuppressLint("MutableCollectionMutableState")
@OptIn(ExperimentalMultiplatform::class, ExperimentalMaterial3Api::class)
@Composable

fun cuerpo3(

){

    //formulario cliente
    var servicio by rememberSaveable { mutableStateOf("") }
    var costo by rememberSaveable { mutableStateOf("") }
    Box (
        modifier = Modifier
            .fillMaxWidth()
            .verticalScroll(rememberScrollState())
//            .background(GrisOscuro)

    ){
        Column (
            modifier = Modifier
                .fillMaxWidth(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            //titulo
            Spacer(modifier = Modifier .padding(top = 15.dp))
            Text(
                text = "Nuevo Servicio",
                color = AzulGris,
                fontWeight = FontWeight.ExtraBold,
                fontSize = 35.sp,
            )

            //cuerpo1
            Row (
                modifier = Modifier
                    .padding(top = 25.dp)
            ){

                Box (){
                    Input(
                        label = "Servico",
                        valor = servicio,
                        derecho = true,
                        modifier = Modifier
                    ) {
                        servicio = it
                    }
                }
//                Spacer(modifier = Modifier.padding(20.dp))
//                Box (){
//                    Input(
//                        label = "Precio",
//                        valor = costo,
//                        derecho = true,
//                        modifier = Modifier
//
//                    ) {
//                        costo = it
//                    }
//                }
//            }
            }
        }
    }
}



@SuppressLint("MutableCollectionMutableState")
@OptIn(ExperimentalMultiplatform::class, ExperimentalMaterial3Api::class)
@Composable

fun imprimir(servicio: List<servicioss> = listOf()){

    //formulario cliente
    var servicio by rememberSaveable { mutableStateOf("") }
    Box (
        modifier = Modifier
            .fillMaxWidth()
            .verticalScroll(rememberScrollState())
    ){
        Column (
            modifier = Modifier
                .fillMaxWidth(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            //titulo
            Spacer(modifier = Modifier .padding(top = 15.dp))
            Text(
                text = "SolidType",
                color = AzulGris,
                fontWeight = FontWeight.ExtraBold,
                fontSize = 35.sp,
            )
            //cuerpo1
            Row {
                Column (
                modifier = Modifier
                    .width(300.dp)
                    .background(Color.Red),
                    horizontalAlignment = Alignment.CenterHorizontally,

                ){
                    Text(
                        text = "Cliente",
                        color = AzulGris,
                        fontWeight = FontWeight.ExtraBold,
                        fontSize = 20.sp,
                    )
                    Text(
                        text = "johan",
                        color = AzulGris,
                        fontWeight = FontWeight.ExtraBold,
                        fontSize = 18.sp,
                    )
                }
                Column (
                modifier = Modifier
                    .width(300.dp)
                    .background(Color.Gray),
                        horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ){
                    Text(
                        text = "servicio",
                        color = AzulGris,
                        fontWeight = FontWeight.ExtraBold,
                        fontSize = 20.sp,
                    )
                    Text(
                        text = "reparacion software",
                        color = AzulGris,
                        fontWeight = FontWeight.ExtraBold,
                        fontSize = 20.sp,
                    )
                }
            }



        }
    }
}


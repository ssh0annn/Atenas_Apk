package com.solidtype.atenas_apk_2.users.presentation.register

import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton

import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.Visibility
import androidx.core.text.isDigitsOnly
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.navigation.NavController
import com.solidtype.atenas_apk_2.users.presentation.pantallas.Screens


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OutlinedTextFieldExample(context: Context,nav:NavController, validarr: login_medenview = login_medenview()) {
    val configuration = LocalConfiguration.current
    val screenHeightDp = configuration.screenHeightDp.dp
    val screenHeightPx = with(LocalDensity.current) { screenHeightDp.toPx() }
    

    Column(modifier = Modifier


        .fillMaxSize(),

        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        val gradient = Brush.verticalGradient(0f to Color.Gray, 1000f to Color.White)

        var text by rememberSaveable {mutableStateOf("")}
        var sim by rememberSaveable {mutableStateOf("")}
        var apellido by rememberSaveable {mutableStateOf("")}
        var correo by rememberSaveable {mutableStateOf("")}
        var nnegocio by rememberSaveable {mutableStateOf("")}
        val nn = 30
        var dnegocio by rememberSaveable {mutableStateOf("")}
        val dn = 50
        //telefono validacion input
        var telefono by rememberSaveable {mutableStateOf("")}
        val contar = 10
        val contarsim = 20
        //cierre
        var password by rememberSaveable {mutableStateOf("")}
        var isPasswordVisible by rememberSaveable {mutableStateOf(false)}
        var confirmar by rememberSaveable {mutableStateOf("")}
        var isPasswordVisible1 by rememberSaveable {mutableStateOf(false)}


        Box(
            modifier = Modifier
                .width(470.dp)
        ) {
            Text(
                text = "Registrate",
                fontWeight = FontWeight.ExtraBold,
                color = Color(0xFF343341),
                fontSize = 60.sp

            )
        }

        Box(
            modifier = Modifier
                .width(470.dp)
        ) {
            Row(){
                Box{
                    Text(
                        text = "Hazlo ya que  esperas!",
                        color = Color(0xFF343341),
                        fontSize = 20.sp
                    )
                }
                Box(
                    modifier = Modifier
                        .padding(start = 220.dp)
                ){

                    Text(
                        text = "Ir a login",
                        color = Color.Blue,
                        fontSize = 15.sp,


                    )

                }



            }
        }


        Column(

            modifier = Modifier

                .padding(top = 20.dp)
                .background(color = Color(0xFFf5f5f5))
                .height(300.dp)
                .verticalScroll(rememberScrollState())


        ){


            //nombre
            Box(
                modifier = Modifier
                    .padding(top = 0.dp)
            ) {
                TextField(
                    value = text,
                    onValueChange = { newText -> text = newText },
                    label = { Text("Nombre usuario",fontSize = 10.sp) },

                    colors = TextFieldDefaults.textFieldColors(containerColor = Color.Transparent),
                    modifier = Modifier
                        .width(500.dp)
                        .height(80.dp)
                        .padding(15.dp)
                )
            }
            //apellido
            Box(
                modifier = Modifier
                    .padding(top = 0.dp)
            ) {
                TextField(
                    value = apellido,
                    onValueChange = { newText -> apellido = newText },
                    label = { Text("Apellido usuario",fontSize = 10.sp) },

                    colors = TextFieldDefaults.textFieldColors(containerColor = Color.Transparent),
                    modifier = Modifier
                        .width(500.dp)
                        .height(80.dp)
                        .padding(15.dp)
                )
            }
            //correo
            Box(
                modifier = Modifier
                    .padding(top = 0.dp)
            ) {
                TextField(
                    value = correo,
                    onValueChange = {
                        correo = it },
                    label = { Text("Correo",fontSize = 10.sp) },

                    colors = TextFieldDefaults.textFieldColors(containerColor = Color.Transparent),
                    modifier = Modifier
                        .width(500.dp)
                        .height(80.dp)
                        .padding(15.dp)
                )

            }
            //sim
            Box(
                modifier = Modifier
                    .padding(top = 0.dp)
            ) {
                TextField(
                    value = sim,
                    onValueChange = {
                            newInt ->
                        if (newInt.isEmpty() || newInt.length <= contarsim) {
                            if (newInt.isEmpty() || newInt.isDigitsOnly()) {
                                sim = newInt
                            }
                        }
                    },
                    label = { Text("ICCID de la licencia",fontSize = 10.sp) },
                    keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
                    colors = TextFieldDefaults.textFieldColors(containerColor = Color.Transparent),
                    modifier = Modifier
                        .width(500.dp)
                        .height(80.dp)
                        .padding(14.dp)
                )
                Text(
                    text = "${sim.length}/$contarsim",
                    color = if (sim.length < 20) Color.Red else Color.Unspecified,
                    modifier = Modifier
                        .padding(start = 445.dp, top = 75.dp)
                )
            }
            //telefono
            Box(
                modifier = Modifier
                    .padding(top = 0.dp)
            ) {
                TextField(
                    value = telefono,
                    //number
                    onValueChange = {
                            newText ->
                        if (newText.isEmpty() || newText.length <= contar) {
                            if (newText.isEmpty() || newText.isDigitsOnly()) {
                                telefono = newText
                            }
                        }
                    },

                    label = { Text("Telefono",fontSize = 10.sp) },
                    keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
                    //cierre
                    colors = TextFieldDefaults.textFieldColors(containerColor = Color.Transparent),
                    modifier = Modifier
                        .width(500.dp)
                        .height(80.dp)
                        .padding(14.dp)
                )
                //limite
                Text(
                    text = "${telefono.length}/$contar",
                    color = if (telefono.length < 10) Color.Red else Color.Unspecified,
                    modifier = Modifier
                        .padding(start = 445.dp,top = 75.dp)

                )
            }
            //negocio nombre
            Box(
                modifier = Modifier
                    .padding(top = 0.dp)
            ) {
                TextField(
                    value = nnegocio,
                    onValueChange = {
                        if (it.length <= nn) {
                            nnegocio = it
                        }
                    },
                    label = { Text("Nombre del negocio", fontSize = 10.sp) },
                    keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Text), // Cambiado a Text
                    colors = TextFieldDefaults.textFieldColors(containerColor = Color.Transparent),
                    modifier = Modifier
                        .width(500.dp)
                        .height(80.dp)
                        .padding(14.dp)
                )
                Text(
                    text = "${nnegocio.length}/$nn",
                    color = if (nnegocio.length < 6) Color.Red else Color.Unspecified,
                    modifier = Modifier
                        .padding(start = 445.dp,top = 75.dp)

                )
            }
            //Direcion negocio
            Box(
                modifier = Modifier
                    .padding(top = 0.dp)
            ) {
                TextField(
                    value = dnegocio,
                    onValueChange = {
                        if (it.length <= dn) {
                            dnegocio = it
                        }
                    },
                    label = { Text("Direccion del negocio", fontSize = 10.sp) },
                    keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Text), // Cambiado a Text
                    colors = TextFieldDefaults.textFieldColors(containerColor = Color.Transparent),
                    modifier = Modifier
                        .width(500.dp)
                        .height(80.dp)
                        .padding(14.dp)
                )

                Text(
                    text = "${dnegocio.length}/$dn",
                    color = if (dnegocio.length < 11) Color.Red else Color.Unspecified,
                    modifier = Modifier
                        .padding(start = 445.dp,top = 75.dp)

                )
            }

            //clave
            Box(
                modifier = Modifier
                    .padding(top = 0.dp)
            ) {
                TextField(
                    value = password,
                    onValueChange = {
                        password = it
                    },
                    label = { Text("Contraseña",fontSize = 10.sp) },
                    visualTransformation = if (isPasswordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                    keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Password),
                    colors = TextFieldDefaults.textFieldColors(containerColor = Color.Transparent),
                    modifier = Modifier
                        .width(500.dp)
                        .height(80.dp)
                        .padding(16.dp)
                )

                IconButton(
                    onClick  = { isPasswordVisible = !isPasswordVisible },
                    modifier = Modifier
                        .padding(start = 435.dp, top = 25.dp)
                ) {
                    Icon(
                        imageVector = if (isPasswordVisible) Icons.Filled.Visibility else Icons.Filled.VisibilityOff,
                        contentDescription = if (isPasswordVisible) "Ocultar contraseña" else "Mostrar contraseña"

                    )
                }

            }
            //confirmar clave
            Box(
                modifier = Modifier
                    .padding(top = 5.dp)
            ) {
                TextField(
                    value = confirmar,
                    onValueChange = { confirmar = it },
                    label = { Text("Confirmar Contraseña",fontSize = 10.sp) },

                    visualTransformation = if (isPasswordVisible1) VisualTransformation.None else PasswordVisualTransformation(),
                    keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Password),
                    colors = TextFieldDefaults.textFieldColors(containerColor = Color.Transparent),
                    modifier = Modifier
                        .width(500.dp)
                        .height(80.dp)
                        .padding(16.dp)
                )

                IconButton(
                    onClick = { isPasswordVisible1 = !isPasswordVisible1 },
                    modifier = Modifier
                        .padding(start = 435.dp, top = 25.dp)
                ) {
                    Icon(
                        imageVector = if (isPasswordVisible1) Icons.Filled.Visibility else Icons.Filled.VisibilityOff,
                        contentDescription = if (isPasswordVisible1) "Ocultar contraseña" else "Mostrar contraseña"

                    )
                }

            }



        }


        val ff = 5
        val f = 10

        Button(
            onClick = { validarr.validar(text, sim, apellido, correo, nnegocio, dnegocio,telefono, password, confirmar, context) },
            shape = RoundedCornerShape(25.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF343341),
                contentColor = Color.White,

                ),
            modifier = Modifier
                .width(500.dp)
                .height(100.dp)
                .padding(top = 30.dp)


        ) {
            Text("Register",
                fontSize = 24.sp)
        }


    }

}



fun onClick() {
    TODO("Not yet implemented")
}
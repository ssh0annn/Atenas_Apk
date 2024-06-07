package com.solidtype.atenas_apk_2.Authentication.actualizacion.presentation

import android.util.Patterns
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.solidtype.atenas_apk_2.R
import com.solidtype.atenas_apk_2.authentication.actualizacion.domain.TipoUser
import com.solidtype.atenas_apk_2.authentication.actualizacion.presentation.AuthEvent
import com.solidtype.atenas_apk_2.authentication.actualizacion.presentation.AuthViewmodel
import com.solidtype.atenas_apk_2.core.pantallas.Screens
import com.solidtype.atenas_apk_2.ui.theme.AzulGris
import com.solidtype.atenas_apk_2.ui.theme.Blanco
import com.solidtype.atenas_apk_2.ui.theme.BlancoOpaco
import com.solidtype.atenas_apk_2.ui.theme.Transparente


@Composable
fun LoginScreen(nav: NavController, viewModel: AuthViewmodel = hiltViewModel()) {

    val context = LocalContext.current

    val uiState by viewModel.uiStates.collectAsStateWithLifecycle()
    
    val email = rememberSaveable { mutableStateOf("") }
    val pass = rememberSaveable { mutableStateOf("") }

    //val corroutineScope = rememberCoroutineScope()

    if(!uiState.network)Toast.makeText(context, "No hay red", Toast.LENGTH_SHORT).show()

    if (uiState.isAutenticated != null) {
        when (uiState.isAutenticated!!.tipoUser){
            TipoUser.ADMIN -> {
                nav.navigate(Screens.Home.route) //Hay que pasar el correo al la navegación que le corresponda
                Toast.makeText(context, "Bienvenido Administrador!", Toast.LENGTH_SHORT).show()
            }
            TipoUser.TECNICO -> {
                nav.navigate(Screens.Home.route)
                Toast.makeText(context, "Bienvenido Técnico!", Toast.LENGTH_SHORT).show()
            }
            TipoUser.VENDEDOR -> {
                nav.navigate(Screens.Home.route)
                Toast.makeText(context, "Bienvenido Vendedor!", Toast.LENGTH_SHORT).show()
            }
            TipoUser.UNKNOWN -> {
                Toast.makeText(context, "Usuario Desconocido. Intente volver a logearse.", Toast.LENGTH_SHORT).show()
            }
        }
    }
    if (uiState.isLoading) {
        Box(
            Modifier.fillMaxSize()
        ) {
            CircularProgressIndicator(
                modifier = Modifier.align(Alignment.Center)
            )
        }

    } else
    {
        var checked by rememberSaveable { mutableStateOf(false) }
        var passwordVisible by rememberSaveable { mutableStateOf(false) }
        val icon = if (passwordVisible) painterResource(id = R.drawable.ic_visibility_false)
        else painterResource(id = R.drawable.ic_visibility_true)
        LazyColumn(
            Modifier
                .fillMaxHeight()
                .fillMaxWidth()
                .background(Blanco),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            item {
                Column(
                    modifier = Modifier
                        .width(500.dp)
                        .padding(start = 50.dp, top = 70.dp, bottom = 50.dp)
                )
                {
                    Text(
                        text = "Hola!",
                        fontSize = 50.sp,
                        fontWeight = FontWeight.Bold,
                        color = AzulGris
                    )
                    Text(
                        text = "Bienvenido",
                        fontSize = 50.sp,
                        fontWeight = FontWeight.Bold,
                        color = AzulGris
                    )
                    Text(
                        text = "Ingresa tus datos aqui.",
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold,
                        color = AzulGris
                    )
                }
                TextField(
                    value = email.value,
                    onValueChange = { email.value = it },
                    singleLine = true,
                    modifier = Modifier
                        .width(500.dp)
                        .height(66.dp)
                        .padding(start = 34.dp, end = 34.dp, top = 8.dp, bottom = 8.dp)
                        .background(Blanco),
                    shape = RoundedCornerShape(20),
                    label = { Text(text = "Username") },
                    textStyle = TextStyle(
                        textAlign = TextAlign.Start,
                        color = AzulGris,
                        fontSize = 14.sp
                    ),
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Filled.AccountCircle,
                            contentDescription = "Email Icon"
                        )
                    },
                    colors = TextFieldDefaults.colors(
                        focusedContainerColor = BlancoOpaco,
                        unfocusedContainerColor = BlancoOpaco,
                        disabledContainerColor = BlancoOpaco,
                        focusedIndicatorColor = Transparente,
                        unfocusedIndicatorColor = Transparente
                    ),
                )
                TextField(
                    value = pass.value,
                    onValueChange = { pass.value = it },
                    singleLine = true,
                    modifier = Modifier
                        .width(500.dp)
                        .height(66.dp)
                        .padding(start = 34.dp, end = 34.dp, top = 8.dp, bottom = 8.dp)
                        .background(Blanco),
                    shape = RoundedCornerShape(20),
                    label = { Text(text = "Password") },
                    textStyle = TextStyle(
                        textAlign = TextAlign.Start,
                        color = AzulGris,
                        fontSize = 14.sp
                    ),
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Filled.Lock,
                            contentDescription = "Username Icon"
                        )
                    },
                    trailingIcon = {
                        IconButton(onClick = {
                            passwordVisible = !passwordVisible
                        }) {
                            Image(
                                painter = icon,
                                contentDescription = "Visibility icon"
                            )
                        }
                    },
                    colors = TextFieldDefaults.colors(
                        focusedContainerColor = BlancoOpaco,
                        unfocusedContainerColor = BlancoOpaco,
                        disabledContainerColor = BlancoOpaco,
                        focusedIndicatorColor = Transparente,
                        unfocusedIndicatorColor = Transparente
                    ),
                    visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
                )
                Row(
                    modifier = Modifier
                        .width(500.dp)
                        .padding(start = 20.dp, top = 10.dp),
                    horizontalArrangement = Arrangement.Start,
                    verticalAlignment = Alignment.CenterVertically
                )
                {
                    Checkbox(
                        checked = checked,
                        onCheckedChange = { checked = it },
                        colors = CheckboxDefaults.colors(
                            checkedColor = AzulGris,
                        )
                    )
                    Text(
                        text = "Recordar",
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold,
                        color = AzulGris
                    )
                }
                Button(
                    onClick = {
                        viewModel.onEvent(AuthEvent.LoginEvent(email.value, pass.value))
                    },
                    shape = RoundedCornerShape(25.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = AzulGris,
                        contentColor = Blanco,

                        ),
                    modifier = Modifier
                        .width(430.dp)
                        .height(100.dp)
                        .padding(top = 30.dp),
                    enabled = Patterns.EMAIL_ADDRESS.matcher(email.value).matches() && pass.value.length >= 8
                ) {
                    Text(
                        "Sign in",
                        fontSize = 24.sp
                    )
                }
                Spacer(modifier = Modifier.height(10.dp))
                Row(
                    modifier = Modifier
                        .width(500.dp)
                        .padding(start = 20.dp, top = 10.dp),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "¿No tienes cuenta?",
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold,
                        color = AzulGris
                    )
                }
                Spacer(modifier = Modifier.height(100.dp))
            }
        }
    }
}
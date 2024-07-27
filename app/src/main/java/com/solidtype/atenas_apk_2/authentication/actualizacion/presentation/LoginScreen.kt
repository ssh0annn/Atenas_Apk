package com.solidtype.atenas_apk_2.authentication.actualizacion.presentation

import android.util.Patterns
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.CreditCard
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
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
import com.solidtype.atenas_apk_2.authentication.actualizacion.presentation.components.DialogoDipositivo
import com.solidtype.atenas_apk_2.authentication.actualizacion.presentation.components.DialogoForget
import com.solidtype.atenas_apk_2.core.pantallas.Screens
import com.solidtype.atenas_apk_2.ui.theme.AzulGris
import com.solidtype.atenas_apk_2.ui.theme.Blanco
import com.solidtype.atenas_apk_2.ui.theme.BlancoOpaco
import com.solidtype.atenas_apk_2.ui.theme.RojoPalido
import com.solidtype.atenas_apk_2.ui.theme.Transparente
import com.solidtype.atenas_apk_2.ui.theme.VerdePalido
import com.solidtype.atenas_apk_2.util.ui.components.Loading

object TipoUserSingleton {
    var tipoUser: TipoUser? = null
}

@Composable
fun LoginScreen(navController: NavController, viewModel: AuthViewmodel = hiltViewModel()) {

    val context = LocalContext.current

    val uiState by viewModel.uiStates.collectAsStateWithLifecycle()

    val email =
        rememberSaveable { mutableStateOf(if (!uiState.correoGuardado.isNullOrEmpty()) uiState.correoGuardado!! else "") }
    val pass = rememberSaveable { mutableStateOf("") }
    val licencia = rememberSaveable { mutableStateOf("") }

    val checked = rememberSaveable { mutableStateOf(!uiState.correoGuardado.isNullOrEmpty()) }
    val passwordVisible = rememberSaveable { mutableStateOf(false) }

    val mostrarForget = rememberSaveable { mutableStateOf(false) }


    val noHoverInteractionSource = remember { MutableInteractionSource() }

    val icon = if (passwordVisible.value) painterResource(id = R.drawable.ic_visibility_false)
    else painterResource(id = R.drawable.ic_visibility_true)

    if (!uiState.razones.isNullOrEmpty()) {
        Toast.makeText(context, "${uiState.razones}", Toast.LENGTH_LONG).show()
        viewModel.limpiaRazones()
    }

    if (uiState.isAutenticated != null) {
        TipoUserSingleton.tipoUser = uiState.isAutenticated!!.tipoUser
        when (uiState.isAutenticated!!.tipoUser) {
            TipoUser.ADMIN -> navController.navigate(Screens.PerfilAdmin.route)
            TipoUser.TECNICO, TipoUser.VENDEDOR -> navController.navigate(Screens.Ventas.route)
            else -> {}
        }
    }
    Column(
        Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .background(Blanco),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
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
                .background(Blanco)
                .border(
                    2.dp,
                        if(email.value.isEmpty()) Transparente
                        else
                            if (
                                Patterns.EMAIL_ADDRESS.matcher(email.value).matches()
                            ) VerdePalido
                            else RojoPalido,
                    RoundedCornerShape(20.dp)
                )
            ,
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
                .background(Blanco)
                .border(
                    2.dp,
                    if(pass.value.isEmpty()) Transparente
                    else
                        if (
                            pass.value.length >= 8
                        ) VerdePalido
                        else RojoPalido,
                    RoundedCornerShape(20.dp)
                )
            ,
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
                    passwordVisible.value = !passwordVisible.value
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
            visualTransformation = if (passwordVisible.value) VisualTransformation.None else PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
        )
        if (!uiState.licenciaGuardada)
            TextField(
                value = licencia.value,
                onValueChange = { licencia.value = it },
                singleLine = true,
                modifier = Modifier
                    .width(500.dp)
                    .height(66.dp)
                    .padding(start = 34.dp, end = 34.dp, top = 8.dp, bottom = 8.dp)
                    .background(Blanco)
                    .border(
                        2.dp,
                        if(licencia.value.isEmpty()) Transparente
                        else
                            if (
                                licencia.value.isNotEmpty()
                            ) VerdePalido
                            else RojoPalido,
                        RoundedCornerShape(20.dp)
                    )
                ,
                shape = RoundedCornerShape(20),
                label = { Text(text = "Licencia") },
                textStyle = TextStyle(
                    textAlign = TextAlign.Start,
                    color = AzulGris,
                    fontSize = 14.sp
                ),
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.CreditCard,
                        contentDescription = "License Icon"
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
        Row(
            modifier = Modifier
                .width(500.dp)
                .padding(start = 20.dp, top = 10.dp),
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Checkbox(
                checked = checked.value,
                onCheckedChange = { checked.value = it },
                colors = CheckboxDefaults.colors(
                    checkedColor = AzulGris,
                )
            )
            Text(
                text = "Recuérdame",
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
                color = AzulGris,
                modifier = Modifier
                    .clickable(
                        interactionSource = noHoverInteractionSource,
                        indication = null
                    ) {
                        checked.value = !checked.value
                    }
            )
        }
        Button(
            onClick = {
                when (uiState.licenciaGuardada) {
                    true -> viewModel.onEvent(AuthEvent.LoginEvent(email.value, pass.value))
                    false -> viewModel.onEvent(
                        AuthEvent.LoginEvent(
                            email.value,
                            pass.value,
                            licencia.value
                        )
                    )
                }
                if (checked.value) viewModel.onEvent(AuthEvent.Recuerdame(email.value))
                else viewModel.onEvent(AuthEvent.EliminarRecuerdos)
            },
            shape = RoundedCornerShape(25.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = AzulGris,
                contentColor = Blanco
            ),
            modifier = Modifier
                .width(430.dp)
                .height(100.dp)
                .padding(top = 30.dp),
            enabled =
                    Patterns.EMAIL_ADDRESS.matcher(email.value).matches() &&
                    pass.value.length >= 8 &&
                    !uiState.isLoading &&
                    (
                        uiState.licenciaGuardada ||
                        licencia.value.isNotEmpty()
                    ) // ~(~p ^ q) = p v ~q
        ) {
            Text(
                "Iniciar Sesión",
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
                text = "¿Olvidó la contraseña?",
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
                color = AzulGris,
                modifier = Modifier
                    .clickable {
                        mostrarForget.value = true
                    }
            )
        }
        if (uiState.isLoading) Loading()
        Spacer(modifier = Modifier.height(16.dp))
    }
    DialogoDipositivo(
        mostrar = !uiState.dispositivo,
        aceptar = { viewModel.onEvent(AuthEvent.RegistrarNuevoDevice) },
        cancelar = { viewModel.onEvent(AuthEvent.CancelarRegistro) }
    )
    DialogoForget(
        mostrar = mostrarForget,
        email = email,
        aceptar = {
            viewModel.onEvent(AuthEvent.ForgetPassword(email.value))
            mostrarForget.value = false
        },
        cancelar = { mostrarForget.value = false }
    )
}
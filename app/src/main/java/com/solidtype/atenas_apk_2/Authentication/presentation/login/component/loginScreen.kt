package com.solidtype.atenas_apk_2.Authentication.presentation.login.component

import android.content.Context
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.solidtype.atenas_apk_2.R
import com.solidtype.atenas_apk_2.Authentication.presentation.login.LoginViewModel
import androidx.compose.runtime.livedata.observeAsState
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.solidtype.atenas_apk_2.core.pantallas.Screens

@Composable
fun Container(context: Context, nav: NavController, viewModel: LoginViewModel = hiltViewModel()) {
    val email: String by viewModel.mail.observeAsState(initial = "")
    val pass: String by viewModel.pass.observeAsState(initial = "")
    val loginEnabled: Boolean by viewModel.login.observeAsState(initial = false)
    val isLoading: Boolean by viewModel.isLoading.observeAsState(initial = false)
    val autenticado = viewModel.logeado.value
    val verificado:Boolean by viewModel.verificado.observeAsState(initial = false)

    //val corroutineScope = rememberCoroutineScope()

    if (verificado) {
        Toast.makeText(context, "if: verificado: ${verificado}, auten: ${autenticado.autenticado}", Toast.LENGTH_SHORT).show()
        nav.navigate(Screens.Home.route)
    } else {
        Toast.makeText(context, "Else: verificado: ${verificado}, auten: ${autenticado.autenticado}", Toast.LENGTH_SHORT).show()

        if (isLoading) {
            Box(
                Modifier.fillMaxSize()
            ) {
                CircularProgressIndicator(
                    modifier = Modifier.align(Alignment.Center)
                )
            }

        } else {
            var checked by rememberSaveable { mutableStateOf(false) }
            var passwordVisible by rememberSaveable { mutableStateOf(false) }
            val icon = if (passwordVisible) painterResource(id = R.drawable.ic_visibility_false)
            else painterResource(id = R.drawable.ic_visibility_true)
            LazyColumn(
                Modifier
                    .fillMaxHeight()
                    .fillMaxWidth()
                    .background(
                        color = Color(
                            android.graphics.Color.parseColor("#ffffff")
                        )
                    ),
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
                            color = Color(android.graphics.Color.parseColor("#343341"))
                        )
                        Text(
                            text = "Bienvenido",
                            fontSize = 50.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color(android.graphics.Color.parseColor("#343341"))
                        )
                        Text(
                            text = "Ingresa tus datos aqui.",
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color(android.graphics.Color.parseColor("#343341"))
                        )
                    }

                    val containerColor = Color(android.graphics.Color.parseColor("#F0F0F0"))
                    TextField(
                        value = email,
                        onValueChange = { viewModel.onLoginChange(it, pass) },
                        singleLine = true,
                        modifier = Modifier
                            .width(500.dp)
                            .height(66.dp)
                            .padding(start = 34.dp, end = 34.dp, top = 8.dp, bottom = 8.dp)
                            .background(Color(android.graphics.Color.parseColor("#FFFFFF"))),
                        shape = RoundedCornerShape(20),
                        label = { Text(text = "Username") },
                        textStyle = TextStyle(
                            textAlign = TextAlign.Start,
                            color = Color(android.graphics.Color.parseColor("#343341")),
                            fontSize = 14.sp
                        ),
                        leadingIcon = {
                            Icon(
                                imageVector = Icons.Filled.AccountCircle,
                                contentDescription = "Email Icon"
                            )
                        },
                        colors = TextFieldDefaults.colors(
                            focusedContainerColor = containerColor,
                            unfocusedContainerColor = containerColor,
                            disabledContainerColor = containerColor,
                            focusedIndicatorColor = Color.Transparent,
                            unfocusedIndicatorColor = Color.Transparent,
                        ),
                    )
                    val containerColor1 = Color(android.graphics.Color.parseColor("#F0F0F0"))
                    TextField(
                        value = pass,
                        onValueChange = { viewModel.onLoginChange(email, it) },
                        singleLine = true,
                        modifier = Modifier
                            .width(500.dp)
                            .height(66.dp)
                            .padding(start = 34.dp, end = 34.dp, top = 8.dp, bottom = 8.dp)
                            .background(Color(android.graphics.Color.parseColor("#FFFFFF"))),
                        shape = RoundedCornerShape(20),
                        label = { Text(text = "Password") },
                        textStyle = TextStyle(
                            textAlign = TextAlign.Start,
                            color = Color(android.graphics.Color.parseColor("#343341")),
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
                            focusedContainerColor = containerColor1,
                            unfocusedContainerColor = containerColor1,
                            disabledContainerColor = containerColor1,
                            focusedIndicatorColor = Color.Transparent,
                            unfocusedIndicatorColor = Color.Transparent,
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
                            onCheckedChange = { checked = it }
                        )
                        Text(
                            text = "Recordar",
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color(android.graphics.Color.parseColor("#343341"))
                        )
                    }
                    Button(
                        onClick = {
                            viewModel.onLoginSelected(context)
                                                   },
                        shape = RoundedCornerShape(25.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(0xFF343341),
                            contentColor = Color.White,

                            ),
                        modifier = Modifier
                            .width(430.dp)
                            .height(100.dp)
                            .padding(top = 30.dp),
                        enabled = loginEnabled
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
                            color = Color(android.graphics.Color.parseColor("#343341"))
                        )
                        Button(
                            onClick = {
                                nav.navigate(Screens.Register.route)

                                                            },
                            shape = RoundedCornerShape(25.dp),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = Color.Transparent,
                                contentColor = Color(0xFF343341),
                            ),
                            modifier = Modifier
                                .width(180.dp)
                                .height(80.dp)
                                .padding(start = 10.dp)

                        ) {
                            Text(
                                "Registrate",
                                fontSize = 20.sp
                            )
                        }
                    }
                    Spacer(modifier = Modifier.height(100.dp))
                }
            }
        }
    }
}


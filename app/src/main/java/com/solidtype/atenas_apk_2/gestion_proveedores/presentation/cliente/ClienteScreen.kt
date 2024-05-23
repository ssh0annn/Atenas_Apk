package com.solidtype.atenas_apk_2.gestion_proveedores.presentation.cliente

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle

@Composable
fun ClienteScreen(
    viewModel: ClientesViewModel = hiltViewModel(),
    clienteEvent: ClienteEvent
){

    val context = LocalContext.current
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    var busqueda by rememberSaveable { mutableStateOf("") }
    var mostrar by rememberSaveable { mutableStateOf(false) }

  






}
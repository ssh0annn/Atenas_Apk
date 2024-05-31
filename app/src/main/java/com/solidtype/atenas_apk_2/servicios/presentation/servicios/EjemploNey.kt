package com.solidtype.atenas_apk_2.servicios.presentation.servicios

import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle

@Composable
fun EjemploNey(viewModel: ServiciosViewModel = hiltViewModel()){
    val state by viewModel.uiStates.collectAsStateWithLifecycle()


    if(state.listaTickets.isNotEmpty()){
        println("No esta bacio : ${state.listaTickets} <--")
    }else{
        println("Si esta bacio : ${state.listaTickets}  <---")
    }

   Button(onClick = { /*TODO*/ }) {
       Text(text = "No se")
       
   }

}
package com.solidtype.atenas_apk_2.gestion_usuarios.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.motionEventSpy
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.solidtype.atenas_apk_2.gestion_usuarios.domain.modelo.usuario
import org.apache.poi.sl.usermodel.VerticalAlignment


@Composable
fun EjemploDeEvento(viewmodel: UsuariosViewmodel = hiltViewModel()){

    val uiState by viewmodel.uiState.collectAsStateWithLifecycle()
  Column(modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.SpaceBetween) {
      Button(onClick = {
          viewmodel.onUserEvent(UserEvent.GetRoles)
      }) {
          println("Roles : ${uiState.roles}")
      }
      Button(onClick = {
          uiState.rolSelecionado?.let { rol ->
              viewmodel.onUserEvent(
                  UserEvent.AgregarUsuario(usuario(
                      id_roll_usuario = rol.id_roll_usuario,
                      nombre = "Pepito",
                      apellido = "Perez",
                      email = "Quien sabe",
                      clave = "Paque",
                      telefono ="8383838383",
                      estado = true
                  ))
              )
          }

      }) {

      }
      Button(onClick = {
          viewmodel.onUserEvent(UserEvent.GetRoles)
      }) {
          println("Roles : ${uiState.roles}")
      }
      Button(onClick = {
          viewmodel.onUserEvent(UserEvent.GetRoles)
      }) {
          println("Roles : ${uiState.roles}")
      }
  }


}
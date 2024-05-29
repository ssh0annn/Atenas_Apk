package com.solidtype.atenas_apk_2.gestion_usuarios.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.solidtype.atenas_apk_2.gestion_usuarios.domain.modelo.roll_usuarios
import com.solidtype.atenas_apk_2.gestion_usuarios.domain.modelo.usuario
import com.solidtype.atenas_apk_2.gestion_usuarios.domain.use_cases.UsuarioUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class UsuariosViewmodel @Inject constructor(private val casos: UsuarioUseCases) : ViewModel() {

    var uiState: MutableStateFlow<UserStatesUI> = MutableStateFlow(UserStatesUI())
        private set

    private var recentlyDelete: usuario? = null
    private var userJob: Job? = null

    init {
        getUsuarios()
    }

    fun onUserEvent(evento: UserEvent) {
        when (evento) {
            is UserEvent.AgregarNuevoRol -> {
                agregarRol(evento.rol)

            }
            is UserEvent.EditarRol -> {
                editarRol(evento.rol)

            }
            is UserEvent.MostrarUserEvent -> {
                getUsuarios()
            }

            is UserEvent.BuscarUsuario -> {
                if (evento.any.isNotEmpty()) {
                    buscarUsuarios(evento.any)
                } else {
                    getUsuarios()
                }
            }

            is UserEvent.BorrarUsuario -> {
                borrarUsuario(evento.usuario)
            }

            is UserEvent.RestaurarUsuario -> {
                restaurarUsuario()
            }

            is UserEvent.AgregarUsuario -> {
                AgregarUsuario(evento.usuario)

            }

            is UserEvent.EditarUsuario -> {
                EditarUsuario(evento.usuario)
            }

           is UserEvent.GetRoles -> {
               getRoles()
           }
            is UserEvent.RolSelecionado -> {
                rolSelecionado(evento.rol)
            }
        }
    }

    private fun agregarRol(rol:roll_usuarios){
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                casos.crearRoles(rol)
            }
        }
    }
    private fun editarRol(rol:roll_usuarios){
        casos
            }
   private fun rolSelecionado(rol : roll_usuarios){
       uiState.update { it.copy(rolSelecionado = rol) }
   }
    private fun AgregarUsuario(usuario: usuario) {
        viewModelScope.launch {
            casos.agregar(usuario = usuario)
        }
    }

    private fun EditarUsuario(usuario:usuario) {
        viewModelScope.launch {
            casos.actualizar(usuario = usuario)
        }
    }

    private fun borrarUsuario(usuario: usuario) {
        viewModelScope.launch {
            casos.eliminar(usuario)
            recentlyDelete = usuario
        }
    }

    private fun restaurarUsuario() {
        viewModelScope.launch {
            recentlyDelete?.let { casos.agregar(it) }
            recentlyDelete = null
        }
    }

    private fun getUsuarios() {
        userJob?.cancel()
        userJob = casos.mostrarUsuarios().onEach { users ->
            uiState.update { it.copy(usuarios = users) }

        }.launchIn(viewModelScope)
    }

    private fun buscarUsuarios(any: String) {

        userJob?.cancel()
        userJob = casos.buscarUsuario(any).onEach { users ->
            uiState.update { it.copy(usuarios = users) }

        }.launchIn(viewModelScope)

    }
    private fun getRoles() {
        viewModelScope.launch {
            casos.getRoles().collect{ roles ->
                uiState.update { it.copy(roles = roles) }
            }
        }
    }

}
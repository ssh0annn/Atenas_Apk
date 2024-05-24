package com.solidtype.atenas_apk_2.gestion_proveedores.presentation.cliente

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.solidtype.atenas_apk_2.gestion_proveedores.domain.casos_usos.casos_cliente.CasosClientes
import com.solidtype.atenas_apk_2.gestion_usuarios.presentation.UserEvent
import com.solidtype.atenas_apk_2.gestion_usuarios.presentation.UserStatesUI
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject
@HiltViewModel
class ClientesViewModel @Inject constructor(private val casos: CasosClientes) : ViewModel() {

    var uiState: MutableStateFlow<UserStatesUI> = MutableStateFlow(UserStatesUI())
        private set

    private var recentlyDelete: Map<String, Any?> = emptyMap()

    private var userJob: Job? = null

    init {
        getUsuarios()
    }

    fun onUserEvent(evento: ClienteEvent) {
        when (evento) {
            is ClienteEvent.MostrarClientesEvent -> {
                getUsuarios()
            }

            is ClienteEvent.BuscarClientes -> {
                if (evento.any.isNotEmpty()) {
                    buscarUsuarios(evento.any)
                } else {
                    getUsuarios()
                }
            }

            is ClienteEvent.BorrarClientes -> {
                borrarUsuario(evento.clientes)
            }

            is ClienteEvent.RestaurarClientes -> {
                restaurarUsuario()
            }

            is ClienteEvent.AgregarClientes -> {
                AgregarUsuario(evento.clientes)

            }

            is ClienteEvent.EditarClientes -> {
                EditarUsuario(evento.clientes)
            }

        }
    }

    private fun AgregarUsuario(usuario: Map<String, Any?>) {
        viewModelScope.launch {
            casos.crearClientes(usuario)
        }
    }

    private fun EditarUsuario(usuario: Map<String, Any?>) {
        viewModelScope.launch {
            // casos.actualizar(usuario = usuario)
        }
    }

    private fun borrarUsuario(usuario: Map<String, Any?>) {
        viewModelScope.launch {
            casos.eliminarPersona(usuario)
            recentlyDelete = usuario
        }
    }

    private fun restaurarUsuario() {
        viewModelScope.launch {
            if (recentlyDelete.isNotEmpty()) {
                casos.crearClientes(recentlyDelete)
                recentlyDelete = emptyMap()
            }
        }
    }

    private fun getUsuarios() {
        userJob?.cancel()
        userJob = casos.getClientes().onEach { users ->
            uiState.update { it.copy(usuarios = users) }

        }.launchIn(viewModelScope)
    }

    private fun buscarUsuarios(any: String) {

        userJob?.cancel()
        userJob = casos.buscarClientes(any).onEach { users ->
            uiState.update { it.copy(usuarios = users) }
        }.launchIn(viewModelScope)

    }

}
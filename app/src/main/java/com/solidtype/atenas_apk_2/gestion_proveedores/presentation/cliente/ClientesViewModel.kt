package com.solidtype.atenas_apk_2.gestion_proveedores.presentation.cliente

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.solidtype.atenas_apk_2.gestion_proveedores.domain.casos_usos.casos_cliente.CasosClientes
import com.solidtype.atenas_apk_2.gestion_proveedores.presentation.cliente.modelo.Personastodas
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

    var uiState: MutableStateFlow<ClienteStateUI> = MutableStateFlow(ClienteStateUI())
        private set

    private var recentlyDelete: Personastodas.ClienteUI? = null

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

    private fun AgregarUsuario(cliente: Personastodas.ClienteUI) {
        viewModelScope.launch {
            casos.crearClientes(cliente)
        }
    }

    private fun EditarUsuario(usuario: Personastodas.ClienteUI) {
        viewModelScope.launch {
            // casos.actualizar(usuario = usuario)
        }
    }

    private fun borrarUsuario(usuario: Personastodas.ClienteUI) {
        viewModelScope.launch {
            casos.eliminarPersona(usuario)
            recentlyDelete = usuario
        }
    }

    private fun restaurarUsuario() {
        viewModelScope.launch {
            recentlyDelete?.let { casos.crearClientes(it) }
            recentlyDelete = null

        }
    }

    private fun getUsuarios() {
        userJob?.cancel()
        userJob = casos.getClientes().onEach { users ->
            uiState.update { it.copy(clientes = users) }

        }.launchIn(viewModelScope)
    }

    private fun buscarUsuarios(any: String) {

        userJob?.cancel()
        userJob = casos.buscarClientes(any).onEach { users ->
            uiState.update { it.copy(clientes = users) }
        }.launchIn(viewModelScope)

    }

}
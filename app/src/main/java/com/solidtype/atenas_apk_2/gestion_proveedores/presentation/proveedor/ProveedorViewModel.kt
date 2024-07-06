package com.solidtype.atenas_apk_2.gestion_proveedores.presentation.proveedor

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.solidtype.atenas_apk_2.gestion_proveedores.domain.casos_usos.casos_proveedores.CasosProveedores
import com.solidtype.atenas_apk_2.gestion_proveedores.presentation.cliente.modelo.Personastodas
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
class ProveedorViewModel @Inject constructor(private val casos: CasosProveedores) : ViewModel() {

    var uiState: MutableStateFlow<ProveedorStatesUI> = MutableStateFlow(ProveedorStatesUI())
        private set

    private var recentlyDelete: Personastodas.Proveedor? = null
    private var userJob: Job? = null
    private var switch:Boolean = uiState.value.switch

    init {
        getUsuarios()
    }

    fun onUserEvent(evento: ProveedorEvent) {
        when (evento) {
            is ProveedorEvent.MostrarProveedorEvent -> {
                getUsuarios()
            }

            is ProveedorEvent.BuscarProveedor -> {
                if (evento.any.isNotEmpty()) {
                    buscarUsuarios(evento.any)
                } else {
                    getUsuarios()
                }
            }

            is ProveedorEvent.BorrarProveedor -> {
                borrarUsuario(evento.proveedors)
            }

            is ProveedorEvent.RestaurarProveedor -> {
                restaurarUsuario()
            }

            is ProveedorEvent.AgregarProveedor -> {
                AgregarUsuario(evento.proveedors)

            }

            is ProveedorEvent.EditarProveedor -> {
                EditarUsuario(evento.proveedors)
            }

            ProveedorEvent.Switch -> {
                uiState.update { it.copy(switch = !switch) }
            }
        }
    }

    private fun AgregarUsuario(usuario: Personastodas.Proveedor) {
        viewModelScope.launch {
            casos.crearProveedor(proveedor = usuario)
        }
    }

    private fun EditarUsuario(usuario: Personastodas.Proveedor) {
        viewModelScope.launch {
            casos.editarProveedores(proveedor = usuario)
        }
    }

    private fun borrarUsuario(usuario: Personastodas.Proveedor) {
        viewModelScope.launch {
            casos.eliminarPersona(usuario)
            recentlyDelete = usuario
        }
    }

    private fun restaurarUsuario() {
        viewModelScope.launch {
            recentlyDelete?.let { casos.crearProveedor(it)}
            recentlyDelete = null

        }
    }

    private fun getUsuarios() {
        userJob?.cancel()
        userJob = casos.getProveedores(switch).onEach { users ->
            uiState.update { it.copy(proveedores = users) }

        }.launchIn(viewModelScope)
    }

    private fun buscarUsuarios(any: String) {

        userJob?.cancel()
        userJob = casos.buscarProveedores(any, switch).onEach { users ->
            uiState.update { it.copy(proveedores = users) }
        }.launchIn(viewModelScope)

    }

}
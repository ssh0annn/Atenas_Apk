package com.solidtype.atenas_apk_2.gestion_usuarios.presentation

import android.annotation.SuppressLint
import android.content.Context
import android.provider.Settings
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.solidtype.atenas_apk_2.gestion_usuarios.domain.modelo.roll_usuarios
import com.solidtype.atenas_apk_2.gestion_usuarios.domain.modelo.usuario
import com.solidtype.atenas_apk_2.gestion_usuarios.domain.use_cases.EliminarRoll
import com.solidtype.atenas_apk_2.gestion_usuarios.domain.use_cases.UsuarioUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
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
class UsuariosViewmodel @Inject constructor(private val casos: UsuarioUseCases
,
    @ApplicationContext private val context: Context) : ViewModel() {

    var uiState: MutableStateFlow<UserStatesUI> = MutableStateFlow(UserStatesUI())
        private set

    private var recentlyDelete: usuario? = null
    private var userJob: Job? = null

    init {
        getUsuarios()
        uiState.update { it.copy(qr = getDeviceId())}
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
                agregarUsuario(evento.usuario)

            }

            is UserEvent.EditarUsuario -> {
                editarUsuario(evento.usuario)
            }

           is UserEvent.GetRoles -> {
               getRoles()
           }
            is UserEvent.RolSelecionado -> {
                rolSelecionado(evento.rol)
            }

            is UserEvent.ElimnarRoll -> {
                elimanarRoll(evento.rol)

            }

            UserEvent.GetQr -> {
                uiState.update { it.copy(qr = getDeviceId()) }
            }
        }
    }
    @SuppressLint("HardwareIds")
    private fun getDeviceId(): String? {
        val deviceId: String? = Settings.Secure.getString(
            context.contentResolver,
            Settings.Secure.ANDROID_ID
        )

        return deviceId
    }
    private fun elimanarRoll(roll: roll_usuarios){
        viewModelScope.launch {
            try{
                casos.eliminarRol(roll)
            }catch (_ : Exception){
                uiState.update { it.copy(error = "No es posible elminar este rol") }
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
    private fun editarRol(rol:roll_usuarios) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                casos.actualizarRoll(rol)
            }
        }
    }
   private fun rolSelecionado(rol : roll_usuarios){
       uiState.update { it.copy(rolSelecionado = rol) }
   }
    private fun agregarUsuario(usuario: usuario) {
        viewModelScope.launch {
            casos.agregar(usuario = usuario)
        }
    }

    private fun editarUsuario(usuario:usuario) {
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
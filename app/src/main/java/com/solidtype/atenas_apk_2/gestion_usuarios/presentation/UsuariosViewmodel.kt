package com.solidtype.atenas_apk_2.gestion_usuarios.presentation.usuarios

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.solidtype.atenas_apk_2.gestion_usuarios.domain.use_cases.UsuarioUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject
@HiltViewModel
class UsuariosViewmodel @Inject constructor(private val casos: UsuarioUseCases) :ViewModel(){

    var uiState: MutableStateFlow<UserStatesUI> = MutableStateFlow(UserStatesUI())
        private set

    private var recentlyDelete: Map<String, Any?> = emptyMap()

    private var userJob: Job? = null
    init {
        getUsuarios()
    }
    fun onUserEvent(evento: UserEvent){
        when(evento){
            is UserEvent.MostrarUserEvent -> {
                getUsuarios()
            }
            is UserEvent.BuscarUsuario -> {
                if(evento.any.isNotEmpty()){
                    buscarUsuarios(evento.any)
                }else{
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

            else -> {

            }
        }
    }

    private fun AgregarUsuario(usuario:Map<String, Any?>) {
        viewModelScope.launch {
            casos.agregar(usuario = usuario)
        }
    }

    private fun EditarUsuario(usuario:Map<String, Any?>) {
        viewModelScope.launch {
            casos.actualizar(usuario = usuario)
        }
    }
    private fun borrarUsuario(usuario:Map<String, Any?>){
        viewModelScope.launch { casos.eliminar(usuario)
            recentlyDelete = usuario
        }
    }

    private fun restaurarUsuario(){
        viewModelScope.launch {
            if(recentlyDelete.isNotEmpty()){
                casos.agregar(recentlyDelete)
                recentlyDelete = emptyMap()
            }
        }
    }
    private fun getUsuarios(){
        userJob?.cancel()
        userJob = casos.mostrarUsuarios().onEach { users ->
            uiState.update { it.copy(usuarios = users) }

        }.launchIn(viewModelScope)
    }

    private fun buscarUsuarios(any:String){

        userJob?.cancel()
        userJob =casos.buscarUsuario(any).onEach { users ->
            uiState.update { it.copy(usuarios = users) }

        }.launchIn(viewModelScope)

    }

}
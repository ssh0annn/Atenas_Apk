package com.solidtype.atenas_apk_2.dispositivos.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.solidtype.atenas_apk_2.authentication.actualizacion.domain.casos_usos.AuthCasos
import com.solidtype.atenas_apk_2.dispositivos.model.Dispositivo
import com.solidtype.atenas_apk_2.dispositivos.model.casos_usos.CasosDispositivo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.cancellable
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class DispositivosViewModel @Inject constructor(private val casos:CasosDispositivo,
) : ViewModel() {

    var uiState:MutableStateFlow<DispositivoUiState> = MutableStateFlow(DispositivoUiState())
        private set

    private var job: Job? = null
    var restore:Dispositivo? = null

    fun onEvent(evento: DispositivosEvent){
        when(evento) {
            is DispositivosEvent.GetDevice -> {
                getDispositivos()
            }
            is DispositivosEvent.Delete -> {
                restore = evento.device
                deleteDevice(evento.device)
            }
            is DispositivosEvent.Search -> {
                search(evento.device)
            }
            is DispositivosEvent.UpdateDevice -> {
                updateDispositivo(evento.device)
            }
            is DispositivosEvent.AddDevice -> {
                addDevice(evento.device)
            }
            is DispositivosEvent.RestoreOnDelete -> {
                restore?.let { addDevice(it) }
                restore = null
            }
        }
    }

    private fun addDevice(device:Dispositivo){
        viewModelScope.launch {
            withContext(Dispatchers.IO){
                casos.agregarDispositivo(device)
            }
        }

    }
    private fun deleteDevice(device:Dispositivo){
        viewModelScope.launch {
            withContext(Dispatchers.IO){
                casos.eliminar(device)
            }
        }

    }
    private fun updateDispositivo(device:Dispositivo){
        viewModelScope.launch {
            withContext(Dispatchers.IO){
                casos.actualizar(device)
            }
        }

    }
    private fun getDispositivos(){
        job?.cancel()
        job =  viewModelScope.launch {
            uiState.update { it.copy(isLoading = true) }
            withContext(Dispatchers.IO){
                casos.getDispositivos().collect{ listaDispositivo ->
                    uiState.update { it.copy(dispositivos = listaDispositivo, isLoading = false)
                    }}

            }
        }

    }
    private fun search(any:String){
        job?.cancel()
        job =  viewModelScope.launch {
            uiState.update { it.copy(isLoading = true) }
            withContext(Dispatchers.IO){
                casos.buscarDispositivos(any).collect{ listaDispositivo ->
                    uiState.update { it.copy(dispositivos = listaDispositivo, isLoading = false)
                    }}
            }
        }

    }

}
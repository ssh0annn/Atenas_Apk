package com.solidtype.atenas_apk_2.dispositivos.presentation

import com.solidtype.atenas_apk_2.dispositivos.model.Dispositivo

sealed class DispositivosEvent {
    data class  AddDevice(val device: Dispositivo) : DispositivosEvent()
    data class  UpdateDevice(val device: Dispositivo) : DispositivosEvent()
    data class  Delete(val device: Dispositivo) : DispositivosEvent()
    data class  Search(val device: String) : DispositivosEvent()
    object GetDevice: DispositivosEvent()

    object RestoreOnDelete:DispositivosEvent()

}
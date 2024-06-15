package com.solidtype.atenas_apk_2.servicios.modelo.casos_usos.manage_dispositivos

import com.solidtype.atenas_apk_2.dispositivos.model.Dispositivo
import com.solidtype.atenas_apk_2.dispositivos.model.casos_usos.CasosDispositivo
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class DispositivosManger @Inject constructor(private val casosDispositivo: CasosDispositivo) {

    fun getDispositivos(): Flow<List<Dispositivo>> {
        return casosDispositivo.getDispositivos()
    }
    suspend fun crearDispositivo(dispositivo: Dispositivo) {
        dispositivo.id_dispositivo = System.currentTimeMillis()
        casosDispositivo.agregarDispositivo(dispositivo)
    }
    fun buscarDispositivos(any:String):Flow<List<Dispositivo>> {
       return casosDispositivo.buscarDispositivos(any)
    }

}
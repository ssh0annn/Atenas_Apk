package com.solidtype.atenas_apk_2.dispositivos.model.repository

import com.solidtype.atenas_apk_2.dispositivos.model.Dispositivo
import kotlinx.coroutines.flow.Flow
interface DispositivosRepository {
    fun getDispositivos(): Flow<List<Dispositivo>>
    fun buscarDispositivoCaracter(any: String):Flow<List<Dispositivo>>
    suspend fun agregarDispositivo(dispositivo:Dispositivo)
    suspend fun updateDispositivo(dispositivo: Dispositivo)
    suspend fun eliminarDispositivo(dispositivo:Dispositivo)
}
package com.solidtype.atenas_apk_2.servicios.modelo.repository

import com.solidtype.atenas_apk_2.servicios.modelo.servicio
import kotlinx.coroutines.flow.Flow
interface ServicioRepository {
    fun getTiposServicios(): Flow<List<servicio>>
    fun buscarTiposServicios(any:String):Flow<List<servicio>>
    suspend fun updateTiposServicios(tipoServicio: servicio)
    suspend fun agregarTipoServicio(tipoServicio: servicio)
    suspend fun eliminarTipoServicio(tipoServicio:servicio)
}
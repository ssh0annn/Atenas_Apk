package com.solidtype.atenas_apk_2.dispositivos.data.repository

import com.solidtype.atenas_apk_2.dispositivos.data.ddbb.DispositivoDao
import com.solidtype.atenas_apk_2.dispositivos.model.Dispositivo
import com.solidtype.atenas_apk_2.dispositivos.model.repository.DispositivosRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class DispositivosRepositoryImpl @Inject constructor(private val dao: DispositivoDao) : DispositivosRepository{
    override fun getDispositivos(): Flow<List<Dispositivo>> {
        return dao.getDispositivos()
    }

    override fun buscarDispositivoCaracter(any: String): Flow<List<Dispositivo>> {
        TODO("Not yet implemented")
    }

    override suspend fun agregarDispositivo(dispositivo: Dispositivo) {
        dao.addDispositivo(dispositivo)
    }

    override suspend fun updateDispositivo(dispositivo: Dispositivo) {
        dao.updateDispositivo(dispositivo)

    }

    override suspend fun eliminarDispositivo(dispositivo: Dispositivo) {
         dao.deleteDispositivo(dispositivo)
    }
}
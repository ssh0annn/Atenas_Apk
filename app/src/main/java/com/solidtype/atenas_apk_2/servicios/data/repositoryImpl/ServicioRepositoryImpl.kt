package com.solidtype.atenas_apk_2.servicios.data.repositoryImpl

import com.solidtype.atenas_apk_2.servicios.data.servicioDao
import com.solidtype.atenas_apk_2.servicios.modelo.repository.ServicioRepository
import com.solidtype.atenas_apk_2.servicios.modelo.servicio
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ServicioRepositoryImpl @Inject constructor(private val dao: servicioDao) : ServicioRepository {
    override fun getTiposServicios(): Flow<List<servicio>> {
        return dao.getServicios()
    }

    override fun buscarTiposServicios(any: String): Flow<List<servicio>> {
        return dao.buscarServicios(any)
    }

    override suspend fun updateTiposServicios(tipoServicio: servicio) {
        dao.updateServicio(tipoServicio)
    }

    override suspend fun agregarTipoServicio(tipoServicio: servicio) {
        dao.addServicio(tipoServicio)
    }

    override suspend fun eliminarTipoServicio(tipoServicio: servicio) {
        dao.deleteServicio(tipoServicio)
    }
}
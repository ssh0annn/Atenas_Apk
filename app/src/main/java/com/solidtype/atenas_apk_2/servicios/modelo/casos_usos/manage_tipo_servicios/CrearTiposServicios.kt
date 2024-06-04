package com.solidtype.atenas_apk_2.servicios.modelo.casos_usos.manage_tipo_servicios

import com.solidtype.atenas_apk_2.servicios.modelo.repository.ServicioRepository
import com.solidtype.atenas_apk_2.servicios.modelo.servicio
import javax.inject.Inject

class CrearTiposServicios @Inject constructor(private val repo: ServicioRepository){

    suspend operator fun invoke(servicio:servicio) {
        repo.agregarTipoServicio(servicio)
    }

}
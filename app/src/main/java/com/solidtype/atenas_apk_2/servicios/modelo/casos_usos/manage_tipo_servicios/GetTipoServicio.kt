package com.solidtype.atenas_apk_2.servicios.modelo.casos_usos.manage_tipo_servicios

import com.solidtype.atenas_apk_2.servicios.modelo.repository.ServicioRepository
import javax.inject.Inject

class GetTipoServicio @Inject constructor(private val repo:ServicioRepository){

    operator fun invoke() = repo.getTiposServicios()
}
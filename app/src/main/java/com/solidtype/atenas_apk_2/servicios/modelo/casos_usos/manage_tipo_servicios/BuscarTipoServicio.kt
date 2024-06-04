package com.solidtype.atenas_apk_2.servicios.modelo.casos_usos.manage_tipo_servicios

import com.solidtype.atenas_apk_2.servicios.modelo.repository.ServicioRepository
import javax.inject.Inject

class BuscarTipoServicio @Inject constructor(private val repo: ServicioRepository) {

    operator fun invoke(any:String) = repo.buscarTiposServicios(any)
}
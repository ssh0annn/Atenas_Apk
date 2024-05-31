package com.solidtype.atenas_apk_2.facturacion.domain.casosUsos

import com.solidtype.atenas_apk_2.facturacion.domain.repositorio.FacturaRepository
import javax.inject.Inject

class DetallesFacturas @Inject constructor(private val repo: FacturaRepository) {

    suspend operator fun invoke(NoFactura:Long)=repo.DetalleFactura(NoFactura)
}
package com.solidtype.atenas_apk_2.gestion_proveedores.domain.casos_usos.casos_cliente

import com.solidtype.atenas_apk_2.gestion_proveedores.domain.casos_usos.EliminarPersona

data class CasosClientes(
    val buscarClientes: BuscarClientes,
    val getClientes: GetClientes,
    val crearClientes: CrearClientes,
    val eliminarPersona: EliminarPersona
)

package com.solidtype.atenas_apk_2.gestion_proveedores.domain.casos_usos.casos_proveedores

import com.solidtype.atenas_apk_2.gestion_proveedores.domain.casos_usos.EliminarPersona


data class CasosProveedores(
    val buscarProveedores: BuscarProveedores,
    val crearProveedor: CrearProveedor,
    val getProveedores: GetProveedores,
    val eliminarPersona: EliminarPersona,
    val editarProveedores: EditarProveedores
)

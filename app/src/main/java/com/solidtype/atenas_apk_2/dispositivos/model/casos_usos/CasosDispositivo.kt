package com.solidtype.atenas_apk_2.dispositivos.model.casos_usos

data class CasosDispositivo(
    val actualizar: ActualizarDispositivo,
    val agregarDispositivo: AgregarDispositivo,
    val buscarDispositivos: BuscarDispositivos,
    val eliminar: EliminarDispositivo,
    val  getDispositivos: GetDispositivos
)
package com.solidtype.atenas_apk_2.gestion_proveedores.presentation.cliente.modelo


sealed class Personastodas {
    data class ClienteUI(
        val  id_cliente :Long = 0,
        val nombre: String?,
        val documento: String?,
        val telefono: String?,
        val email: String?
    ): Personastodas()
    data class Proveedor(
        val  id_proveedor :Long = 0,
        val nombre: String?,
        val tipo_documento: String?,
        val documento: String?,
        val direccion: String?,
        val telefono: String?,
        val email: String?,
    ): Personastodas()

}

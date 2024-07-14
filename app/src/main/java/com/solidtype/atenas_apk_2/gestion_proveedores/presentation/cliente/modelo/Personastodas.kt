package com.solidtype.atenas_apk_2.gestion_proveedores.presentation.cliente.modelo


sealed class Personastodas {
    data class ClienteUI(
        var id_cliente :Long = 0,
        val nombre: String?,
        val tipo_documento: String?,
        val documento: String?,
        val telefono: String?,
        val email: String?
    ): Personastodas()
    data class Proveedor(
        var id_proveedor :Long = 0,
        val nombre: String?,
        val tipo_documento: String?,
        val documento: String?,
        val direccion: String?,
        val telefono: String?,
        val email: String?,
    ): Personastodas()

}

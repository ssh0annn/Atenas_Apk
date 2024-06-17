package com.solidtype.atenas_apk_2.servicios.presentation.servicios

import com.solidtype.atenas_apk_2.core.entidades.tipo_venta
import com.solidtype.atenas_apk_2.dispositivos.model.Dispositivo
import com.solidtype.atenas_apk_2.gestion_proveedores.presentation.cliente.modelo.Personastodas
import com.solidtype.atenas_apk_2.gestion_usuarios.domain.modelo.usuario
import com.solidtype.atenas_apk_2.servicios.modelo.servicio
import java.time.LocalDate

data class ServicioTicket(
    val cliente: Personastodas.ClienteUI?  = null,
    val dispositivo: Dispositivo? = null,
    val vendedor: usuario? = null,
    val detalles: InfoTicket = InfoTicket(),
    val servicio: servicio? = null,
    val datosFinance: tipo_venta? = null,
    var fecha_final: LocalDate = LocalDate.now().plusDays(3)
)

data class InfoTicket(
    val imei: String = "",    val falla: String = "",
    val descripcion: String = "",
    val nota: String = "",
    val assesorios: String = "",
)


package com.solidtype.atenas_apk_2.servicios.presentation.servicios

import com.solidtype.atenas_apk_2.dispositivos.model.Dispositivo
import com.solidtype.atenas_apk_2.gestion_proveedores.presentation.cliente.modelo.Personastodas
import com.solidtype.atenas_apk_2.gestion_usuarios.domain.modelo.usuario
import com.solidtype.atenas_apk_2.servicios.modelo.servicio
import com.solidtype.atenas_apk_2.servicios.presentation.modelo.FormaPagos
import java.time.LocalDate

data class ServicioTicket(
    val cliente: Personastodas.ClienteUI?  = null,
    val dispositivo: Dispositivo? = null,
    val vendedor: Long? = null,
    val tipoVenta: FormaPagos? = null,
    val detalles: InfoTicket? = null,
    val servicio: servicio? = null,
    val datosFinance: DatoFinancieros? = null,
    var fecha_final: LocalDate = LocalDate.now()
)

data class InfoTicket(
    val imei: String = "",
    val falla: String = "",
    val descripcion: String = "",
    val nota: String = "",
    val assesorios: String? = "",
)

data class DatoFinancieros(
    val presupuesto: Double = 0.0,
    var abono: Double = 0.0,
    val subtotal: Double = 0.0,
    val impuesto: Double = 0.18,
    val total: Double =0.0
)
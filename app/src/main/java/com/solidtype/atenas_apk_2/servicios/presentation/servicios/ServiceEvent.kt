package com.solidtype.atenas_apk_2.servicios.presentation.servicios

import com.solidtype.atenas_apk_2.dispositivos.model.Dispositivo
import com.solidtype.atenas_apk_2.gestion_proveedores.presentation.cliente.modelo.Personastodas
import com.solidtype.atenas_apk_2.servicios.modelo.servicio
import com.solidtype.atenas_apk_2.servicios.presentation.modelo.FormaPagos
sealed class VendedorEvent{ data object GetCurrentUser : VendedorEvent()}
sealed class ServiceEvent {
//Eventos de servicios
    data object GetServicios : ServiceEvent()
    data class CreateServicio(val servicio: servicio) : ServiceEvent()
    data class ServicioSelecionado(val servicio: servicio) : ServiceEvent()
}
sealed class OnTicket{
    object GetTickets : OnTicket()
    data class InforTicket(val infoTicket: InfoTicket):OnTicket()
    data class CrearTicket(val ticket: ServicioTicket) : OnTicket()

}
sealed class ClientEvents{
    data class CrearCliente(val clienteUI: Personastodas.ClienteUI) : ClientEvents()
    object GetClientes : ClientEvents()
    data class ClienteSelecionado(val clienteUI: Personastodas.ClienteUI):ClientEvents()
}
sealed class DeviceEvent{
    object GetDispositivos : DeviceEvent()
    data class CrearDispositivo(val dispositivo: Dispositivo) : DeviceEvent()
    data class DispositivoSelecionado(val dispositivo: Dispositivo):DeviceEvent()
}
/**
 * Eventos y actividades de pagos.
 */
sealed class PagosEvent{

    data class TipoDePago(val formaPagos: FormaPagos):PagosEvent()

    data class DatosDelPago(val finaciero:DatoFinancieros):PagosEvent()

}




package com.solidtype.atenas_apk_2.gestion_tickets.di

import com.solidtype.atenas_apk_2.core.daos.tipo_ventaDao
import com.solidtype.atenas_apk_2.gestion_tickets.data.repositoryImpl.TicketRepositoryImpl
import com.solidtype.atenas_apk_2.gestion_tickets.data.ticketDao
import com.solidtype.atenas_apk_2.gestion_tickets.domain.TicketRepository
import com.solidtype.atenas_apk_2.gestion_tickets.domain.casos_tickets.CasosTicket
import com.solidtype.atenas_apk_2.gestion_tickets.domain.casos_tickets.CloseTicket
import com.solidtype.atenas_apk_2.gestion_tickets.domain.casos_tickets.CompletarPago
import com.solidtype.atenas_apk_2.gestion_tickets.domain.casos_tickets.CrearTicket
import com.solidtype.atenas_apk_2.gestion_tickets.domain.casos_tickets.GetPaymentInfo
import com.solidtype.atenas_apk_2.gestion_tickets.domain.casos_tickets.GetTickets
import com.solidtype.atenas_apk_2.gestion_tickets.domain.casos_tickets.buscarTickets
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton
@Module
@InstallIn(SingletonComponent::class)
object TicketModule {
    //Tickets Manejador
    @Provides
    @Singleton
    fun provideTicketRepository(ticket: ticketDao, venta: tipo_ventaDao): TicketRepository =
        TicketRepositoryImpl(ticket,venta )

    @Provides
    @Singleton
    fun providesCasosTickets(repo: TicketRepository) = CasosTicket(
        getTickets= GetTickets(repo),
        crearTicket= CrearTicket(repo),
        completarPago= CompletarPago(repo),
        closeTicket= CloseTicket(repo),
        buscarTickets= buscarTickets(repo),
        getPaymentInfo = GetPaymentInfo(repo)
    )

}
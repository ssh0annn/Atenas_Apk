package com.solidtype.atenas_apk_2.historial_ventas.di

import com.solidtype.atenas_apk_2.historial_ventas.data.implementaciones.HistorialRepositoryImp
import com.solidtype.atenas_apk_2.historial_ventas.domain.casosusos.BuscarporFechCatego
import com.solidtype.atenas_apk_2.historial_ventas.domain.casosusos.CasosHistorialReportes
import com.solidtype.atenas_apk_2.historial_ventas.domain.casosusos.ExportarTicketsHistorial
import com.solidtype.atenas_apk_2.historial_ventas.domain.casosusos.ExportarVentas
import com.solidtype.atenas_apk_2.historial_ventas.domain.casosusos.MostrarTodasVentas
import com.solidtype.atenas_apk_2.historial_ventas.domain.casosusos.Sync
import com.solidtype.atenas_apk_2.historial_ventas.domain.casosusos.VerTicketsPorFechas
import com.solidtype.atenas_apk_2.historial_ventas.domain.casosusos.VerTodosTickets
import com.solidtype.atenas_apk_2.historial_ventas.domain.repositories.HistorialRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton
@Module
@InstallIn(SingletonComponent::class)
object HistorialModule {
    @Singleton
    @Provides
    fun historialVentasRepository(impl: HistorialRepositoryImp): HistorialRepository = impl

    @Singleton
    @Provides
    fun provideCasosHistorial(repo: HistorialRepository) = CasosHistorialReportes(
        mostrarVentas = MostrarTodasVentas(repo),
        exportarVentas = ExportarVentas(repo),
        buscarporFechCatego = BuscarporFechCatego(repo),
        verTicketsPorFechas = VerTicketsPorFechas(repo),
        verTodosTickets = VerTodosTickets(repo),
        syncronizacion = Sync(repo),
        exportarTickets = ExportarTicketsHistorial(repo)
    )
}
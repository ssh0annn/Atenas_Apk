package com.solidtype.atenas_apk_2.facturacion.di

import com.solidtype.atenas_apk_2.facturacion.data.FacturaRepositoryImpl
import com.solidtype.atenas_apk_2.facturacion.domain.casosUsos.BuscarFacturas
import com.solidtype.atenas_apk_2.facturacion.domain.casosUsos.FacturacionCasosdeUso
import com.solidtype.atenas_apk_2.facturacion.domain.casosUsos.MostrarTodo
import com.solidtype.atenas_apk_2.facturacion.domain.repositorio.FacturaRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton
@Module
@InstallIn(SingletonComponent::class)
object FacturacionModule {
    //Facturacion

    @Provides
    @Singleton
    fun provideFacturaRepo(impl: FacturaRepositoryImpl): FacturaRepository = impl

    @Provides
    @Singleton
    fun provideFacturacionCasosUso(repo: FacturaRepository) = FacturacionCasosdeUso(
        buscarFacturas = BuscarFacturas(repo),
        mostrarTodo = MostrarTodo(repo)
    )
}
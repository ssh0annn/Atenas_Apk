package com.solidtype.atenas_apk_2.servicios.di

import com.solidtype.atenas_apk_2.servicios.data.repositoryImpl.ServicioRepositoryImpl
import com.solidtype.atenas_apk_2.servicios.data.servicioDao
import com.solidtype.atenas_apk_2.servicios.modelo.casos_usos.manage_tipo_servicios.BuscarTipoServicio
import com.solidtype.atenas_apk_2.servicios.modelo.casos_usos.manage_tipo_servicios.CasosTipoServicios
import com.solidtype.atenas_apk_2.servicios.modelo.casos_usos.manage_tipo_servicios.CrearTiposServicios
import com.solidtype.atenas_apk_2.servicios.modelo.casos_usos.manage_tipo_servicios.GetTipoServicio
import com.solidtype.atenas_apk_2.servicios.modelo.repository.ServicioRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton
@Module
@InstallIn(SingletonComponent::class)
object ServiciosModule {

    //Servicios y tipos servicios
    @Provides
    @Singleton
    fun provideCasosServicios(repo: ServicioRepository) = CasosTipoServicios(
        buscarTipoServicio = BuscarTipoServicio(repo),
        crearTiposServicios = CrearTiposServicios(repo),
        getTipoServicio = GetTipoServicio(repo)
    )

    @Provides
    @Singleton
    fun provideRepositoryServicios(dao : servicioDao): ServicioRepository = ServicioRepositoryImpl(dao)


}
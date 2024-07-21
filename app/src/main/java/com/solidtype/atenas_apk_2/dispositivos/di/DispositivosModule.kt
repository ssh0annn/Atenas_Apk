package com.solidtype.atenas_apk_2.dispositivos.di

import com.solidtype.atenas_apk_2.dispositivos.data.ddbb.DispositivoDao
import com.solidtype.atenas_apk_2.dispositivos.data.repository.DispositivosRepositoryImpl
import com.solidtype.atenas_apk_2.dispositivos.model.casos_usos.ActualizarDispositivo
import com.solidtype.atenas_apk_2.dispositivos.model.casos_usos.AgregarDispositivo
import com.solidtype.atenas_apk_2.dispositivos.model.casos_usos.BuscarDispositivos
import com.solidtype.atenas_apk_2.dispositivos.model.casos_usos.CasosDispositivo
import com.solidtype.atenas_apk_2.dispositivos.model.casos_usos.EliminarDispositivo
import com.solidtype.atenas_apk_2.dispositivos.model.casos_usos.GetDispositivos
import com.solidtype.atenas_apk_2.dispositivos.model.repository.DispositivosRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton
@Module
@InstallIn(SingletonComponent::class)
object DispositivosModule {
    //Dispositivos
    @Provides
    @Singleton
    fun provideCasosDispositivos(repo: DispositivosRepository): CasosDispositivo {
        return CasosDispositivo(
            actualizar = ActualizarDispositivo(repo),
            agregarDispositivo = AgregarDispositivo(repo),
            buscarDispositivos = BuscarDispositivos(repo),
            eliminar = EliminarDispositivo(repo),
            getDispositivos = GetDispositivos(repo)
        )

    }
    @Provides
    @Singleton
    fun provideDispositivoRepository(dao: DispositivoDao) : DispositivosRepository {
        return DispositivosRepositoryImpl(dao)
    }

}
package com.solidtype.atenas_apk_2.perfil_administrador.di

import com.solidtype.atenas_apk_2.perfil_administrador.data.PerfilAdminRepoImpl
import com.solidtype.atenas_apk_2.perfil_administrador.data.administradorDao
import com.solidtype.atenas_apk_2.perfil_administrador.domain.casos_usos.AdminUseCases
import com.solidtype.atenas_apk_2.perfil_administrador.domain.casos_usos.GetAdminInfo
import com.solidtype.atenas_apk_2.perfil_administrador.domain.casos_usos.UpdateAdmin
import com.solidtype.atenas_apk_2.perfil_administrador.domain.repository.PerfilAdminRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton
@Module
@InstallIn(SingletonComponent::class)
object AdministradorModule {

    //Perfil admnistrador
    @Provides
    @Singleton
    fun provideAdministradorRepository(adminDao: administradorDao): PerfilAdminRepository =
        PerfilAdminRepoImpl(adminDao)

    @Provides
    @Singleton
    fun proviteCasosPerfilAdministrador(repo: PerfilAdminRepository) = AdminUseCases(
        getAdminInfo = GetAdminInfo(repo),
        updateAdmin = UpdateAdmin(repo)
    )

}
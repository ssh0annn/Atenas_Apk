package com.solidtype.atenas_apk_2.gestion_usuarios.di

import com.solidtype.atenas_apk_2.core.remote.authtentication.MetodoAutenticacion
import com.solidtype.atenas_apk_2.gestion_usuarios.data.repositoryImpl.GestionUserRepoImpl
import com.solidtype.atenas_apk_2.gestion_usuarios.data.roll_usuarioDao
import com.solidtype.atenas_apk_2.gestion_usuarios.data.usuarioDao
import com.solidtype.atenas_apk_2.gestion_usuarios.domain.repository.GestionUserRepository
import com.solidtype.atenas_apk_2.gestion_usuarios.domain.use_cases.Actualizar
import com.solidtype.atenas_apk_2.gestion_usuarios.domain.use_cases.Agregar
import com.solidtype.atenas_apk_2.gestion_usuarios.domain.use_cases.Buscar
import com.solidtype.atenas_apk_2.gestion_usuarios.domain.use_cases.CrearRoles
import com.solidtype.atenas_apk_2.gestion_usuarios.domain.use_cases.EditarRoll
import com.solidtype.atenas_apk_2.gestion_usuarios.domain.use_cases.Eliminar
import com.solidtype.atenas_apk_2.gestion_usuarios.domain.use_cases.EliminarRoll
import com.solidtype.atenas_apk_2.gestion_usuarios.domain.use_cases.GetRoles
import com.solidtype.atenas_apk_2.gestion_usuarios.domain.use_cases.MostrarUsuario
import com.solidtype.atenas_apk_2.gestion_usuarios.domain.use_cases.UsuarioUseCases
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton
@Module
@InstallIn(SingletonComponent::class)
object UsuariosModule {
    //USUARIOS GESTION

    @Provides
    @Singleton
    fun provideRepositorioUsuario(
        userDao: usuarioDao,
        roll: roll_usuarioDao,
        registro: MetodoAutenticacion
    ): GestionUserRepository = GestionUserRepoImpl(roll, userDao, registro)

    @Provides
    @Singleton
    fun provideCasosUsuario(repo: GestionUserRepository) = UsuarioUseCases(
        actualizar = Actualizar(repo),
        agregar = Agregar(repo),
        eliminar = Eliminar(repo),
        mostrarUsuarios = MostrarUsuario(repo),
        buscarUsuario = Buscar(repo),
        getRoles = GetRoles(repo),
        crearRoles = CrearRoles(repo),
        actualizarRoll = EditarRoll(repo),
        eliminarRol = EliminarRoll(repo)
    )

}
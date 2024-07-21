package com.solidtype.atenas_apk_2.authentication.actualizacion.di

import com.google.firebase.auth.FirebaseAuth
import com.solidtype.atenas_apk_2.authentication.actualizacion.data.AuthRepositoryImpl
import com.solidtype.atenas_apk_2.authentication.actualizacion.data.remote_auth.MetodoAutenticacionImpl
import com.solidtype.atenas_apk_2.authentication.actualizacion.domain.AuthRepository
import com.solidtype.atenas_apk_2.authentication.actualizacion.domain.casos_usos.AuthCasos
import com.solidtype.atenas_apk_2.authentication.actualizacion.domain.casos_usos.CambiarPassword
import com.solidtype.atenas_apk_2.authentication.actualizacion.domain.casos_usos.ForgotPassword
import com.solidtype.atenas_apk_2.authentication.actualizacion.domain.casos_usos.Login
import com.solidtype.atenas_apk_2.authentication.actualizacion.domain.casos_usos.Logout
import com.solidtype.atenas_apk_2.authentication.actualizacion.domain.casos_usos.RegistraNuevoDevice
import com.solidtype.atenas_apk_2.authentication.actualizacion.domain.casos_usos.WhoIs
import com.solidtype.atenas_apk_2.core.remote.authtentication.MetodoAutenticacion
import com.solidtype.atenas_apk_2.core.remote.dataCloud.DataCloud
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton
@Module
@InstallIn(SingletonComponent::class)
object AuthModule {
    //Actualizacion de la autenticacion
    @Provides
    @Singleton
    fun provideMetodoAutenticacion(firebaseAuth: FirebaseAuth, dataCloud: DataCloud): MetodoAutenticacion = MetodoAutenticacionImpl(firebaseAuth, dataCloud)

    @Provides
    @Singleton
    fun provideCasosdeAutenticacion(repo: AuthRepository) = AuthCasos(
        login = Login(repo),
        logout= Logout(repo),
        whoIs = WhoIs(repo),
        forgotPassword = ForgotPassword(repo),
        cambiarPassword= CambiarPassword(repo),
        nuevoDevice= RegistraNuevoDevice(repo)
    )

    @Singleton
    @Provides
    fun provideAuthRepository(metod: MetodoAutenticacion): AuthRepository = AuthRepositoryImpl(metod)

}
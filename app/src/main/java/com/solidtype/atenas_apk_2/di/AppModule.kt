package com.solidtype.atenas_apk_2.di

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.firestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.solidtype.atenas_apk_2.users.data.repository.RepositoryImpl
import com.solidtype.atenas_apk_2.users.domain.repository.UserRepository
import com.solidtype.atenas_apk_2.users.domain.userCase.All_useCases
import com.solidtype.atenas_apk_2.users.domain.userCase.implementados.CapturaICCID
import com.solidtype.atenas_apk_2.users.domain.userCase.implementados.EstadoLicencia
import com.solidtype.atenas_apk_2.users.domain.userCase.implementados.ExisteUsuario
import com.solidtype.atenas_apk_2.users.domain.userCase.implementados.Registrarse
import com.solidtype.atenas_apk_2.users.domain.userCase.implementados.SignInUseCase
import com.solidtype.atenas_apk_2.users.domain.userCase.implementados.SignOutUseCase
import com.solidtype.atenas_apk_2.users.domain.userCase.implementados.VerificaICCIDUseCase
import com.solidtype.atenas_apk_2.users.domain.userCase.implementados.getCurrentUser
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Singleton
    @Provides
    fun provideFirebaseFirestore(): FirebaseFirestore = Firebase.firestore
    @Singleton
    @Provides
    fun provideFirebaseAuth(): FirebaseAuth = FirebaseAuth.getInstance()

    @Singleton
    @Provides
    fun userRepository(impl : RepositoryImpl): UserRepository =impl

    @Singleton
    @Provides
    fun provideAuthCases (repository: UserRepository)= All_useCases(
        login = SignInUseCase(repository),
        logout = SignOutUseCase(repository),
        current_user = getCurrentUser(repository),
        register = Registrarse(repository),
        capturaIccid = CapturaICCID(repository),
        validarICCID = VerificaICCIDUseCase(repository),
        estado_licencia = EstadoLicencia(repository),
        usuarioExiste = ExisteUsuario(repository)

    )

    /*
     val login: SignInUseCase,
    val logout: SignOutUseCase= SignOutUseCase(),
    val current_user: getCurrentUser=getCurrentUser(),
    val register: Registrarse =Registrarse(),
    val capturaIccid:CapturaICCID=CapturaICCID(),
    val validarICCID: VerificaICCIDUseCase=VerificaICCIDUseCase(),
    val estado_licencia: EstadoLicencia = EstadoLicencia(),
    val usuarioExiste: ExisteUsuario =ExisteUsuario()
     */




}
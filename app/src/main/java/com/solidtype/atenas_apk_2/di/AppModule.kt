package com.solidtype.atenas_apk_2.di

import android.app.Application
import android.content.Context
import androidx.room.Room
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.solidtype.atenas_apk_2.products.data.repositoryImpl.InventarioRepoImpl
import com.solidtype.atenas_apk_2.products.domain.repository.InventarioRepo
import com.solidtype.atenas_apk_2.products.domain.userCases.CasosInventario
import com.solidtype.atenas_apk_2.products.domain.userCases.DeleteProductos
import com.solidtype.atenas_apk_2.products.domain.userCases.SearchProductosLike
import com.solidtype.atenas_apk_2.products.domain.userCases.UpdateProducto
import com.solidtype.atenas_apk_2.products.domain.userCases.createProductos
import com.solidtype.atenas_apk_2.products.domain.userCases.getProductos
import com.solidtype.atenas_apk_2.products.domain.userCases.getProductosByCodigo
import com.solidtype.atenas_apk_2.Authentication.data.repository.RepositoryImpl
import com.solidtype.atenas_apk_2.Authentication.domain.repository.UserRepository
import com.solidtype.atenas_apk_2.Authentication.domain.userCase.AuthUseCases
import com.solidtype.atenas_apk_2.Authentication.domain.userCase.implementados.CapturaICCID
import com.solidtype.atenas_apk_2.Authentication.domain.userCase.implementados.EstadoLicencia
import com.solidtype.atenas_apk_2.Authentication.domain.userCase.implementados.ExisteUsuario
import com.solidtype.atenas_apk_2.Authentication.domain.userCase.implementados.Registrarse
import com.solidtype.atenas_apk_2.Authentication.domain.userCase.implementados.SignInUseCase
import com.solidtype.atenas_apk_2.Authentication.domain.userCase.implementados.SignOutUseCase
import com.solidtype.atenas_apk_2.Authentication.domain.userCase.implementados.VerificaICCIDUseCase
import com.solidtype.atenas_apk_2.Authentication.domain.userCase.implementados.getCurrentUser
import com.solidtype.atenas_apk_2.historial_ventas.data.implementaciones.HistorialRepositoryImp
import com.solidtype.atenas_apk_2.historial_ventas.data.local.dao.HistorialVentaDAO
import com.solidtype.atenas_apk_2.historial_ventas.domain.repositories.HistorialRepository
import com.solidtype.atenas_apk_2.historial_ventas.domain.casosusos.BuscarporFechCatego
import com.solidtype.atenas_apk_2.historial_ventas.domain.casosusos.CasosHistorialReportes
import com.solidtype.atenas_apk_2.historial_ventas.domain.casosusos.ExportarVentas
import com.solidtype.atenas_apk_2.historial_ventas.domain.casosusos.MostrarTodasVentas
import com.solidtype.atenas_apk_2.products.data.local.ProductDataBase
import com.solidtype.atenas_apk_2.products.data.local.dao.ProductDao
import com.solidtype.atenas_apk_2.products.domain.userCases.ExportarExcel
import com.solidtype.atenas_apk_2.products.domain.userCases.ImportarExcelFile
import com.solidtype.atenas_apk_2.products.domain.userCases.SyncProductos
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Singleton
    @Provides
    fun contextAplicacion(@ApplicationContext context: Context) =context

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
    fun historialVentasRepository(impl : HistorialRepositoryImp): HistorialRepository =impl

    @Singleton
    @Provides
    fun provideAuthCases (repository: UserRepository)= AuthUseCases(
        login = SignInUseCase(repository),
        logout = SignOutUseCase(repository),
        current_user = getCurrentUser(repository),
        register = Registrarse(repository),
        capturaIccid = CapturaICCID(repository),
        validarICCID = VerificaICCIDUseCase(repository),
        estado_licencia = EstadoLicencia(repository),
        usuarioExiste = ExisteUsuario(repository)
    )


    @Singleton
    @Provides
    fun provideInventarioRepo( impl: InventarioRepoImpl): InventarioRepo=impl


    @Singleton
    @Provides
    fun provideCasosInventario(repository: InventarioRepo)=CasosInventario(
        getProductos= getProductos(repository),
        createProductos=createProductos(repository),
        getProductosByCodigo= getProductosByCodigo(repository),
        searchProductos= SearchProductosLike(repository),
        updateProducto=UpdateProducto(repository),
        deleteProductos=DeleteProductos(repository),
        exportarExcel = ExportarExcel(repository),
        importarExcelFile = ImportarExcelFile(repository),
        syncProductos= SyncProductos(repository)
    )
    @Singleton
    @Provides
    fun provideCasosHistorial(repo: HistorialRepository) = CasosHistorialReportes(
        mostrarVentas= MostrarTodasVentas(repo),
        exportarVentas= ExportarVentas(repo),
        buscarporFechCatego = BuscarporFechCatego(repo)
    )

    @Singleton
    @Provides
    fun provideUserDatabase(app: Application) = Room.databaseBuilder(
        app,
        ProductDataBase::class.java,
        "tabla_producto"
    ).build()

    @Provides
    @Singleton
    fun provideProductDao(db : ProductDataBase): ProductDao {
        return db.ProductDao
    }

    @Provides
    @Singleton
    fun provideHistorialDao(db : ProductDataBase): HistorialVentaDAO {
        return db.HistorialDao
    }


}
package com.solidtype.atenas_apk_2.di

import android.app.Application
import android.content.Context
import androidx.room.Room
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.solidtype.atenas_apk_2.perfil_administrador.data.administradorDao
import com.solidtype.atenas_apk_2.dispositivos.data.ddbb.DispositivoDao
import com.solidtype.atenas_apk_2.products.data.local.dao.categoriaDao
import com.solidtype.atenas_apk_2.facturacion.data.local.dao.detalle_ventaDao
import com.solidtype.atenas_apk_2.products.data.local.dao.inventarioDao
import com.solidtype.atenas_apk_2.gestion_proveedores.data.personaDao
import com.solidtype.atenas_apk_2.gestion_usuarios.data.roll_usuarioDao
import com.solidtype.atenas_apk_2.servicios.data.servicioDao
import com.solidtype.atenas_apk_2.gestion_tickets.data.ticketDao
import com.solidtype.atenas_apk_2.core.daos.tipo_ventaDao
import com.solidtype.atenas_apk_2.gestion_usuarios.data.usuarioDao
import com.solidtype.atenas_apk_2.historial_ventas.data.local.dao.actualizacion.ventaDao
import com.solidtype.atenas_apk_2.core.ddbb.ProductDataBase
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
    fun contextAplicacion(@ApplicationContext context: Context) = context
    @Singleton
    @Provides
    fun provideFirebaseFirestore(): FirebaseFirestore = Firebase.firestore

    @Singleton
    @Provides
    fun provideFirebaseAuth(): FirebaseAuth = FirebaseAuth.getInstance()

    @Singleton
    @Provides
    fun provideUserDatabase(app: Application) = Room.databaseBuilder(
        app,
        ProductDataBase::class.java,
        "tabla_producto"
    ).fallbackToDestructiveMigration().build()



//Esta injection esta a espera del viewmodel. Cuando el viewmodel este completo ,hay que quitar el comentario

    //Nueva inservion de datos con su correspondiente base de datos local apartado hacia esto

    @Provides
    @Singleton
    fun provideCategoriaDao(db: ProductDataBase): categoriaDao {
        return db.categoriaDAO
    }

    @Provides
    @Singleton
    fun provideDetalleVentaDao(db: ProductDataBase): detalle_ventaDao {
        return db.detalleVentaDAO
    }

    @Provides
    @Singleton
    fun provideInventarioDao(db: ProductDataBase): inventarioDao {
        return db.inventarioDAO
    }

    @Provides
    @Singleton
    fun provideRollUsuarioDao(db: ProductDataBase): roll_usuarioDao {
        return db.rollUsuarioDAO
    }

    @Provides
    @Singleton
    fun provideServicioDao(db: ProductDataBase): servicioDao {
        return db.servicioDAO
    }

    @Provides
    @Singleton
    fun provideTicketDao(db: ProductDataBase): ticketDao {
        return db.ticketDAO
    }
    @Provides
    @Singleton
    fun provideTipoVentaDao(db: ProductDataBase): tipo_ventaDao {
        return db.tipoVentaDAO
    }

    @Provides
    @Singleton
    fun provideUsuarioDao(db: ProductDataBase): usuarioDao {
        return db.usuarioDAO
    }

    @Provides
    @Singleton
    fun provideVentaDao(db: ProductDataBase): ventaDao {
        return db.ventaDAO
    }

    @Provides
    @Singleton
    fun providePersonaDao(db: ProductDataBase): personaDao {
        return db.personaDAO
    }

    @Provides
    @Singleton
    fun provideAdministradorDao(db: ProductDataBase): administradorDao {
        return db.adminDao
    }

    @Provides
    @Singleton
    fun provideDispositivoDao(db: ProductDataBase): DispositivoDao {
        return db.dispositivoDao
    }

}

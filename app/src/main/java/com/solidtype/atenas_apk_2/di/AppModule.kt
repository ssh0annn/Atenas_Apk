package com.solidtype.atenas_apk_2.di

import android.app.Application
import android.content.Context
import androidx.room.Room
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.solidtype.atenas_apk_2.Authentication.data.remote.RemoteFirebase
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
import com.solidtype.atenas_apk_2.perfil_administrador.data.administradorDao
import com.solidtype.atenas_apk_2.dispositivos.data.ddbb.DispositivoDao
import com.solidtype.atenas_apk_2.products.data.local.dao.categoriaDao
import com.solidtype.atenas_apk_2.gestion_tickets.data.detalle_ticketDao
import com.solidtype.atenas_apk_2.facturacion.data.local.dao.detalle_ventaDao
import com.solidtype.atenas_apk_2.products.data.local.dao.inventarioDao
import com.solidtype.atenas_apk_2.gestion_proveedores.data.personaDao
import com.solidtype.atenas_apk_2.gestion_usuarios.data.roll_usuarioDao
import com.solidtype.atenas_apk_2.servicios.data.servicioDao
import com.solidtype.atenas_apk_2.gestion_tickets.data.ticketDao
import com.solidtype.atenas_apk_2.core.daos.tipo_ventaDao
import com.solidtype.atenas_apk_2.gestion_usuarios.data.usuarioDao
import com.solidtype.atenas_apk_2.historial_ventas.data.local.dao.actualizacion.ventaDao
import com.solidtype.atenas_apk_2.historial_ventas.data.implementaciones.HistorialRepositoryImp
import com.solidtype.atenas_apk_2.historial_ventas.domain.repositories.HistorialRepository
import com.solidtype.atenas_apk_2.historial_ventas.domain.casosusos.BuscarporFechCatego
import com.solidtype.atenas_apk_2.historial_ventas.domain.casosusos.CasosHistorialReportes
import com.solidtype.atenas_apk_2.historial_ventas.domain.casosusos.ExportarVentas
import com.solidtype.atenas_apk_2.historial_ventas.domain.casosusos.MostrarTodasVentas
import com.solidtype.atenas_apk_2.historial_ventas.data.local.dao.HistorialTicketDAO
import com.solidtype.atenas_apk_2.historial_ventas.data.local.dao.HistorialVentaDAO
import com.solidtype.atenas_apk_2.core.ddbb.ProductDataBase
import com.solidtype.atenas_apk_2.core.remote.authtentication.auth
import com.solidtype.atenas_apk_2.core.remote.dataCloud.DataCloud
import com.solidtype.atenas_apk_2.core.remote.dataCloud.DataCloudImpl
import com.solidtype.atenas_apk_2.dispositivos.data.repository.DispositivosRepositoryImpl
import com.solidtype.atenas_apk_2.dispositivos.model.casos_usos.ActualizarDispositivo
import com.solidtype.atenas_apk_2.dispositivos.model.casos_usos.AgregarDispositivo
import com.solidtype.atenas_apk_2.dispositivos.model.casos_usos.BuscarDispositivos
import com.solidtype.atenas_apk_2.dispositivos.model.casos_usos.CasosDispositivo
import com.solidtype.atenas_apk_2.dispositivos.model.casos_usos.EliminarDispositivo
import com.solidtype.atenas_apk_2.dispositivos.model.casos_usos.GetDispositivos
import com.solidtype.atenas_apk_2.dispositivos.model.repository.DispositivosRepository
import com.solidtype.atenas_apk_2.facturacion.data.FacturaRepositoryImpl
import com.solidtype.atenas_apk_2.facturacion.domain.casosUsos.BuscarFacturas
import com.solidtype.atenas_apk_2.facturacion.domain.casosUsos.DetallesFacturas
import com.solidtype.atenas_apk_2.facturacion.domain.casosUsos.FacturacionCasosdeUso
import com.solidtype.atenas_apk_2.facturacion.domain.casosUsos.MostrarTodo
import com.solidtype.atenas_apk_2.facturacion.domain.repositorio.FacturaRepository
import com.solidtype.atenas_apk_2.gestion_proveedores.data.repositoryimpl.ClienteProveedorRepoImpl
import com.solidtype.atenas_apk_2.gestion_proveedores.domain.casos_usos.EliminarPersona
import com.solidtype.atenas_apk_2.gestion_proveedores.domain.casos_usos.casos_cliente.BuscarClientes
import com.solidtype.atenas_apk_2.gestion_proveedores.domain.casos_usos.casos_cliente.CasosClientes
import com.solidtype.atenas_apk_2.gestion_proveedores.domain.casos_usos.casos_cliente.CrearClientes
import com.solidtype.atenas_apk_2.gestion_proveedores.domain.casos_usos.casos_cliente.GetClientes
import com.solidtype.atenas_apk_2.gestion_proveedores.domain.casos_usos.casos_proveedores.BuscarProveedores
import com.solidtype.atenas_apk_2.gestion_proveedores.domain.casos_usos.casos_proveedores.CasosProveedores
import com.solidtype.atenas_apk_2.gestion_proveedores.domain.casos_usos.casos_proveedores.CrearProveedor
import com.solidtype.atenas_apk_2.gestion_proveedores.domain.casos_usos.casos_proveedores.GetProveedores
import com.solidtype.atenas_apk_2.gestion_proveedores.domain.repository.ClienteProveedorRepository
import com.solidtype.atenas_apk_2.gestion_tickets.data.repositoryImpl.TicketRepositoryImpl
import com.solidtype.atenas_apk_2.gestion_tickets.domain.TicketRepository
import com.solidtype.atenas_apk_2.gestion_tickets.domain.casos_tickets.BuscarDetallesTicket
import com.solidtype.atenas_apk_2.gestion_tickets.domain.casos_tickets.CasosTicket
import com.solidtype.atenas_apk_2.gestion_tickets.domain.casos_tickets.CloseTicket
import com.solidtype.atenas_apk_2.gestion_tickets.domain.casos_tickets.CompletarPago
import com.solidtype.atenas_apk_2.gestion_tickets.domain.casos_tickets.CrearTicket
import com.solidtype.atenas_apk_2.gestion_tickets.domain.casos_tickets.GetDetallesTicket
import com.solidtype.atenas_apk_2.gestion_tickets.domain.casos_tickets.GetTickets
import com.solidtype.atenas_apk_2.gestion_tickets.domain.casos_tickets.buscarTickets
import com.solidtype.atenas_apk_2.gestion_usuarios.data.repositoryImpl.GestionUserRepoImpl
import com.solidtype.atenas_apk_2.gestion_usuarios.domain.repository.GestionUserRepository
import com.solidtype.atenas_apk_2.gestion_usuarios.domain.use_cases.Actualizar
import com.solidtype.atenas_apk_2.gestion_usuarios.domain.use_cases.Agregar
import com.solidtype.atenas_apk_2.gestion_usuarios.domain.use_cases.Buscar
import com.solidtype.atenas_apk_2.gestion_usuarios.domain.use_cases.CrearRoles
import com.solidtype.atenas_apk_2.gestion_usuarios.domain.use_cases.EditarRoll
import com.solidtype.atenas_apk_2.gestion_usuarios.domain.use_cases.Eliminar
import com.solidtype.atenas_apk_2.gestion_usuarios.domain.use_cases.GetRoles
import com.solidtype.atenas_apk_2.gestion_usuarios.domain.use_cases.MostrarUsuario
import com.solidtype.atenas_apk_2.gestion_usuarios.domain.use_cases.UsuarioUseCases
import com.solidtype.atenas_apk_2.historial_ventas.data.remoteHistoVentaFB.intefaces.QueryDBHistorialVentas
import com.solidtype.atenas_apk_2.historial_ventas.data.remoteHistoVentaFB.QueryDBHistorial.QueryDBHistorialVentasVentaImpl
import com.solidtype.atenas_apk_2.historial_ventas.data.remoteHistoVentaFB.intefaces.MediatorHistorialVentas
import com.solidtype.atenas_apk_2.historial_ventas.data.remoteHistoVentaFB.mediator.MediatorHistorialVentasImpl
import com.solidtype.atenas_apk_2.historial_ventas.data.remoteTicketsFB.interfaces.QueryDBticket
import com.solidtype.atenas_apk_2.historial_ventas.data.remoteTicketsFB.QueryDBTicket.QueryDBticketImpl
import com.solidtype.atenas_apk_2.historial_ventas.data.remoteTicketsFB.interfaces.RemoteTicketsFB
import com.solidtype.atenas_apk_2.historial_ventas.data.remoteTicketsFB.mediadorTicket.RemoteTicketsFBFBImpl
import com.solidtype.atenas_apk_2.historial_ventas.domain.casosusos.ExportarTicketsHistorial
import com.solidtype.atenas_apk_2.historial_ventas.domain.casosusos.Sync
import com.solidtype.atenas_apk_2.historial_ventas.domain.casosusos.VerTicketsPorFechas
import com.solidtype.atenas_apk_2.historial_ventas.domain.casosusos.VerTodosTickets
import com.solidtype.atenas_apk_2.perfil_administrador.data.PerfilAdminRepoImpl
import com.solidtype.atenas_apk_2.perfil_administrador.domain.casos_usos.AdminUseCases
import com.solidtype.atenas_apk_2.perfil_administrador.domain.casos_usos.GetAdminInfo
import com.solidtype.atenas_apk_2.perfil_administrador.domain.casos_usos.UpdateAdmin
import com.solidtype.atenas_apk_2.perfil_administrador.domain.repository.PerfilAdminRepository
import com.solidtype.atenas_apk_2.products.data.local.dao.ProductDao
import com.solidtype.atenas_apk_2.products.data.remote.remoteProFB.interfaces.QueryDBlocal
import com.solidtype.atenas_apk_2.products.data.remote.remoteProFB.dataDb.DataDbProducts.QueryDBlocalImpl
import com.solidtype.atenas_apk_2.products.data.remote.remoteProFB.interfaces.MediatorProducts
import com.solidtype.atenas_apk_2.products.data.remote.remoteProFB.mediator.MediatorProductsImpl
import com.solidtype.atenas_apk_2.products.domain.userCases.CrearCategoria
import com.solidtype.atenas_apk_2.products.domain.userCases.ExportarExcel
import com.solidtype.atenas_apk_2.products.domain.userCases.GetCategorias
import com.solidtype.atenas_apk_2.products.domain.userCases.ImportarExcelFile
import com.solidtype.atenas_apk_2.products.domain.userCases.SyncProductos
import com.solidtype.atenas_apk_2.servicios.data.repositoryImpl.ServicioRepositoryImpl
import com.solidtype.atenas_apk_2.servicios.modelo.casos_usos.manage_tipo_servicios.BuscarTipoServicio
import com.solidtype.atenas_apk_2.servicios.modelo.casos_usos.manage_tipo_servicios.CasosTipoServicios
import com.solidtype.atenas_apk_2.servicios.modelo.casos_usos.manage_tipo_servicios.CrearTiposServicios
import com.solidtype.atenas_apk_2.servicios.modelo.casos_usos.manage_tipo_servicios.GetTipoServicio
import com.solidtype.atenas_apk_2.servicios.modelo.repository.ServicioRepository
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
    fun userRepository(impl: RepositoryImpl): UserRepository = impl

    @Singleton
    @Provides
    fun historialVentasRepository(impl: HistorialRepositoryImp): HistorialRepository = impl


    @Singleton
    @Provides
    fun provideAuthCases(repository: UserRepository) = AuthUseCases(
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
    fun provideInventarioRepo(impl: InventarioRepoImpl): InventarioRepo = impl


    @Singleton
    @Provides
    fun provideCasosInventario(repository: InventarioRepo) = CasosInventario(
        getProductos = getProductos(repository),
        createProductos = createProductos(repository),
        getProductosByCodigo = getProductosByCodigo(repository),
        searchProductos = SearchProductosLike(repository),
        updateProducto = UpdateProducto(repository),
        deleteProductos = DeleteProductos(repository),
        exportarExcel = ExportarExcel(repository),
        importarExcelFile = ImportarExcelFile(repository),
        syncProductos = SyncProductos(repository),
        agregarCategoria = CrearCategoria(repository),
        getCategorias = GetCategorias(repository)
    )

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


    @Singleton
    @Provides
    fun provideUserDatabase(app: Application) = Room.databaseBuilder(
        app,
        ProductDataBase::class.java,
        "tabla_producto"
    ).fallbackToDestructiveMigration().build()

    @Provides
    @Singleton
    fun provideProductDao(db: ProductDataBase): ProductDao {
        return db.ProductDao
    }

    @Provides
    @Singleton
    fun provideHistorialVentaDao(db: ProductDataBase): HistorialVentaDAO {
        return db.HistorialVentaDao
    }

    @Provides
    @Singleton
    fun provideHistorialTicketDao(db: ProductDataBase): HistorialTicketDAO {
        return db.HistorialTicketDao
    }

    //proveeyendo el DataCloud correspondiente
    @Provides
    @Singleton
    fun providesDataImpl(DataImpl: DataCloudImpl): DataCloud = DataImpl

    @Provides
    @Singleton
    fun providesDataDbHistorial(Query: QueryDBHistorialVentasVentaImpl): QueryDBHistorialVentas =
        Query

    @Provides
    @Singleton
    fun providesDataDbTicekts(QueryDBticketImpl: QueryDBticketImpl): QueryDBticket =
        QueryDBticketImpl

    @Provides
    @Singleton
    fun providesDataDBTickets(QueryDBlocalImpl: QueryDBlocalImpl): QueryDBlocal = QueryDBlocalImpl

    @Provides
    @Singleton
    fun providesAuthInterface(RemoteFirebase: RemoteFirebase): auth = RemoteFirebase
    //proveyendo el asyncPro interface

    @Provides
    @Singleton
    fun providesAsyncPro(MediatorProductsImpl: MediatorProductsImpl): MediatorProducts =
        MediatorProductsImpl

    @Provides
    @Singleton
    fun providesAsyncVentas(MediatorHistorialVentasImpl: MediatorHistorialVentasImpl): MediatorHistorialVentas =
        MediatorHistorialVentasImpl


    //proveyendo el asyncTicejts interface
    @Provides
    @Singleton
    fun providesAsyncTickets(RemoteTicketsFBImpl: RemoteTicketsFBFBImpl): RemoteTicketsFB =
        RemoteTicketsFBImpl

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
    fun provideDetalleTicketDao(db: ProductDataBase): detalle_ticketDao {
        return db.detalleTicketDAO
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

    @Provides
    @Singleton
    fun provideFacturaRepo(impl: FacturaRepositoryImpl): FacturaRepository = impl

    @Provides
    @Singleton
    fun provideFacturacionCasosUso(repo: FacturaRepository) = FacturacionCasosdeUso(
        buscarFacturas = BuscarFacturas(repo),
        detallesFacturas = DetallesFacturas(repo),
        mostrarTodo = MostrarTodo(repo)
    )

    //USUARIOS GESTION

    @Provides
    @Singleton
    fun provideRepositorioUsuario(
        userDao: usuarioDao,
        roll: roll_usuarioDao
    ): GestionUserRepository = GestionUserRepoImpl(roll, userDao)

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
        actualizarRoll = EditarRoll(repo)
    )
    //Injectando Personas y tipos de personas.

    @Provides
    @Singleton
    fun provideClienteProveedorRepository(persDao: personaDao): ClienteProveedorRepository {
        return ClienteProveedorRepoImpl(persDao)
    }

    @Provides
    @Singleton
    fun provideCasosCliente(repo: ClienteProveedorRepository) = CasosClientes(
        buscarClientes = BuscarClientes(repo),
        getClientes = GetClientes(repo),
        crearClientes = CrearClientes(repo),
        eliminarPersona = EliminarPersona(repo)
    )

    @Provides
    @Singleton
    fun provideCasosProveedor(repo: ClienteProveedorRepository) = CasosProveedores(
        buscarProveedores = BuscarProveedores(repo),
        getProveedores = GetProveedores(repo),
        crearProveedor = CrearProveedor(repo),
        eliminarPersona = EliminarPersona(repo)
    )

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
    fun provideDispositivoRepository(dao:DispositivoDao) : DispositivosRepository {
        return DispositivosRepositoryImpl(dao)
    }

    //Tickets Manejador
    @Provides
    @Singleton
    fun provideTicketRepository(ticket:ticketDao, detalle:detalle_ticketDao):TicketRepository =
        TicketRepositoryImpl(ticket, detalle)

    @Provides
    @Singleton
    fun providesCasosTickets(repo:TicketRepository) = CasosTicket(
        getTickets= GetTickets(repo),
        getDetallesTicket= GetDetallesTicket(repo),
        crearTicket= CrearTicket(repo),
        completarPago= CompletarPago(repo),
        closeTicket= CloseTicket(repo),
        buscarTickets= buscarTickets(repo),
        buscarDetallesTicket= BuscarDetallesTicket(repo)
    )

    //Servicios y tipos servicios

    @Provides
    @Singleton
    fun provideCasosServicios(repo:ServicioRepository) = CasosTipoServicios(
        buscarTipoServicio = BuscarTipoServicio(repo),
        crearTiposServicios = CrearTiposServicios(repo),
        getTipoServicio = GetTipoServicio(repo)
    )

    @Provides
    @Singleton
    fun provideRepositoryServicios(dao : servicioDao):ServicioRepository = ServicioRepositoryImpl(dao)



}

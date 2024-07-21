package com.solidtype.atenas_apk_2.gestion_proveedores.di

import com.solidtype.atenas_apk_2.gestion_proveedores.data.personaDao
import com.solidtype.atenas_apk_2.gestion_proveedores.data.repositoryimpl.ClienteProveedorRepoImpl
import com.solidtype.atenas_apk_2.gestion_proveedores.domain.casos_usos.EliminarPersona
import com.solidtype.atenas_apk_2.gestion_proveedores.domain.casos_usos.casos_cliente.BuscarClientes
import com.solidtype.atenas_apk_2.gestion_proveedores.domain.casos_usos.casos_cliente.CasosClientes
import com.solidtype.atenas_apk_2.gestion_proveedores.domain.casos_usos.casos_cliente.CrearClientes
import com.solidtype.atenas_apk_2.gestion_proveedores.domain.casos_usos.casos_cliente.EditarCliente
import com.solidtype.atenas_apk_2.gestion_proveedores.domain.casos_usos.casos_cliente.GetClientes
import com.solidtype.atenas_apk_2.gestion_proveedores.domain.casos_usos.casos_proveedores.BuscarProveedores
import com.solidtype.atenas_apk_2.gestion_proveedores.domain.casos_usos.casos_proveedores.CasosProveedores
import com.solidtype.atenas_apk_2.gestion_proveedores.domain.casos_usos.casos_proveedores.CrearProveedor
import com.solidtype.atenas_apk_2.gestion_proveedores.domain.casos_usos.casos_proveedores.EditarProveedores
import com.solidtype.atenas_apk_2.gestion_proveedores.domain.casos_usos.casos_proveedores.GetProveedores
import com.solidtype.atenas_apk_2.gestion_proveedores.domain.casos_usos.casos_proveedores.ProveedoresTodos
import com.solidtype.atenas_apk_2.gestion_proveedores.domain.repository.ClienteProveedorRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton
@Module
@InstallIn(SingletonComponent::class)
object PersonasModule {

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
        eliminarPersona = EliminarPersona(repo),
        editarCliente = EditarCliente(repo)
    )

    @Provides
    @Singleton
    fun provideCasosProveedor(repo: ClienteProveedorRepository) = CasosProveedores(
        buscarProveedores = BuscarProveedores(repo),
        getProveedores = GetProveedores(repo),
        crearProveedor = CrearProveedor(repo),
        eliminarPersona = EliminarPersona(repo),
        editarProveedores = EditarProveedores(repo),
        proveedoresTodos= ProveedoresTodos(repo)

    )


}
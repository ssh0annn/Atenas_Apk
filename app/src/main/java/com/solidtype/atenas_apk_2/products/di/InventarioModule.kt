package com.solidtype.atenas_apk_2.products.di

import com.solidtype.atenas_apk_2.products.data.repositoryImpl.InventarioRepoImpl
import com.solidtype.atenas_apk_2.products.domain.repository.InventarioRepo
import com.solidtype.atenas_apk_2.products.domain.userCases.BuscarCategorias
import com.solidtype.atenas_apk_2.products.domain.userCases.CasosInventario
import com.solidtype.atenas_apk_2.products.domain.userCases.CrearCategoria
import com.solidtype.atenas_apk_2.products.domain.userCases.DeleteProductos
import com.solidtype.atenas_apk_2.products.domain.userCases.EliminarCategorias
import com.solidtype.atenas_apk_2.products.domain.userCases.ExportarExcel
import com.solidtype.atenas_apk_2.products.domain.userCases.GetCategorias
import com.solidtype.atenas_apk_2.products.domain.userCases.ImportarExcelFile
import com.solidtype.atenas_apk_2.products.domain.userCases.SearchProductosLike
import com.solidtype.atenas_apk_2.products.domain.userCases.SyncProductos
import com.solidtype.atenas_apk_2.products.domain.userCases.UpdateProducto
import com.solidtype.atenas_apk_2.products.domain.userCases.createProductos
import com.solidtype.atenas_apk_2.products.domain.userCases.getProductos
import com.solidtype.atenas_apk_2.products.domain.userCases.getProductosByCodigo
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton
@Module
@InstallIn(SingletonComponent::class)
object InventarioModule {
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
        getCategorias = GetCategorias(repository),
        eliminarCategorias= EliminarCategorias(repository),
        buscarCategorias= BuscarCategorias(repository)
    )
}
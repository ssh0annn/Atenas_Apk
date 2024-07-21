package com.solidtype.atenas_apk_2.core.remote.di

import com.solidtype.atenas_apk_2.core.remote.dataCloud.DataCloud
import com.solidtype.atenas_apk_2.core.remote.dataCloud.DataCloudImpl
import com.solidtype.atenas_apk_2.historial_ventas.data.remoteHistoVentaFB.QueryDBHistorial.QueryDBHistorialVentasVentaImpl
import com.solidtype.atenas_apk_2.historial_ventas.data.remoteHistoVentaFB.intefaces.MediatorHistorialVentas
import com.solidtype.atenas_apk_2.historial_ventas.data.remoteHistoVentaFB.intefaces.QueryDBHistorialVentas
import com.solidtype.atenas_apk_2.historial_ventas.data.remoteHistoVentaFB.mediator.MediatorHistorialVentasImpl
import com.solidtype.atenas_apk_2.historial_ventas.data.remoteTicketsFB.QueryDBTicket.QueryDBticketImpl
import com.solidtype.atenas_apk_2.historial_ventas.data.remoteTicketsFB.interfaces.QueryDBticket
import com.solidtype.atenas_apk_2.historial_ventas.data.remoteTicketsFB.interfaces.RemoteTicketsFB
import com.solidtype.atenas_apk_2.historial_ventas.data.remoteTicketsFB.mediadorTicket.RemoteTicketsFBFBImpl
import com.solidtype.atenas_apk_2.products.data.remote.remoteProFB.dataDb.DataDbProducts.QueryDBlocalImpl
import com.solidtype.atenas_apk_2.products.data.remote.remoteProFB.interfaces.MediatorProducts
import com.solidtype.atenas_apk_2.products.data.remote.remoteProFB.interfaces.QueryDBlocal
import com.solidtype.atenas_apk_2.products.data.remote.remoteProFB.mediator.MediatorProductsImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RemoteModule {


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

}
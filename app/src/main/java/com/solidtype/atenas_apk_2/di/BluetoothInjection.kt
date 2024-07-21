package com.solidtype.atenas_apk_2.di

import android.content.Context
import com.solidtype.atenas_apk_2.gestion_facturar.domain.casos_usos.bluetooth.data.AndroidBluetoothController
import com.solidtype.atenas_apk_2.gestion_facturar.domain.casos_usos.bluetooth.domain.BluetoothController
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton
@Module
@InstallIn(SingletonComponent::class)
object BluetoothInjection {
    //Bluetooth
    @Provides
    @Singleton
    fun provideBluetoothController(@ApplicationContext context: Context): BluetoothController {
        return AndroidBluetoothController(context)
    }

}
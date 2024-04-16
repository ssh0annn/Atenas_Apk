package com.solidtype

import android.app.Application
import com.solidtype.atenas_apk_2.historial_ventas.data.remoteHistoVentaFB.mediator.MediatorHistorialVentas
import com.solidtype.atenas_apk_2.products.data.remoteProFB.QuerysFirstore
import dagger.hilt.android.HiltAndroidApp
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


@HiltAndroidApp
class AtenasAplication : Application ()
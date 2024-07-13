package com.solidtype

import android.app.Application
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import com.solidtype.atenas_apk_2.core.remote.managerSync.SyncWorker
import dagger.hilt.android.HiltAndroidApp
import java.util.concurrent.TimeUnit


@HiltAndroidApp
class AtenasAplication : Application (){
    override fun onCreate() {
        super.onCreate()
       // Printooth.init(this)
        setupPeriodicSync()

    }

    private fun setupPeriodicSync() {
        println("entre a la funcion setupPeridodicSync")

        val syncRequest = PeriodicWorkRequestBuilder<SyncWorker>(10, TimeUnit.SECONDS).build()
        WorkManager.getInstance(this).enqueue(syncRequest)

    }
}


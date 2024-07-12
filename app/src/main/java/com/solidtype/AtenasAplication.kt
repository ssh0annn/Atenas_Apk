package com.solidtype

import android.app.Application
import dagger.hilt.android.HiltAndroidApp


@HiltAndroidApp
class AtenasAplication : Application (){
    override fun onCreate() {
        super.onCreate()
       // Printooth.init(this)
    }
}

/*
  override fun onCreate() {
        super.onCreate()

        // Inicializa y configura WorkManager para sincronización periódica
        setupPeriodicSync()
    }

    private fun setupPeriodicSync() {
        val constraints = Constraints.Builder()
            .setRequiredNetworkType(NetworkType.CONNECTED)
            .build()

        val syncWorkRequest = PeriodicWorkRequestBuilder<SyncWorker>(15, TimeUnit.MINUTES)
            .setConstraints(constraints)
            .build()

        WorkManager.getInstance(this).enqueue(syncWorkRequest)
    }
 */
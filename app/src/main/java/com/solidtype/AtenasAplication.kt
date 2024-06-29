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

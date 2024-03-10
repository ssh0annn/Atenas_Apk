package com.solidtype

import android.app.Application
import com.solidtype.atenas_apk_2.users.data.remote.FIrestoreConnect

class AtenasAplication: Application() {
    override fun onCreate() {
        super.onCreate()
           FIrestoreConnect()
    }

}
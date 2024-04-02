package com.solidtype

import android.app.Application
import com.solidtype.atenas_apk_2.products.data.remote.MediatorRemote.MediatorFbPrododucts
import dagger.hilt.android.HiltAndroidApp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltAndroidApp
class AtenasAplication : Application()

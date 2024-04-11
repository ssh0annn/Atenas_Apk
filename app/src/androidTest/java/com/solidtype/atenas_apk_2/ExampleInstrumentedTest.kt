package com.solidtype.atenas_apk_2

import androidx.compose.runtime.Composable
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.solidtype.atenas_apk_2.historial_ventas.presentation.historial.HistorialScreen

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {
    @Test
    @Composable
    fun TestingCarlos() {
        HistorialScreen()
    }
}
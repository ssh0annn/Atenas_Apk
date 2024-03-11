package com.solidtype.atenas_apk_2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.solidtype.atenas_apk_2.users.presentation.register.OutlinedTextFieldExample


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            OutlinedTextFieldExample(this)

        }
    }
}

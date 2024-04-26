package com.solidtype.atenas_apk_2.util.ui.Components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredWidth
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.DatePickerDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.ProvideTextStyle
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.DialogProperties

@ExperimentalMaterial3Api
@Composable
fun MyDatePickerDialog(
    onDismissRequest: () -> Unit,
    confirmButton: @Composable () -> Unit,
    modifier: Modifier = Modifier,
    dismissButton: @Composable (() -> Unit)? = null,
    shape: Shape = DatePickerDefaults.shape,
    tonalElevation: Dp = DatePickerDefaults.TonalElevation,
    properties: DialogProperties = DialogProperties(usePlatformDefaultWidth = false),
    content: @Composable ColumnScope.() -> Unit
) {
    AlertDialog(
        onDismissRequest = onDismissRequest,
        modifier = modifier.wrapContentHeight(),
        properties = properties
    ) {
        Surface(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .requiredWidth(400.0.dp)
                .heightIn(500.0.dp),
            shape = shape,
            color = Color(0xFFFFFFFF),
            tonalElevation = tonalElevation,
        ) {
            Column(verticalArrangement = Arrangement.SpaceBetween) {
                content()
                // Buttons
                Box(
                    modifier = Modifier
                        .align(Alignment.End)
                        .padding(bottom = 8.dp, end = 6.dp)
                ) {
                    CompositionLocalProvider(
                        LocalContentColor provides Color(0xFF313342)
                    ) {
                        val textStyle = TextStyle(color = Color.White, fontSize = 16.sp)
                        ProvideTextStyle(value = textStyle) {
                            Column(
                                verticalArrangement = Arrangement.spacedBy(8.dp),
                                horizontalAlignment = Alignment.End
                            ) {
                                dismissButton?.invoke()
                                confirmButton()
                            }
                        }
                    }
                }
            }
        }
    }
}
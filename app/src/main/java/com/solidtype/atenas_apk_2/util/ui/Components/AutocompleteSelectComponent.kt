package com.solidtype.atenas_apk_2.util.ui.Components

import android.util.Log
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.solidtype.atenas_apk_2.ui.theme.AzulGris
import com.solidtype.atenas_apk_2.ui.theme.Blanco

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AutocompleteSelect(
    text: String,
    variableStr: String,
    items: List<String>,
    corto: Boolean = false,
    expanded: MutableState<Boolean> = rememberSaveable { mutableStateOf(false) },
    onClickAgregar: (() -> Unit)? = null,
    onSelectionChange: (String) -> Unit
) {
    var searchText: String by rememberSaveable { mutableStateOf(variableStr) }

    val keyboardController = LocalSoftwareKeyboardController.current

    val focusRequester = remember { FocusRequester() }

    ExposedDropdownMenuBox(
        expanded = expanded.value,
        onExpandedChange = {
            expanded.value = !expanded.value
        }
    ) {
        TextField(
            value = searchText,
            onValueChange = {
                searchText = it
                expanded.value = true
            },
            singleLine = true,
            textStyle = TextStyle(
                color = AzulGris,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            ),label = {
                Text(
                    text = text,
                    color = AzulGris,
                    fontSize = 15.sp,
                    fontWeight = FontWeight.ExtraBold
                )
            },
            trailingIcon = {
                ExposedDropdownMenuDefaults.TrailingIcon(
                    expanded = expanded.value
                )
            },
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = Blanco,
                unfocusedBorderColor = Blanco,
            ),
            modifier = Modifier
                .focusRequester(focusRequester)
                .onFocusChanged { focusState ->
                    if (!focusState.isFocused) {
                        expanded.value = false
                    }
                }
                .menuAnchor()
                .width(
                    when (corto) {
                        true -> 250.dp
                        false -> 300.dp
                    }
                )
                .background(Color(0xFFFFFFFF), RoundedCornerShape(15.dp))
                .fillMaxWidth()
                .wrapContentHeight()
                .clip(RoundedCornerShape(15.dp))
        )
        val filteredItems = items.filter { it.contains(searchText, ignoreCase = true) }
        if (filteredItems.isNotEmpty()) {
            ExposedDropdownMenu(
                expanded = expanded.value,
                onDismissRequest = {
                    // Nosotros no deberíamos ocultar el menú cuando el usuario ingresa o elimina algún carácter
                }
            ) {
                for (item in filteredItems) {
                    DropdownMenuItem(text = { Text(item) }, onClick = {
                        searchText = item
                        expanded.value = false
                        onSelectionChange(item)
                    })
                }
                if (onClickAgregar != null)
                Row (
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(5.dp)
                        .clickable(onClick = {
                            onClickAgregar()
                            expanded.value = false
                            //Debería hacer un back
                            keyboardController?.hide()
                        }),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Default.Add,
                        contentDescription = "Add",
                        tint = AzulGris
                    )
                    Text(
                        text = "Agregar",
                        color = AzulGris,
                        fontSize = 15.sp,
                        fontWeight = FontWeight.ExtraBold,
                        modifier = Modifier
                    )
                    Spacer(modifier = Modifier.width(10.dp))
                }
            }
        }
    }
}
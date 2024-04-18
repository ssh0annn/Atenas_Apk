package com.solidtype.atenas_apk_2.products.presentation.inventory.componets

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AutocompleteSelect(
    text: String,
    categoria: String,
    items: List<String>,
    corto: Boolean = false,
    onSelectionChange: (String) -> Unit
) {
    var searchText: String by rememberSaveable { mutableStateOf("") }
    var expanded by rememberSaveable { mutableStateOf(false) }

    searchText = categoria

    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = {
            expanded = !expanded
        }
    ) {
        TextField(
            value = searchText,
            onValueChange = {
                searchText = it
                expanded = true
            },
            singleLine = true,
            textStyle = TextStyle(
                color = Color.Black,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            ),label = {
                Text(
                    text = text,
                    color = Color(android.graphics.Color.parseColor("#343341")),
                    fontSize = 15.sp,
                    fontWeight = FontWeight.ExtraBold
                )
            },
            trailingIcon = {
                ExposedDropdownMenuDefaults.TrailingIcon(
                    expanded = expanded
                )
            },
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = Color.White,
                unfocusedBorderColor = Color.White,
            ),
            modifier = Modifier
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
                expanded = expanded,
                onDismissRequest = {
                    // Nosotros no deberíamos ocultar el menú cuando el usuario ingresa o elimina algún carácter
                }
            ) {
                for (item in filteredItems) {
                    DropdownMenuItem(text = { Text(item) }, onClick = {
                        searchText = item
                        expanded = false
                        onSelectionChange(item)
                    })
                }
                //Si le dan al botón Atrás, debe cerrarse
            }
        }
    }
}
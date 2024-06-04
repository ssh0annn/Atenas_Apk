package com.solidtype.atenas_apk_2.gestion_proveedores.presentation.cliente.componets

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.focusModifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.solidtype.atenas_apk_2.servicios.Input
import com.solidtype.atenas_apk_2.servicios.Inputlargo
import com.solidtype.atenas_apk_2.ui.theme.AzulGris

@OptIn(ExperimentalMultiplatform::class, ExperimentalMaterial3Api::class)
@Composable
fun modalAddClient(){

        //formulario cliente
        var nombre by rememberSaveable { mutableStateOf("") }
        var modelo by rememberSaveable { mutableStateOf("") }
        val DocumetSelector = arrayOf("Cedula", "Pasaporte")
        var expanded by remember { mutableStateOf(false) }
        var selectedText by remember { mutableStateOf(DocumetSelector[0]) }
        val context = LocalContext.current




    Box (
            modifier = Modifier
                .fillMaxWidth()
                .verticalScroll(rememberScrollState())
//            .background(GrisOscuro)



        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                //titulo
                Spacer(modifier = Modifier.padding(top = 15.dp))
                Text(
                    text = "Cliente",
                    color = AzulGris,
                    fontWeight = FontWeight.ExtraBold,
                    fontSize = 35.sp,
                )

                //cuerpo1
                Column(
                    modifier = Modifier
                        .padding(top = 25.dp)
                ) {

                    Box() {
                        Input(

                            label = "Nombre",
                            valor = nombre,
                            derecho = true,
                            modifier = Modifier
                        ) {
                            nombre = it
                        }
                    }
                    Spacer(modifier = Modifier.padding(10.dp))
                    Box() {

                          ExposedDropdownMenuBox(

                              expanded = expanded,
                              onExpandedChange = {
                                  expanded = !expanded
                              }
                          ) {
                              TextField(
                                  value = selectedText,
                                  onValueChange = {},
                                  readOnly = true,
                                  trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
                                  modifier = Modifier.menuAnchor()
                              )

                              ExposedDropdownMenu(
                                  modifier = Modifier
                                      .height(125.dp),
                                  expanded = expanded,
                                  onDismissRequest = { expanded = false }
                              ) {
                                  DocumetSelector.forEach { item ->
                                      DropdownMenuItem(
                                          text = { Text(text = item) },
                                          onClick = {
                                              selectedText = item
                                              expanded = false
                                              Toast.makeText(context, item, Toast.LENGTH_SHORT).show()
                                          }
                                      )
                                  }
                              }
                          }

                    }
                    Spacer(modifier = Modifier.padding(10.dp))
                    Box() {
                        Input(
                            label = "Email",
                            valor = modelo,
                            derecho = true,
                            modifier = Modifier

                        ) {
                            modelo = it
                        }
                    }
                    Spacer(modifier = Modifier.padding(10.dp))
                    Box() {
                        Input(
                            label = "Telefono",
                            valor = modelo,
                            derecho = true,
                            modifier = Modifier

                        ) {
                            modelo = it
                        }
                    }
                    Spacer(modifier = Modifier.padding(30.dp))
                }
            }
        }
    }


@Composable
@Preview
fun modal(){
    MaterialTheme {
        modalAddClient()
    }
}

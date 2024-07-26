package com.solidtype.atenas_apk_2.products.presentation.inventory.componets

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.solidtype.atenas_apk_2.products.domain.model.actualizacion.categoria
import com.solidtype.atenas_apk_2.ui.theme.AzulGris
import com.solidtype.atenas_apk_2.ui.theme.Blanco
import com.solidtype.atenas_apk_2.ui.theme.GrisClaro
import com.solidtype.atenas_apk_2.ui.theme.GrisOscuro
import com.solidtype.atenas_apk_2.util.formatoActivo
import com.solidtype.atenas_apk_2.util.ui.Pantalla
import com.solidtype.atenas_apk_2.util.ui.components.AutocompleteSelect
import com.solidtype.atenas_apk_2.util.ui.components.BotonBlanco
import com.solidtype.atenas_apk_2.util.ui.components.Dialogo
import com.solidtype.atenas_apk_2.util.ui.components.InputDetalle

@Composable
@OptIn(ExperimentalMultiplatform::class)
fun DialogoCategoria(
    mostrarCategoria: MutableState<Boolean>,
    idCategoria: MutableState<String>,
    nombreCategoria: MutableState<String>,
    descripcionCategoria: MutableState<String>,
    estadoCategoria: MutableState<String>,
    uiCategoria: List<categoria>,
    onGuardar: () -> Unit,
    onInactivar: () -> Unit
) {
    Dialogo(
        "Gestor de Categoría",
        mostrarCategoria.value,
        { mostrarCategoria.value = false },
        false
    ) {
        Row {//Detalles y Lista
            Column( //Detalles
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .padding(20.dp)
                    .background(AzulGris, RoundedCornerShape(20.dp))
            ) {
                LazyColumn(
                    modifier = Modifier
                        .padding(10.dp)
                        .background(GrisOscuro, RoundedCornerShape(5.dp))
                ) {
                    item {
                        Column(
                            modifier = Modifier
                                .padding(10.dp)
                        ) {
                            InputDetalle("ID", idCategoria.value) { idCategoria.value = it }
                            InputDetalle(
                                "Categoría",
                                nombreCategoria.value
                            ) { nombreCategoria.value = it }
                            InputDetalle("Descripción", descripcionCategoria.value) {
                                descripcionCategoria.value = it
                            }
                            Spacer(modifier = Modifier.height(5.dp))
                            AutocompleteSelect(
                                "Estado",
                                estadoCategoria.value,
                                listOf("Activo", "Inactivo")
                            ) { estadoCategoria.value = it }
                        }
                    }
                }
                Row {
                    BotonBlanco(
                        text = "Guardar",
                        habilitar =
                            idCategoria.value.matches("[0-9]+".toRegex()) &&
                            nombreCategoria.value != "" &&
                            descripcionCategoria.value != "" &&
                            estadoCategoria.value != "",
                    ) {
                        onGuardar()
                    }
                    Spacer(modifier = Modifier.width(40.dp))
                    BotonBlanco("Inactivar") {
                        onInactivar()
                    }
                }
                Spacer(modifier = Modifier.height(10.dp))
            }
            Column( //Lista
                modifier = Modifier
                    .padding(0.dp, 20.dp, 0.dp, 0.dp)
                    .background(AzulGris, RoundedCornerShape(20.dp))
            ) {
                LazyColumn(
                    modifier = Modifier
                        .height(Pantalla.alto * 0.482f)
                        .padding(10.dp)
                        .background(GrisOscuro, RoundedCornerShape(5.dp))
                ) {
                    item {
                        Column(
                            modifier = Modifier.padding(5.dp)
                        ) {
                            Row(
                                modifier = Modifier
                                    .padding(5.dp)
                                    .background(GrisOscuro, RoundedCornerShape(5.dp))
                            ) {
                                Text(
                                    text = "ID",
                                    modifier = Modifier.weight(1f),
                                    color = Blanco,
                                    textAlign = TextAlign.Center
                                )
                                Text(
                                    text = "Nombre",
                                    modifier = Modifier.weight(1f),
                                    color = Blanco,
                                    textAlign = TextAlign.Center
                                )
                                Text(
                                    text = "Descripción",
                                    modifier = Modifier.weight(1f),
                                    color = Blanco,
                                    textAlign = TextAlign.Center
                                )
                                Text(
                                    text = "Estado",
                                    modifier = Modifier.weight(1f),
                                    color = Blanco,
                                    textAlign = TextAlign.Center
                                )
                            }
                        }
                    }
                    if (uiCategoria.isNotEmpty())
                        items(uiCategoria) { categoriaIndex ->
                            Row(
                                modifier = Modifier
                                    .padding(
                                        start = 10.dp,
                                        end = 5.dp,
                                        top = 5.dp,
                                        bottom = 5.dp
                                    )
                                    .background(
                                        GrisClaro,
                                        RoundedCornerShape(10.dp)
                                    )
                                    .clickable {
                                        idCategoria.value = categoriaIndex.id_categoria.toString()
                                        nombreCategoria.value = categoriaIndex.nombre
                                        descripcionCategoria.value =
                                            categoriaIndex.descripcion.toString()
                                        estadoCategoria.value =
                                            categoriaIndex.estado.formatoActivo()
                                    },
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(
                                    text = categoriaIndex.id_categoria.toString(),
                                    modifier = Modifier
                                        .padding(5.dp, 5.dp, 0.dp, 5.dp)
                                        .weight(1f),
                                    textAlign = TextAlign.Center
                                )
                                Text(
                                    text = categoriaIndex.nombre,
                                    modifier = Modifier.weight(1f),
                                    textAlign = TextAlign.Center
                                )
                                Text(
                                    text = categoriaIndex.descripcion.toString(),
                                    modifier = Modifier.weight(1f),
                                    textAlign = TextAlign.Center
                                )
                                Text(
                                    text = categoriaIndex.estado.formatoActivo(),
                                    modifier = Modifier
                                        .padding(0.dp, 5.dp, 5.dp, 5.dp)
                                        .weight(1f),
                                    textAlign = TextAlign.Center
                                )
                            }
                        }
                }
            }
        }
    }
}
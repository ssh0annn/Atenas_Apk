package com.solidtype.atenas_apk_2.products.presentation.inventory.componets

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.solidtype.atenas_apk_2.products.presentation.inventory.InventarioViewModel
import com.solidtype.atenas_apk_2.util.showFilePicker
import com.solidtype.atenas_apk_2.util.ui.components.Boton

@Composable
fun Botones(
    context: Context,
    viewModel: InventarioViewModel,
    showSnackbarIni: MutableState<Boolean>,
    mostrar: MutableState<Boolean>,
    mostrarProducto: MutableState<Boolean>,
    idInventario: MutableState<String>,
    categoria: MutableState<String>,
    nombre: MutableState<String>,
    descripcion: MutableState<String>,
    costo: MutableState<String>,
    precio: MutableState<String>,
    modelo: MutableState<String>,
    marca: MutableState<String>,
    cantidad: MutableState<String>,
    impuesto: MutableState<String>,
    provider: MutableState<String>,
    idCategoria: MutableState<String>,
    idProveedor: MutableState<String>
) {
    Box(
        modifier = Modifier.fillMaxWidth(),
        contentAlignment = Alignment.TopEnd
    ) {
        Row {
            Boton("Importar") {
                showFilePicker(context)
            }
            Boton("Exportar") {
                Toast.makeText(context, "Espere un momento...", Toast.LENGTH_SHORT)
                    .show()
                viewModel.exportarExcel()
                showSnackbarIni.value = true
            }
            Boton("Ejemplar") {
                mostrar.value = true
            }
            Boton ("Agregar") {
                mostrarProducto.value = true

                idInventario.value = ""
                categoria.value = ""
                nombre.value = ""
                descripcion.value = ""
                costo.value = ""
                precio.value = ""
                modelo.value = ""
                marca.value = ""
                cantidad.value = ""
                impuesto.value = ""
                //estado.value = "Activo"
                provider.value = ""
                idCategoria.value = ""
                idProveedor.value = ""
            }
        }
    }
}
package com.solidtype.atenas_apk_2.gestion_proveedores.presentation.cliente.componets

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.ArrowDropUp
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.Wallpapers
import androidx.compose.ui.unit.dp
import com.solidtype.atenas_apk_2.facturacion.presentation.componets.FacturaConDetalle
import com.solidtype.atenas_apk_2.facturacion.presentation.facturas
import com.solidtype.atenas_apk_2.gestion_proveedores.presentation.cliente.ClienteEvent
import com.solidtype.atenas_apk_2.gestion_proveedores.presentation.cliente.modelo.Personastodas
import com.solidtype.atenas_apk_2.ui.theme.AzulGris
import com.solidtype.atenas_apk_2.ui.theme.Blanco
import com.solidtype.atenas_apk_2.ui.theme.GrisAzulado
import com.solidtype.atenas_apk_2.ui.theme.GrisOscuro
import com.solidtype.atenas_apk_2.util.formatoActivo
import com.solidtype.atenas_apk_2.util.formatoParaUser
import com.solidtype.atenas_apk_2.util.ui.Pantalla

val listClients : List<Personastodas.ClienteUI> = listOf(
    Personastodas.ClienteUI(
        1,
        "Johan Diaz",
        "423-4234-324",
        "3242343",
        "therealdiaz@live.com",
    ),   Personastodas.ClienteUI(
        1,
        "Johan Diaz",
        "423-4234-324",
        "3242343",
        "therealdiaz@live.com",
    ),   Personastodas.ClienteUI(
        1,
        "Johan Diaz",
        "423-4234-324",
        "3242343",
        "therealdiaz@live.com",
    ),   Personastodas.ClienteUI(
        1,
        "Johan Diaz",
        "423-4234-324",
        "3242343",
        "therealdiaz@live.com",
    ),   Personastodas.ClienteUI(
        1,
        "Johan Diaz",
        "423-4234-324",
        "3242343",
        "therealdiaz@live.com",
    ),   Personastodas.ClienteUI(
        1,
        "Johan Diaz",
        "423-4234-324",
        "3242343",
        "therealdiaz@live.com",
    )
)

 @Composable
    fun TableClients(Clients: List<Personastodas.ClienteUI>) {

     println("tabla Cliente")
     println(Clients)
     println("")

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(Pantalla.ancho - 800.dp)
                .background(AzulGris, shape = RoundedCornerShape(20.dp))
        ) {
            Column(
                modifier = Modifier
                    .padding(10.dp)
                    .fillMaxSize()
                    .background(GrisOscuro, shape = RoundedCornerShape(20.dp))
            ) {
                Row(
                    modifier = Modifier
                        .padding(10.dp)
                        .fillMaxWidth()

                ) {
                    Text(
                        text = "Nombre",
                        modifier = Modifier.weight(1f),
                        color = Blanco,
                        textAlign = TextAlign.Center
                    )
                    Text(
                        text = "Documento",
                        modifier = Modifier.weight(1f),
                        color = Blanco,
                        textAlign = TextAlign.Center
                    )
                    Text(
                        text = "Email",
                        modifier = Modifier.weight(1f),
                        color = Blanco,
                        textAlign = TextAlign.Center
                    )
                    Text(
                        text = "Telefono",
                        modifier = Modifier.weight(1f),
                        color = Blanco,
                        textAlign = TextAlign.Center
                    )

                    Text(
                        "",
                        modifier = Modifier.weight(0.2f)
                    )
                }
                LazyColumn(
                    modifier = Modifier
                        .padding(start = 10.dp, end = 10.dp, bottom = 10.dp)
                        .fillMaxSize()
                        .background(GrisOscuro)
                ) {
                   items(Clients){cliente->
                       MyClientItem(Client = cliente)

                   }
                }
            }
        }
    }



@Preview(
    backgroundColor = 0xFFFFFFFF, showBackground = true, widthDp = 1080, heightDp = 560
)
@Composable
fun previewTable(){
    MaterialTheme {
        TableClients(listClients)
    }
}


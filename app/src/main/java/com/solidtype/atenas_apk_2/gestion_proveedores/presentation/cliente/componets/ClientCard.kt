package com.solidtype.atenas_apk_2.gestion_proveedores.presentation.cliente.componets

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.solidtype.atenas_apk_2.gestion_proveedores.domain.modelo.persona
import com.solidtype.atenas_apk_2.products.domain.model.ProductEntity
import kotlin.math.truncate

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ClientCard(client: Map<String,Any>,ondeleteClick: ()-> Unit, onEditClick: ()-> Unit){
   Card(
       modifier = Modifier.fillMaxWidth().padding(start = 10.dp, end = 10.dp),
       shape = RoundedCornerShape(30.dp),
       elevation = CardDefaults.cardElevation(defaultElevation = 20.dp),
       colors = CardDefaults.cardColors(containerColor = Color.White),
       onClick = {/* */},

   ) {
       Row(modifier = Modifier.fillMaxWidth().padding(12.dp),
           Arrangement.Center){

           Text(text =  client["nombre"].toString() , modifier = Modifier.weight(1f).padding(start = 20.dp))
           Text(text = client["documento"].toString(), modifier = Modifier.weight(1f).padding(start = 10.dp))
           Text(text = client["direccion"].toString(), modifier = Modifier.weight(1f).padding(start = 10.dp))
           Text(text = client["email"].toString(), modifier = Modifier.weight(1f).padding(start = 10.dp))
           Text(text = client["telefono"].toString(), modifier = Modifier.weight(1f).padding(start = 10.dp))

       }
   }
}

val mapClient = mapOf(
    "nombre" to "Johan",
    "documento" to "84864",
    "direccion" to "calle 4",
    "email" to "therealdiaz",
    "telefono" to "8096134992",
)

val mapeo: Map<String,Any> = mapOf<String,Any>()


val listMapOfClients: List<Map<String,Any>> = listOf(
    mapOf(
        "nombre" to "Johan",
        "documento" to "84864",
        "direccion" to "calle 4",
        "email" to "therealdiaz",
        "telefono" to "8096134992",
    ),mapOf(
        "nombre" to "Johan",
        "documento" to "84864",
        "direccion" to "calle 4",
        "email" to "therealdiaz",
        "telefono" to "8096134992",
    ),mapOf(
        "nombre" to "Johan",
        "documento" to "84864",
        "direccion" to "calle 4",
        "email" to "therealdiaz",
        "telefono" to "8096134992",
    ),mapOf(
        "nombre" to "Johan",
        "documento" to "84864",
        "direccion" to "calle 4",
        "email" to "therealdiaz",
        "telefono" to "8096134992",
    ),mapOf(
        "nombre" to "Johan",
        "documento" to "84864",
        "direccion" to "calle 4",
        "email" to "therealdiaz",
        "telefono" to "8096134992",
    ),mapOf(
        "nombre" to "Johan",
        "documento" to "84864",
        "direccion" to "calle 4",
        "email" to "therealdiaz",
        "telefono" to "8096134992",
    )
)

@Preview
@Composable
fun previevCard(
){

    ClientCard(mapClient,{},{})
}
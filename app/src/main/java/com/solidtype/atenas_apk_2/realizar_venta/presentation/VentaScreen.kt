package com.solidtype.atenas_apk_2.realizar_venta.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.solidtype.atenas_apk_2.ui.theme.Boton
import com.solidtype.atenas_apk_2.ui.theme.Fondo
import com.solidtype.atenas_apk_2.ui.theme.SubFondo
import com.solidtype.atenas_apk_2.ui.theme.SubPaneles
import com.solidtype.atenas_apk_2.util.ui.components.Buscador
import com.solidtype.atenas_apk_2.util.ui.components.Titulo

@Preview(backgroundColor = 0xffD7D7D9, showBackground = true, widthDp = 1280, heightDp = 800)
@Composable
fun VentaScreen(){
    val buscador = rememberSaveable { mutableStateOf("") }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Fondo),
    ) {
        Row (modifier = Modifier
            .fillMaxWidth()
            .align(Alignment.CenterHorizontally)
            .padding(20.dp)
        ){
            Titulo(text = "Venta")
            Spacer(modifier = Modifier.width(100.dp))
            Buscador(buscador.value) {
                buscador.value = it
            }
        }
        Spacer(modifier = Modifier.height(50.dp))
        Column (modifier = Modifier
            .clip(RoundedCornerShape(20.dp))
            .background(SubFondo)
            .padding(30.dp)
            .align(Alignment.CenterHorizontally)
            .height(400.dp)
            .width(1100.dp),
        ){
            Row {
                Text(text = "Descripcion", color = Fondo, fontSize = 18.sp, fontStyle = FontStyle.Normal)
                Spacer(modifier = Modifier.width(110.dp))
                Text(text = "Categoria", color = Fondo, fontSize = 18.sp, fontStyle = FontStyle.Normal)
                Spacer(modifier = Modifier.width(110.dp))
                Text(text = "Cantidad", color = Fondo, fontSize = 18.sp, fontStyle = FontStyle.Normal)
                Spacer(modifier = Modifier.width(110.dp))
                Text(text = "SubTotal", color = Fondo, fontSize = 18.sp, fontStyle = FontStyle.Normal)
                Spacer(modifier = Modifier.width(110.dp))
                Text(text = "Impuesto", color = Fondo, fontSize = 18.sp, fontStyle = FontStyle.Normal)
                Spacer(modifier = Modifier.width(110.dp))
                Text(text = "Total", color = Fondo, fontSize = 18.sp, fontStyle = FontStyle.Normal)
                Spacer(modifier = Modifier.width(110.dp))
            }
            Spacer(modifier = Modifier.height(20.dp))
            Column (modifier = Modifier
                .clip(RoundedCornerShape(20.dp))
                .background(SubPaneles)
                .padding(10.dp)
                .height(400.dp)
                .width(1100.dp)
                .align(Alignment.CenterHorizontally)
            ){
                Row (modifier = Modifier
                    .clip(RoundedCornerShape(20.dp))
                    .background(Fondo)
                    .padding(10.dp)
                ){
                    Text(text = "Samsung S3", color = SubFondo, fontSize = 18.sp, fontStyle = FontStyle.Normal)
                    Spacer(modifier = Modifier.width(125.dp))
                    Text(text = "Celular", color = SubFondo, fontSize = 18.sp, fontStyle = FontStyle.Normal)
                    Spacer(modifier = Modifier.width(125.dp))
                    Text(text = "1", color = SubFondo, fontSize = 18.sp, fontStyle = FontStyle.Normal)
                    Spacer(modifier = Modifier.width(125.dp))
                    Text(text = "$ "+"12000.00", color = SubFondo, fontSize = 18.sp, fontStyle = FontStyle.Normal)
                    Spacer(modifier = Modifier.width(125.dp))
                    Text(text = "$ "+"100.00", color = SubFondo, fontSize = 18.sp, fontStyle = FontStyle.Normal)
                    Spacer(modifier = Modifier.width(125.dp))
                    Text(text = "$ "+"12100.00", color = SubFondo, fontSize = 18.sp, fontStyle = FontStyle.Normal)
                }
            }
        }
        Spacer(modifier = Modifier.height(50.dp))
        Row(modifier = Modifier
            .clip(RoundedCornerShape(40.dp))
            .align(Alignment.CenterHorizontally)
            .background(SubFondo)
            .width(1000.dp)
            .padding(20.dp)
        ){
            Text(modifier = Modifier.padding(7.dp), text = "Totales: $ "+"12100.00", color = Fondo, fontSize = 28.sp, fontStyle = FontStyle.Normal)
            Spacer(modifier = Modifier.width(60.dp))
            Text(modifier = Modifier.padding(7.dp), text = "Cantidades: "+"1", color = Fondo, fontSize = 28.sp, fontStyle = FontStyle.Normal)
            Spacer(modifier = Modifier.width(140.dp))
            Button(onClick = { /*TODO*/ }, colors = ButtonDefaults.buttonColors(Fondo)) {
                Text(text = "Facturar", color = SubFondo, fontSize = 18.sp, fontStyle = FontStyle.Normal)
            }
            Spacer(modifier = Modifier.width(70.dp))
            Button(onClick = { /*TODO*/ }, colors = ButtonDefaults.buttonColors(Fondo)) {
                Text(text = "Cancelar", color = SubFondo, fontSize = 18.sp, fontStyle = FontStyle.Normal)
            }
        }
    }

}

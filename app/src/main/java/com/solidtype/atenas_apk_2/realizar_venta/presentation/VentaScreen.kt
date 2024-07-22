package com.solidtype.atenas_apk_2.realizar_venta.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.solidtype.atenas_apk_2.R
import com.solidtype.atenas_apk_2.ui.theme.Fondo
import com.solidtype.atenas_apk_2.ui.theme.SubFondo
import com.solidtype.atenas_apk_2.ui.theme.SubPaneles
import com.solidtype.atenas_apk_2.util.PickerButton
import com.solidtype.atenas_apk_2.util.ui.components.Buscador
import com.solidtype.atenas_apk_2.util.ui.components.MenuLateral
import com.solidtype.atenas_apk_2.util.ui.components.Titulo

@Composable
fun VentaScreen(
    navController: NavController
){
    val buscador = rememberSaveable { mutableStateOf("") }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Fondo),
    ) {
        Box(modifier = Modifier
            .fillMaxWidth()
            .padding(start = 55.dp)
        ) {
            Row(
                modifier = Modifier
                    .padding(30.dp)
            ) {
                Titulo(text = "Venta")
                Spacer(modifier = Modifier.width(60.dp))
                Buscador(buscador.value) {
                    buscador.value = it
                }
            }
        }
        Column (modifier = Modifier
            .clip(RoundedCornerShape(20.dp))
            .background(SubFondo)
            .padding(30.dp)
            .align(Alignment.CenterHorizontally)
            .height(450.dp)
            .width(1100.dp),
        ){
            Row {
                Text(text = "Descripcion", color = Fondo, fontSize = 18.sp, fontWeight = FontWeight.Bold, textAlign = TextAlign.Center, modifier = Modifier.weight(1f))
                Text(text = "Categoria", color = Fondo, fontSize = 18.sp, fontWeight = FontWeight.Bold, textAlign = TextAlign.Center, modifier = Modifier.weight(1f))
                Text(text = "Cantidad", color = Fondo, fontSize = 18.sp, fontWeight = FontWeight.Bold, textAlign = TextAlign.Center, modifier = Modifier.weight(1f))
                Text(text = "SubTotal", color = Fondo, fontSize = 18.sp, fontWeight = FontWeight.Bold, textAlign = TextAlign.Center, modifier = Modifier.weight(1f))
                Text(text = "Impuesto", color = Fondo, fontSize = 18.sp, fontWeight = FontWeight.Bold, textAlign = TextAlign.Center, modifier = Modifier.weight(1f))
                Text(text = "Total", color = Fondo, fontSize = 18.sp, fontWeight = FontWeight.Bold, textAlign = TextAlign.Center, modifier = Modifier.weight(1f))
            }
            Spacer(modifier = Modifier.height(20.dp))
            LazyColumn (modifier = Modifier
                .clip(RoundedCornerShape(20.dp))
                .background(SubPaneles)
                .padding(10.dp)
                .height(400.dp)
                .width(1100.dp)
                .align(Alignment.CenterHorizontally)
            ){
                item {
                    Row (modifier = Modifier
                        .clip(RoundedCornerShape(20.dp))
                        .background(Fondo)
                        .padding(10.dp)
                    ){
                        Text(text = "Samsung S3", color = SubFondo, fontSize = 18.sp, fontWeight = FontWeight.Bold, textAlign = TextAlign.Center, modifier = Modifier.weight(1f), maxLines = 2)
                        Text(text = "Celular", color = SubFondo, fontSize = 18.sp, fontWeight = FontWeight.Bold, textAlign = TextAlign.Center, modifier = Modifier.weight(1f), maxLines = 2)
                        Box(modifier = Modifier.weight(1f), contentAlignment = Alignment.Center
                        ){
                            CambiarCantidad()
                        }
                        Text(text = "$ "+"12000.00", color = SubFondo, fontSize = 18.sp, fontWeight = FontWeight.Bold, textAlign = TextAlign.Center, modifier = Modifier.weight(1f), maxLines = 2)
                        Text(text = "$ "+"100.00", color = SubFondo, fontSize = 18.sp, fontWeight = FontWeight.Bold, textAlign = TextAlign.Center, modifier = Modifier.weight(1f), maxLines = 2)
                        Text(text = "$ "+"12100.00", color = SubFondo, fontSize = 18.sp, fontWeight = FontWeight.Bold, textAlign = TextAlign.Center, modifier = Modifier.weight(1f), maxLines = 2)
                    }
                }

            }
        }
        Spacer(modifier = Modifier.height(30.dp))
        Box(modifier = Modifier
            .padding(end = 55.dp)
            .fillMaxWidth(),
            contentAlignment = Alignment.CenterEnd
        ){
            Row(modifier = Modifier
                .clip(RoundedCornerShape(40.dp))
                .background(SubFondo)
                .width(1000.dp)
                .padding(20.dp),
                horizontalArrangement = Arrangement.Center
            ){
                Text(modifier = Modifier.padding(7.dp), text = "Totales: $ "+"12100.00", color = Fondo, fontSize = 28.sp, fontWeight = FontWeight.Bold)
                Spacer(modifier = Modifier.width(60.dp))
                Text(modifier = Modifier.padding(7.dp), text = "Cantidades: "+"1", color = Fondo, fontSize = 28.sp, fontWeight = FontWeight.Bold)
                Spacer(modifier = Modifier.width(140.dp))
                Button(onClick = { /*TODO*/ }, colors = ButtonDefaults.buttonColors(Fondo)) {
                    Text(text = "Facturar", color = SubFondo, fontSize = 18.sp, fontWeight = FontWeight.Bold)
                }
                Spacer(modifier = Modifier.width(70.dp))
                Button(onClick = { /*TODO*/ }, colors = ButtonDefaults.buttonColors(Fondo)) {
                    Text(text = "Cancelar", color = SubFondo, fontSize = 18.sp, fontWeight = FontWeight.Bold)
                }
            }
        }
    }
    MenuLateral(navController)
}

@Composable
fun CambiarCantidad(
    modifier: Modifier = Modifier,
    width: Dp = 45.dp,
    min: Int = 1,
    max: Int = 30,
    default: Int = min,
    onValueChange: (Int) -> Unit = {}
){
    val number = remember{ mutableIntStateOf(default) }
    Row{
        PickerButton(
            size = width,
            drawable = R.drawable.ic_go,
            enabled = number.intValue > min,
            onClick = {
                if (number.intValue > min) number.intValue --
                onValueChange(number.intValue)
            }
        )
        Text(
            fontSize = 18.sp,
            text = number.intValue.toString(),
            modifier = Modifier
                .padding(start = 10.dp, end = 10.dp)
                .height(IntrinsicSize.Max)
                .align(Alignment.CenterVertically)
        )
        PickerButton(
            size = width,
            drawable = R.drawable.ic_back,
            enabled = number.intValue < max,
            onClick = {
                if (number.intValue < max) number.intValue ++
                onValueChange(number.intValue)
            }
        )
    }
}

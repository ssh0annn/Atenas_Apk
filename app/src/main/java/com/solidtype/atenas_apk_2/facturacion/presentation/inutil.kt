package com.solidtype.atenas_apk_2.facturacion.presentation

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.solidtype.atenas_apk_2.facturacion.domain.model.detalle_venta
import com.solidtype.atenas_apk_2.historial_ventas.domain.model.actualizacion.venta

data class FacturaConDetalle(
    val factura: venta,
    val detalles: detalle_venta?
)

val facturas = listOf(
    Factura(
        1, "maria", "2021-10-10", 100.0, "Efectivo", 1,
        listOf(
            Producto(1, 1, "iPhone X", 2, 50.0, 100.0, 0.0),
            Producto(2, 1, "iPhone 11", 2, 50.0, 100.0, 0.0)
        )
    ),
    Factura(
        2, "maria","2021-10-10", 100.0, "Efectivo", 1,
        listOf(
            Producto(3, 2, "iPhone 12", 2, 50.0, 100.0, 0.0),
            Producto(4, 2, "iPhone 13", 2, 50.0, 100.0, 0.0)
        )
    ),
    Factura(
        3, "maria","2021-10-10", 100.0, "Efectivo", 1,
        listOf(
            Producto(5, 3, "iPhone 14", 2, 50.0, 100.0, 0.0),
            Producto(6, 3, "iPhone 15", 3, 50.0, 150.0, 0.0),
            Producto(7, 3, "iPhone 16", 2, 35.0, 70.0, 0.0)
        )
    ),
    Factura(
        4, "maria","2021-10-10", 100.0, "Efectivo", 1,
        listOf(
            Producto(8, 4, "iPhone 17", 2, 50.0, 100.0, 0.0),
            Producto(9, 4, "iPhone 18", 2, 50.0, 100.0, 0.0)
        )
    ),
    Factura(
        5, "marta","2021-10-10", 100.0, "Efectivo", 1,
        listOf(
            Producto(10, 5, "iPhone 19", 2, 50.0, 100.0, 0.0),
            Producto(11, 5, "iPhone 20", 2, 50.0, 100.0, 0.0)
        )
    ),
    Factura(
        6, "marta","2021-10-10", 100.0, "Efectivo", 1,
        listOf(
            Producto(12, 6, "iPhone 21", 2, 50.0, 100.0, 0.0),
            Producto(13, 6, "iPhone 22", 2, 50.0, 100.0, 0.0)
        )
    ),
    Factura(
        7, "marta","2021-10-10", 100.0, "Efectivo", 1,
        listOf(
            Producto(14, 7, "iPhone 23", 2, 50.0, 100.0, 0.0),
            Producto(15, 7, "iPhone 24", 2, 50.0, 100.0, 0.0)
        )
    ),
    Factura(
        8, "marta","2021-10-10", 100.0, "Efectivo", 1,
        listOf(
            Producto(16, 8, "iPhone 25", 2, 50.0, 100.0, 0.0),
            Producto(17, 8, "iPhone 26", 2, 50.0, 100.0, 0.0)
        )
    ),
    Factura(
        9, "marta","2021-10-10", 100.0, "Efectivo", 1,
        listOf(
            Producto(18, 9, "iPhone 27", 2, 50.0, 100.0, 0.0),
            Producto(19, 9, "iPhone 28", 2, 50.0, 100.0, 0.0)
        )
    ),
    Factura(
        10, "sofia","2021-10-10", 100.0, "Efectivo", 1,
        listOf(
            Producto(20, 10, "iPhone 29", 2, 50.0, 100.0, 0.0),
            Producto(21, 10, "iPhone 30", 2, 50.0, 100.0, 0.0)
        )
    ),
    Factura(
        11, "sofia","2021-10-10", 100.0, "Efectivo", 1,
        listOf(
            Producto(22, 11, "iPhone 31", 2, 50.0, 100.0, 0.0),
            Producto(23, 11, "iPhone 32", 2, 50.0, 100.0, 0.0)
        )
    ),
    Factura(
        12, "sofia","2021-10-10", 100.0, "Efectivo", 1,
        listOf(
            Producto(24, 12, "iPhone 33", 2, 50.0, 100.0, 0.0),
            Producto(25, 12, "iPhone 34", 2, 50.0, 100.0, 0.0)
        )
    ),
    Factura(
        13, "sofia","2021-10-10", 100.0, "Efectivo", 1,
        listOf(
            Producto(26, 13, "iPhone 35", 2, 50.0, 100.0, 0.0),
            Producto(27, 13, "iPhone 36", 2, 50.0, 100.0, 0.0)
        )
    ),
    Factura(
        14, "sofia","2021-10-10", 100.0, "Efectivo", 1,
        listOf(
            Producto(28, 14, "iPhone 37", 2, 50.0, 100.0, 0.0),
            Producto(29, 14, "iPhone 38", 2, 50.0, 100.0, 0.0)
        )
    ),
    Factura(
        15, "sofia","2021-10-10", 100.0, "Efectivo", 1,
        listOf(
            Producto(30, 15, "iPhone 39", 2, 50.0, 100.0, 0.0),
            Producto(31, 15, "iPhone 40", 2, 50.0, 100.0, 0.0)
        )
    ),
) //viene del VM

//Preview para Vortex T10M (T10MPROPLUS) Horizontal
@Preview(backgroundColor = 0xFFFFFFFF, showBackground = true, widthDp = 1080, heightDp = 560)
@Composable
fun FacturacionScreenPreview() {
    //FacturacionScreen()
}

data class Factura(  // Devería ser del VM
    val noFactura: Int,
    val cliente: String,
    val fecha: String,
    val total: Double,
    val tipoPago: String,
    val idVendedor: Int,

    val productos: List<Producto>
)

data class Producto( // Devería ser del VM
    val idProducto: Int,
    val idFactura: Int,
    val nombre: String,
    val cantidad: Int,
    val precio: Double,
    val total: Double,
    val ITB: Double
)

fun List<Producto>.sumaTotal(): Double { // Devería ser del VM
    var total = 0.0
    this.forEach { total += it.total }
    return total
}
package com.solidtype.atenas_apk_2.products.domain.model

data class DataProductos(
    val nombre :String,
    val codigo: Int,
    val descripcion: String,
    val categoria: String,
    val precio_importe: Double,
    val modelo: String,
    val precio_venta: Double,
    val Marca: String,
    val Cantidad: Int
)

package com.solidtype.atenas_apk_2.servicios

val servicio = listOf(
    servicioss(
        "Johan", "reparacion", 100.0, "Abierto" ,
    ),
    servicioss(
        "Johan", "reparacion", 100.0, "Abierto" ,
    ),
    servicioss(
        "Johan", "reparacion", 100.0, "Abierto" ,
    ),
    servicioss(
        "Johan", "reparacion", 100.0, "Abierto" ,
    ),
    servicioss(
        "Johan", "reparacion", 100.0, "Abierto" ,
    ),
    servicioss(
        "Johan", "reparacion", 100.0, "Abierto" ,
    ),
    servicioss(
        "Johan", "reparacion", 100.0, "Abierto" ,
    ),
    servicioss(
        "Johan", "reparacion", 100.0, "Abierto" ,
    ),
    servicioss(
        "Johan", "reparacion", 100.0, "Abierto" ,
    ),
    servicioss(
        "Johan", "reparacion", 100.0, "Abierto" ,
    ),
    servicioss(
        "Johan", "reparacion", 100.0, "Abierto" ,
    ),
    servicioss(
        "Johan", "reparacion", 100.0, "Abierto" ,
    ),
    servicioss(
        "Johan", "reparacion", 100.0, "Abierto" ,
    ),
    servicioss(
        "Johan", "reparacion", 100.0, "Abierto" ,
    ),
    servicioss(
        "Johan", "reparacion", 100.0, "Abierto" ,
    ),
)
//@Preview(backgroundColor = 0xFFFFFFFF, showBackground = true, widthDp = 1080, heightDp = 560)
//@Composable
//fun FacturacionScreenPreview() {
//    FacturacionScreen()
//}

data class servicioss(  // Devería ser del VM
    val nombres: String,
    val servicio: String,
    val total: Double,
    val estado: String,
)

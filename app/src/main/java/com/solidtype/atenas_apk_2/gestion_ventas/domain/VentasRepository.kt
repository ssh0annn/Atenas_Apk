package com.solidtype.atenas_apk_2.gestion_ventas.domain

import com.solidtype.atenas_apk_2.products.domain.model.actualizacion.inventario
import kotlinx.coroutines.flow.Flow

interface VentasRepository {
    fun MostrarProductos(): Flow<List<inventario>>

//    2. Selecionar un producto(inventario)
//    3. Aumentar cantidad.
//    4. MostrarClientes(CasoDe_uso)
//    5. AgregarCliente.
//    6. IngresarAbono(Caso_de_uso)
//    7. ConfirmarPago

}
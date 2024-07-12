package com.solidtype.atenas_apk_2.products.presentation.inventory

import com.solidtype.atenas_apk_2.gestion_proveedores.presentation.cliente.modelo.Personastodas
import com.solidtype.atenas_apk_2.products.domain.model.actualizacion.categoria
import com.solidtype.atenas_apk_2.products.domain.model.actualizacion.inventario

sealed class InventariosEvent {

    object GetCategorias:InventariosEvent()
    data class AgregarCategorias(val catego:categoria):InventariosEvent()

    data class eliminarCategoria(val catego:categoria):InventariosEvent()

    data class ActualizarCategoria(val catego:categoria):InventariosEvent()

    data class BuscarCategoria(val any:String):InventariosEvent()


    object GetProductos:InventariosEvent()

    data class AgregarProductos(val productos:inventario):InventariosEvent()

    data class EliminarProductos(val producto:inventario):InventariosEvent()

    data class ActualizarProductos(val producto:inventario):InventariosEvent()

    data class BuscarProducto(val any:String):InventariosEvent()



    //Exel

    object Getrpoveedores:InventariosEvent()
    data class BuscarProveedores(val any:String):InventariosEvent()
    data class CrearProveedor(val provee:Personastodas.Proveedor):InventariosEvent()
    data class EliminarProveedor(val provee:Personastodas.Proveedor):InventariosEvent()

    object Switch:InventariosEvent()



}
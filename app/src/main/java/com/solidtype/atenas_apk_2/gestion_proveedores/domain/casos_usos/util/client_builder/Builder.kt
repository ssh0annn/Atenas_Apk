package com.solidtype.atenas_apk_2.gestion_proveedores.domain.casos_usos.util.client_builder

import com.solidtype.atenas_apk_2.gestion_proveedores.data.persona


interface Builder {

    fun id_persona(idPersona:Long):Builder
    fun ipo_persona(rsona:String?):Builder//proveedor, cliente
    fun nombre(nombre:String?):Builder
    fun tipo_documento (tipo_documento :String?):Builder
    fun documento (documento :String?):Builder
    fun direccion (direccion :String?):Builder
    fun telefono(telefono:String?):Builder
    fun email(email:String?):Builder
    fun estado(stado:String?):Builder

    fun reset():Builder
    fun builder(): persona


}
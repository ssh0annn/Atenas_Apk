package com.solidtype.atenas_apk_2.perfil_administrador.domain.modelo

import androidx.room.PrimaryKey
import com.solidtype.atenas_apk_2.perfil_administrador.presentation.modelo.PerfilAdmin
import com.solidtype.atenas_apk_2.perfil_administrador.presentation.modelo.VerInfoAdmin


fun administrador.toPerfil(): PerfilAdmin? {
    return this.correo?.let {
        PerfilAdmin(
        nombre = this.nombre,
        apellido = this.apellido,
        correo = it,
        telefono = this.telefono,
        direccion_negocio = this.direccion_negocio,
        nombre_negocio = this.nombre_negocio
        )
    }
}

fun administrador.toVerInfoAdmin(): VerInfoAdmin? {
    return this.estado?.let {
        VerInfoAdmin(
        id_administrador = this.id_administrador,
        nombre = this.nombre,
        apellido = this.apellido,
        correo = this.correo,
        telefono = this.telefono,
        clave = this.clave,
        direccion_negocio = this.direccion_negocio,
        nombre_negocio = this.nombre_negocio,
        licencia = this.licencia,
        fecha_compra = this.fecha_compra,
        fecha_caduca = this.fecha_caduca,
        estado = it
    )
    }
}

fun PerfilAdmin.toAdministrador(): administrador {
    return administrador(
      //  id_administrador = this.id_administrador,
        nombre=this.nombre,
        apellido=this.apellido,
        correo=this.correo,
        telefono=this.telefono,
        clave= null,
        direccion_negocio=this.direccion_negocio,
        nombre_negocio=this.nombre_negocio,
        licencia=null,
        fecha_compra=null,
        fecha_caduca=null,
        estado=null
    )



}
package com.solidtype.atenas_apk_2.perfil_administrador.domain.modelo

import com.solidtype.atenas_apk_2.perfil_administrador.presentation.modelo.VerInfoAdmin

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




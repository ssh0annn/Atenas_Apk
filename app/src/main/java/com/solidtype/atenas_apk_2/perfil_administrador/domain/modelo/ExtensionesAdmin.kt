package com.solidtype.atenas_apk_2.perfil_administrador.domain.modelo

import androidx.room.PrimaryKey
import com.solidtype.atenas_apk_2.perfil_administrador.presentation.modelo.PerfilAdmin
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

<<<<<<< HEAD
fun PerfilAdmin.toAdministrador(): administrador {
    return administrador(
        id_administrador = 5000,
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
=======
>>>>>>> 962eaf80d49e37822f832fae950a27a21e5f77cd




package com.solidtype.atenas_apk_2.gestion_proveedores.domain.modelo

import androidx.room.ColumnInfo
import androidx.room.PrimaryKey
import com.solidtype.atenas_apk_2.gestion_proveedores.domain.casos_usos.util.client_builder.Builder
import com.solidtype.atenas_apk_2.gestion_proveedores.domain.casos_usos.util.client_builder.PersonaBuilder
import com.solidtype.atenas_apk_2.gestion_proveedores.domain.casos_usos.util.client_builder.PersonaDirector

data class TipoPersona(
    val id_persona: Long? = null,
    val tipo_persona: String?,//proveedor, cliente
    val nombre: String?,
    val tipo_documento: String?,
    val documento: String?,
    val direccion: String?,
    val telefono: String?,
    val email: String?,
    val estado: Boolean?,
  ){
    companion object {
        val builder: Builder = PersonaBuilder()
    }
}

package com.solidtype.atenas_apk_2.gestion_proveedores.domain.casos_usos.util.client_builder

import com.solidtype.atenas_apk_2.gestion_proveedores.data.persona


class PersonaBuilder: Builder {
    private var id_persona: Long = 0
    private var tipo_persona: String? = "proveedor"
    private var nombre: String? = null
    private var tipo_documento: String? = null
    private var documento: String? = null
    private var direccion: String? = null
    private var telefono: String? = null
    private var email: String? = null
    private var estado: Boolean = true

    override fun id_persona(idPersona: Long): Builder {
        this.id_persona = idPersona
        return this
    }


    override fun ipo_persona(rsona: String?): Builder {
        this.tipo_persona = rsona
        return this
    }

    override fun nombre(nombre: String?): Builder {
        this.nombre = nombre
        return this
    }

    override fun tipo_documento(tipo_documento: String?): Builder {
        this.tipo_documento = tipo_documento
        return this
    }

    override fun documento(documento: String?): Builder {
        this.documento = documento
        return this
    }

    override fun direccion(direccion: String?): Builder {
        this.direccion = direccion
        return this
    }

    override fun telefono(telefono: String?): Builder {
        this.telefono = telefono
        return this
    }

    override fun email(email: String?): Builder {
        this.email = email
        return this
    }

    override fun estado(stado: String?): Builder {
        TODO("Not yet implemented")
    }

    override fun reset(): Builder {
        this.id_persona = 0
        this.nombre = null
        this.tipo_documento = null
        this.documento = null
        this.direccion = null
        this.telefono = null
        this.email = null
        this.estado = true

        return this


    }

    override fun builder(): persona {

        return persona(
            id_persona = id_persona,
            tipo_persona = tipo_persona,
            nombre = nombre,
            tipo_documento = tipo_documento,
            documento = documento,
            direccion = direccion,
            telefono = telefono,
            email = email,
            estado = estado
        )
    }
}



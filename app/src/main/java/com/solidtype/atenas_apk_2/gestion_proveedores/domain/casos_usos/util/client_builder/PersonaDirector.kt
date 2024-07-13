package com.solidtype.atenas_apk_2.gestion_proveedores.domain.casos_usos.util.client_builder

import com.solidtype.atenas_apk_2.gestion_proveedores.data.persona
import com.solidtype.atenas_apk_2.gestion_proveedores.presentation.cliente.modelo.Personastodas

class PersonaDirector {
    companion object {
        val builder: Builder = PersonaBuilder()
        fun createPersona(persona: Personastodas): persona {
            when (persona) {
                is Personastodas.ClienteUI -> {
                    if (persona.documento != null) {
                        if (!persona.email.isNullOrEmpty()) {
                            return builder
                                .reset()
                                .id_persona(persona.id_cliente)
                                .ipo_persona("cliente")
                                .nombre(persona.nombre)
                                .tipo_documento(persona.tipo_documento)
                                .documento(persona.documento)
                                .telefono(persona.telefono)
                                .email(persona.email)
                                .builder()
                        }
                        return builder
                            .reset()
                            .id_persona(persona.id_cliente)
                            .ipo_persona("cliente")
                            .nombre(persona.nombre)
                            .tipo_documento(persona.tipo_documento)
                            .documento(persona.documento)
                            .telefono(persona.telefono)
                            .builder()
                    }
                    return builder
                        .reset()
                        .id_persona(persona.id_cliente)
                        .ipo_persona("cliente")
                        .nombre(persona.nombre)
                        .telefono(persona.telefono)
                        .builder()
                }

                is Personastodas.Proveedor -> {

                    return builder
                        .reset()
                        .id_persona(persona.id_proveedor)
                        .ipo_persona("proveedor")
                        .nombre(persona.nombre)
                        .tipo_documento(persona.tipo_documento)
                        .documento(persona.documento)
                        .direccion(persona.direccion)
                        .telefono(persona.telefono)
                        .email(persona.email)
                        .builder()
                }
            }
        }

    }
    }

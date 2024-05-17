package com.solidtype.atenas_apk_2.gestion_usuarios.domain.use_cases


data class UsuarioUseCases(
    val actualizar: Actualizar,
    val agregar: Agregar,
    val eliminar: Eliminar,
    val mostrarUsuarios: MostrarUsuario,
    val buscarUsuario: Buscar,
    val getRoles: GetRoles,
    val crearRoles: CrearRoles
    )

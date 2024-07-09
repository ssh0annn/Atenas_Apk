package com.solidtype.atenas_apk_2.gestion_usuarios.presentation

import com.solidtype.atenas_apk_2.gestion_usuarios.domain.modelo.roll_usuarios
import com.solidtype.atenas_apk_2.gestion_usuarios.domain.modelo.usuario

sealed class UserEvent{
   data class BuscarUsuario(val any:String) : UserEvent()

   data class BorrarUsuario(val usuario:usuario) : UserEvent()

   data class AgregarUsuario(val usuario:usuario) : UserEvent()

   data class EditarUsuario(val usuario: usuario) : UserEvent()

   data class AgregarNuevoRol(val rol: roll_usuarios) : UserEvent()
   data class ElimnarRoll(val rol:roll_usuarios) : UserEvent()
   data class EditarRol(val rol:roll_usuarios):UserEvent()
   data class RolSelecionado(val rol : roll_usuarios) : UserEvent()
   object RestaurarUsuario : UserEvent()

   object MostrarUserEvent: UserEvent()

   object GetRoles:UserEvent()

   object GetQr:UserEvent()

   object Switch:UserEvent()


}


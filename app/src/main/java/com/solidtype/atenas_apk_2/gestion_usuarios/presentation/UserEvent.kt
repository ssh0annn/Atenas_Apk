package com.solidtype.atenas_apk_2.gestion_usuarios.presentation.usuarios

sealed class UserEvent{
   data class BuscarUsuario(val any:String) :UserEvent()

   data class BorrarUsuario(val usuario:Map<String, Any?>) : UserEvent()

   data class AgregarUsuario(val usuario:Map<String, Any?>) : UserEvent()

   data class EditarUsuario(val usuario: Map<String, Any?>) : UserEvent()
   object RestaurarUsuario : UserEvent()

   object MostrarUserEvent: UserEvent()


}


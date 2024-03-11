package com.solidtype.atenas_apk_2.users.domain.userCase

import android.util.Patterns

class ValidateFullnameUseCase {
    operator fun invoke(name:String,lastname:String):Boolean{
        return name.length in 3.. 20 && lastname.length in 3 .. 40
    }
    fun execute(name:String,lastname:String):ValidateResults{
        if(name.isBlank() && lastname.isBlank()){
            return ValidateResults(
                successful = false,
                errorMessage = "El campo de Nombre o Apellido es invalido."
            )
        }
        return ValidateResults(
            successful = true,
        )
    }
}
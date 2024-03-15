package com.solidtype.atenas_apk_2.users.domain.userCase

import com.solidtype.atenas_apk_2.users.domain.userCase.implementados.ValidateResults

class ValidateBusinessUseCase {
    operator fun invoke(name:String,addres:String):Boolean{
        return name.length in 3 .. 50 && addres.length in 3 .. 150
    }
    fun execute(name:String,addres:String): ValidateResults {
        if(name.isBlank() && addres.isBlank()){
            return ValidateResults(
                successful = false,
                errorMessage = "El campo de nombre o dirrecion de negocio es invalido."
            )
        }
        return ValidateResults(
            successful = true,
        )
    }
}
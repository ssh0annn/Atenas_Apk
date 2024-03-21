package com.solidtype.atenas_apk_2.Authentication.domain.userCase

import com.solidtype.atenas_apk_2.Authentication.domain.userCase.implementados.ValidateResults

class ValidateIdUseCase {
    operator fun invoke(id:String):Boolean{
        return id.length >= 6
    }
    fun execute(id:String): ValidateResults {
        if(id.length < 8){
            return ValidateResults(
                successful = false,
                errorMessage = "El campo sobre Id de licencia es invalido."
            )
        }
        return ValidateResults(
            successful = true,
        )
    }
}
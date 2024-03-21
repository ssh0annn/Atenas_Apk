package com.solidtype.atenas_apk_2.Authentication.domain.userCase

import com.solidtype.atenas_apk_2.Authentication.domain.userCase.implementados.ValidateResults

class ValidateRepeatedPasswordUseCase {
    operator fun invoke(password:String,repeatedPassword:String):Boolean{
        return password == repeatedPassword
    }
    fun execute(password:String,repeatedPassword:String): ValidateResults {
        if(password != repeatedPassword){
            return ValidateResults(
                successful = false,
                errorMessage = "La Contraseña no coincide, favor verificar."
            )
        }
        return ValidateResults(
            successful = true,
        )
    }
}
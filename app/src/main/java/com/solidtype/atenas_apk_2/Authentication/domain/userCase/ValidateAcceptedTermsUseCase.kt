package com.solidtype.atenas_apk_2.Authentication.domain.userCase

import com.solidtype.atenas_apk_2.Authentication.domain.userCase.implementados.ValidateResults

class ValidateAcceptedTermsUseCase {
    operator fun invoke(accepted:Boolean):Boolean{
        return accepted
    }
    fun execute(accepted:Boolean): ValidateResults {
        if(!accepted){
            return ValidateResults(
                successful = false,
                errorMessage = "Aceptar los terminos y condiciones para continuar."
            )
        }
        return ValidateResults(
            successful = true,
        )
    }
}
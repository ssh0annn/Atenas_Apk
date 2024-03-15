package com.solidtype.atenas_apk_2.users.domain.userCase

import com.solidtype.atenas_apk_2.users.domain.userCase.implementados.ValidateResults

class ValidatePhoneUseCase {
    operator fun invoke(telefono:String):Boolean{
        return telefono.length >= 5 &&
                telefono.any {it.isDigit()}
    }
    fun execute(telefono:String): ValidateResults {
        if(telefono.length < 8 && !telefono.any {it.isDigit()}){
            return ValidateResults(
                successful = false,
                errorMessage = "El campo de Telefono es invalido."
            )
        }
        return ValidateResults(
            successful = true,
        )
    }
}
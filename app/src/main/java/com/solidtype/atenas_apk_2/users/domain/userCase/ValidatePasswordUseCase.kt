package com.solidtype.atenas_apk_2.users.domain.userCase

import com.solidtype.atenas_apk_2.users.domain.userCase.implementados.ValidateResults

class ValidatePasswordUseCase {
    operator fun invoke(password:String):Boolean{
        return password.length >= 8 &&
                password.any {it.isLowerCase()}&&
                password.any {it.isDigit()}
    }
    fun execute(password:String): ValidateResults {
        if(password.length < 8 && !password.any {it.isLowerCase()}&& !password.any {it.isDigit()}) {
            return ValidateResults(
                successful = false,
                errorMessage = "El campo de Contraseña es invalido."
            )
        }
        return ValidateResults(
            successful = true,
        )
    }
}
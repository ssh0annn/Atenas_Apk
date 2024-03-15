package com.solidtype.atenas_apk_2.users.domain.userCase

import android.util.Patterns
import com.solidtype.atenas_apk_2.users.domain.userCase.implementados.ValidateResults

class ValidateEmailUseCase {
    operator fun invoke(email:String):Boolean{
        return Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }
    fun execute(email:String): ValidateResults {
        if(email.isBlank()){
            return ValidateResults(
                successful = false,
                errorMessage = "El campo de Email es invalido."
            )
        }
        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            return ValidateResults(
                successful = false,
                errorMessage = "El campo de Email es invalido."
            )
        }
        return ValidateResults(
            successful = true,
        )
    }
}
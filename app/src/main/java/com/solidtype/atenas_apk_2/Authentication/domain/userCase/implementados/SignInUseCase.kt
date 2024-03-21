package com.solidtype.atenas_apk_2.Authentication.domain.userCase.implementados

import com.solidtype.atenas_apk_2.Authentication.domain.repository.UserRepository
import javax.inject.Inject

class SignInUseCase @Inject constructor(private val repositorio:UserRepository){

    suspend operator fun invoke(email:String,password:String) : ValidateResults {


        return ValidateResults(successful =  repositorio.SignIn(email,password))

    }
}
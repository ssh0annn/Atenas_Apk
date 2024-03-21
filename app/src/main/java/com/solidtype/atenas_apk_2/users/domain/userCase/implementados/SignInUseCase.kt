package com.solidtype.atenas_apk_2.users.domain.userCase.implementados

import com.solidtype.atenas_apk_2.users.data.repository.RepositoryImpl
import com.solidtype.atenas_apk_2.users.domain.repository.UserRepository
import javax.inject.Inject

class SignInUseCase @Inject constructor(private val repositorio:UserRepository){

    suspend operator fun invoke(email:String,password:String) : ValidateResults {


        return ValidateResults(successful =  repositorio.SignIn(email,password))

    }
}
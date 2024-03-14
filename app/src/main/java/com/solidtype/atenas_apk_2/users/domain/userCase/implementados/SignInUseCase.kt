package com.solidtype.atenas_apk_2.users.domain.userCase.implementados

import com.solidtype.atenas_apk_2.users.data.repository.RepositoryImpl
import com.solidtype.atenas_apk_2.users.domain.repository.UserRepository
class SignInUseCase{
    private val repositorio:UserRepository= RepositoryImpl()
    suspend operator fun invoke(email:String,password:String) : ValidateResults {


        return ValidateResults(successful =  repositorio.SignIn(email,password))

    }
}
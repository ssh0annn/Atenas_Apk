package com.solidtype.atenas_apk_2.users.domain.userCase

import com.solidtype.atenas_apk_2.users.data.repository.RepositoryImpl
import com.solidtype.atenas_apk_2.users.domain.model.UserModel
import com.solidtype.atenas_apk_2.users.domain.repository.UserRepository
class SignInUseCase{
    private val repositorio:UserRepository=RepositoryImpl()
    suspend operator fun invoke(email:String,password:String) : ValidateResults {
        return repositorio.SignIn(email,password)
    }
}
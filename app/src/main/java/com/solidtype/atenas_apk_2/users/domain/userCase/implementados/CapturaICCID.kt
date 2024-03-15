package com.solidtype.atenas_apk_2.users.domain.userCase.implementados

import com.solidtype.atenas_apk_2.users.data.repository.RepositoryImpl
import com.solidtype.atenas_apk_2.users.domain.repository.UserRepository

class CapturaICCID (private val repository: UserRepository= RepositoryImpl()){

    suspend operator fun invoke():String{
        return repository.capturaICCID()
    }
}
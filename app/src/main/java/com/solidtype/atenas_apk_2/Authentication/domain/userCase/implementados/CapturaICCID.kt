package com.solidtype.atenas_apk_2.Authentication.domain.userCase.implementados

import com.solidtype.atenas_apk_2.Authentication.domain.repository.UserRepository
import javax.inject.Inject

class CapturaICCID @Inject constructor(private val repository: UserRepository){

    suspend operator fun invoke():String{
        return repository.capturaICCID()
    }
}
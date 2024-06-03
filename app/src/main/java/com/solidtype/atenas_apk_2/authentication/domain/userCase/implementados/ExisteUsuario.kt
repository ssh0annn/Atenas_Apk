package com.solidtype.atenas_apk_2.authentication.domain.userCase.implementados

import com.solidtype.atenas_apk_2.authentication.domain.repository.UserRepository
import javax.inject.Inject

class ExisteUsuario @Inject constructor(private val repository: UserRepository) {

    suspend operator fun invoke():Boolean{
        return repository.existeUsuario()
    }
}
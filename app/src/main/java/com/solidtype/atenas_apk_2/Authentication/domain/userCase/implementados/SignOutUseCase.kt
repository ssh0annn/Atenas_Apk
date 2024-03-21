package com.solidtype.atenas_apk_2.Authentication.domain.userCase.implementados

import com.solidtype.atenas_apk_2.Authentication.domain.repository.UserRepository
import javax.inject.Inject

class SignOutUseCase @Inject constructor(private val repositorio: UserRepository){


     operator suspend fun invoke()=repositorio.signout()


}
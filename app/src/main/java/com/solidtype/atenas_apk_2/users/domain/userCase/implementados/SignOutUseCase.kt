package com.solidtype.atenas_apk_2.users.domain.userCase.implementados

import com.solidtype.atenas_apk_2.users.data.repository.RepositoryImpl
import com.solidtype.atenas_apk_2.users.domain.repository.UserRepository
import javax.inject.Inject

class SignOutUseCase @Inject constructor(private val repositorio: UserRepository){


     operator suspend fun invoke()=repositorio.signout()


}
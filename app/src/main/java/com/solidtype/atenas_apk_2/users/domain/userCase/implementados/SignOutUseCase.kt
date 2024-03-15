package com.solidtype.atenas_apk_2.users.domain.userCase.implementados

import com.solidtype.atenas_apk_2.users.data.repository.RepositoryImpl
import com.solidtype.atenas_apk_2.users.domain.repository.UserRepository

class SignOutUseCase (private val repositorio: UserRepository = RepositoryImpl()){


     operator fun invoke()=repositorio.signout()


}
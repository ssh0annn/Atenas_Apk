package com.solidtype.atenas_apk_2.users.domain.userCase.implementados

import com.solidtype.atenas_apk_2.users.data.repository.RepositoryImpl
import com.solidtype.atenas_apk_2.users.domain.repository.UserRepository
import javax.inject.Inject

class EstadoLicencia @Inject constructor(private val repository:UserRepository) {

    suspend operator fun invoke(iccid:String)= repository.estadoDeLicencia(iccid)


}

package com.solidtype.atenas_apk_2.authentication.domain.userCase.implementados

import com.solidtype.atenas_apk_2.authentication.domain.repository.UserRepository
import javax.inject.Inject

class VerificaICCIDUseCase @Inject constructor(private val repository:UserRepository) {

    suspend operator fun invoke(iccid:String)=repository.validaICCID(iccid)
}
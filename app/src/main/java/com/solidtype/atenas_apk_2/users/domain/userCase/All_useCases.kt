package com.solidtype.atenas_apk_2.users.domain.userCase

import com.solidtype.atenas_apk_2.users.domain.userCase.implementados.CapturaICCID
import com.solidtype.atenas_apk_2.users.domain.userCase.implementados.EstadoLicencia
import com.solidtype.atenas_apk_2.users.domain.userCase.implementados.ExisteUsuario
import com.solidtype.atenas_apk_2.users.domain.userCase.implementados.Registrarse
import com.solidtype.atenas_apk_2.users.domain.userCase.implementados.SignInUseCase
import com.solidtype.atenas_apk_2.users.domain.userCase.implementados.SignOutUseCase
import com.solidtype.atenas_apk_2.users.domain.userCase.implementados.VerificaICCIDUseCase
import com.solidtype.atenas_apk_2.users.domain.userCase.implementados.getCurrentUser

data class All_useCases(
    val login: SignInUseCase = SignInUseCase(),
    val logout: SignOutUseCase= SignOutUseCase(),
    val current_user: getCurrentUser=getCurrentUser(),
    val register: Registrarse =Registrarse(),
    val capturaIccid:CapturaICCID=CapturaICCID(),
    val validarICCID: VerificaICCIDUseCase=VerificaICCIDUseCase(),
    val estado_licencia: EstadoLicencia = EstadoLicencia(),
    val usuarioExiste: ExisteUsuario =ExisteUsuario()

)

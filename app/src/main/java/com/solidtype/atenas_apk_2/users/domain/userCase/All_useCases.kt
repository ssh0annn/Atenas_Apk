package com.solidtype.atenas_apk_2.users.domain.userCase

import com.solidtype.atenas_apk_2.users.domain.userCase.implementados.CapturaICCID
import com.solidtype.atenas_apk_2.users.domain.userCase.implementados.EstadoLicencia
import com.solidtype.atenas_apk_2.users.domain.userCase.implementados.ExisteUsuario
import com.solidtype.atenas_apk_2.users.domain.userCase.implementados.Registrarse
import com.solidtype.atenas_apk_2.users.domain.userCase.implementados.SignInUseCase
import com.solidtype.atenas_apk_2.users.domain.userCase.implementados.SignOutUseCase
import com.solidtype.atenas_apk_2.users.domain.userCase.implementados.SignUpUseCase
import com.solidtype.atenas_apk_2.users.domain.userCase.implementados.VerificaICCIDUseCase
import com.solidtype.atenas_apk_2.users.domain.userCase.implementados.getCurrentUser

data class All_useCases(
    val login: SignInUseCase,
    val logout: SignOutUseCase,
    val current_user: getCurrentUser,
    val register: Registrarse,
    val capturaIccid:CapturaICCID,
    val validarICCID: VerificaICCIDUseCase,
    val estado_licencia: EstadoLicencia,
    val usuarioExiste: ExisteUsuario
)

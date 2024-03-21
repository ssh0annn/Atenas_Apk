package com.solidtype.atenas_apk_2.Authentication.domain.userCase.implementados

data class ValidateResults (
    var successful:Boolean,
    var errorMessage: String? = null
)
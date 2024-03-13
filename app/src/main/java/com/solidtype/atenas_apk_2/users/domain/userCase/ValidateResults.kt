package com.solidtype.atenas_apk_2.users.domain.userCase

data class ValidateResults (
    var successful:Boolean,
    var errorMessage: String? = null
)
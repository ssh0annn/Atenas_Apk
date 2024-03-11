package com.solidtype.atenas_apk_2.users.domain.userCase

data class ValidateResults (
    val successful:Boolean,
    val errorMessage: String? = null
)
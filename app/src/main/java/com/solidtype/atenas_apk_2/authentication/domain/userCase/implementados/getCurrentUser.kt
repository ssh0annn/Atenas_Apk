package com.solidtype.atenas_apk_2.authentication.domain.userCase.implementados

import com.google.firebase.auth.FirebaseUser
import com.solidtype.atenas_apk_2.authentication.domain.repository.UserRepository
import javax.inject.Inject

class getCurrentUser @Inject constructor(private val repository: UserRepository) {
            suspend operator fun invoke():FirebaseUser?{
               return repository.getCurrentUser()
            }

}
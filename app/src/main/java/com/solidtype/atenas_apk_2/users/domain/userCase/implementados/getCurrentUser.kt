package com.solidtype.atenas_apk_2.users.domain.userCase.implementados

import com.google.firebase.auth.FirebaseUser
import com.solidtype.atenas_apk_2.users.data.repository.RepositoryImpl
import com.solidtype.atenas_apk_2.users.domain.repository.UserRepository

class getCurrentUser(private val repository: UserRepository=RepositoryImpl()) {
            suspend operator fun invoke():FirebaseUser?{
               return repository.getCurrentUser()
            }

}
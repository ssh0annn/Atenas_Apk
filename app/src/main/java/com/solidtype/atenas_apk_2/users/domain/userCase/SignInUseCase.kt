package com.solidtype.atenas_apk_2.users.domain.userCase

import com.solidtype.atenas_apk_2.users.data.repository.RepositoryImpl
import com.solidtype.atenas_apk_2.users.domain.model.UserModel

class SignInUseCase(private val repository: RepositoryImpl) {
    private val emailValidate : ValidateEmailUseCase = ValidateEmailUseCase()
    private val passwordValida : ValidatePasswordUseCase = ValidatePasswordUseCase()
    private val repeatedPasswordValidate : ValidateRepeatedPasswordUseCase = ValidateRepeatedPasswordUseCase()
    operator fun invoke(email:String,password:String,repeatedpassword:String) : ValidateResults? {

        val user : UserModel = UserModel(null,null,email,null,password,
            null,null, null,null,null,
            null)
        if(emailValidate.invoke(email)){
            if(passwordValida.invoke(password)){
                if(repeatedPasswordValidate.invoke(password,repeatedpassword)){
                    repository.SignIn(user)
                    return ValidateResults(
                        successful = true,
                    )
                }
                return ValidateResults(
                    successful = false,
                )
            }
            return ValidateResults(
                successful = false,
            )
        }
        return ValidateResults(
            successful = false,
        )
    }
}
package com.solidtype.atenas_apk_2.users.domain.userCase

import com.solidtype.atenas_apk_2.users.data.repository.RepositoryImpl
import com.solidtype.atenas_apk_2.users.domain.model.UserModel
import com.solidtype.atenas_apk_2.users.domain.repository.UserRepository

class SignInUseCase{
    private val repository: UserRepository=RepositoryImpl()
    private val emailValidate : ValidateEmailUseCase = ValidateEmailUseCase()
    private val passwordValida : ValidatePasswordUseCase = ValidatePasswordUseCase()
    private val repeatedPasswordValidate : ValidateRepeatedPasswordUseCase = ValidateRepeatedPasswordUseCase()
    operator fun invoke(email:String,password:String,repeatedpassword:String) : ValidateResults? {

        val user : UserModel = UserModel("","",email,"",password,
            "","", "")
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
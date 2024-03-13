package com.solidtype.atenas_apk_2.users.domain.userCase

import com.solidtype.atenas_apk_2.users.data.repository.RepositoryImpl
import com.solidtype.atenas_apk_2.users.domain.model.UserModel
import com.solidtype.atenas_apk_2.users.domain.repository.UserRepository

class SignUpUseCase{
    private val repository:UserRepository= RepositoryImpl()
    private val fullnameValidate : ValidateFullnameUseCase = ValidateFullnameUseCase()
    private val emailValidate : ValidateEmailUseCase = ValidateEmailUseCase()
    private val idValidate : ValidateIdUseCase = ValidateIdUseCase()
    private val passwordValida : ValidatePasswordUseCase = ValidatePasswordUseCase()
    private val repeatedPasswordValidate : ValidateRepeatedPasswordUseCase = ValidateRepeatedPasswordUseCase()
    private val businessValidate : ValidateBusinessUseCase = ValidateBusinessUseCase()
    private val phoneValidate : ValidatePhoneUseCase = ValidatePhoneUseCase()
    operator fun invoke(name:String,lastname:String,email:String,id:String,password:String,
                        repeatedpassword:String,businessName:String,businessAddres:String,phone:String,
                        ) : ValidateResults {

        val user  = UserModel(name,lastname,email,id,password,businessName,businessAddres,
            phone)
        if(fullnameValidate.invoke(name,lastname)){
            if(emailValidate.invoke(email)){
                if(idValidate.invoke(id)){
                    if(passwordValida.invoke(password)){
                        if(repeatedPasswordValidate.invoke(password,repeatedpassword)){
                            if(businessValidate.invoke(businessName,businessAddres)){
                                if(phoneValidate.invoke(phone)){
                                    //repository.signUp("user", "a")
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
        return ValidateResults(
            successful = false,
        )
    }
}
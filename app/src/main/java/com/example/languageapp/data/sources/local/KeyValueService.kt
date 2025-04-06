package com.example.languageapp.data.sources.local

interface KeyValueService {
    var email: String?
    var onboardingPage: Int

    var signUpEmail: String
    var signUpPassword: String
    var signUpPasswordConfirm: String
    var signUpFirstName: String
    var signUpSecondName: String

}
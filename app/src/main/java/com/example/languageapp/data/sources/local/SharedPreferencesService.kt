package com.example.languageapp.data.sources.local

import android.content.Context
import androidx.core.content.edit

class SharedPreferencesService (
    context: Context
) : KeyValueService {
    private val sp = context.getSharedPreferences(
        "LanguageAppSharedPreferences",
        Context.MODE_PRIVATE
    )

    override var email: String?
        get() = sp.getString("email", null)
        set(value) = sp.edit { putString("email", value) }
    override var onboardingPage: Int
        get() = sp.getInt("onboardingPage", 0)
        set(value) =  sp.edit { putInt("onboardingPage", value) }

    override var signUpEmail: String
        get() = sp.getString("signUpEmail", "") ?: ""
        set(value) = sp.edit { putString("signUpEmail", value) }
    override var signUpPassword: String
        get() = sp.getString("signUpPassword", "") ?: ""
        set(value) = sp.edit { putString("signUpPassword", value) }
    override var signUpPasswordConfirm: String
        get() = sp.getString("signUpPasswordConfirm", "") ?: ""
        set(value) = sp.edit { putString("signUpPasswordConfirm", value) }
    override var signUpFirstName: String
        get() = sp.getString("signUpFirstName", "") ?: ""
        set(value) = sp.edit { putString("signUpFirstName", value) }
    override var signUpSecondName: String
        get() = sp.getString("signUpSecondName", "") ?: ""
        set(value) = sp.edit { putString("signUpSecondName", value) }
}
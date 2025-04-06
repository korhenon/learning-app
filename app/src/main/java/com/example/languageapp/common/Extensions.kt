package com.example.languageapp.common

fun String.isEmailValid(): Boolean {
    val regex = Regex("^[\\w.]+@(\\w+\\.)+\\w{2,4}$")
    return this.matches(regex)
}

fun String.isPasswordValid(): Boolean {
    val regex = Regex("^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[a-zA-Z]).{8,}\$")
    return this.matches(regex)
}
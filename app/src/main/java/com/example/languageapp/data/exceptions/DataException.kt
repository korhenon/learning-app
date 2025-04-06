package com.example.languageapp.data.exceptions

class DataException(
    val exceptionReason: ExceptionReason,
    cause: Throwable? = null,
) : Exception(cause)
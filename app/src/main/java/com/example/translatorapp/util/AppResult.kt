package com.example.translatorapp.util


sealed class AppResult<T>(
    val data: T? = null,
    val message: String? = null
) {


    class Done<T>(data: T) : AppResult<T>(data)
    class Error<T>(message: String) : AppResult<T>(message = message)
    class Loading<T>(val loading: Boolean = true) : AppResult<T>(null, null)

}
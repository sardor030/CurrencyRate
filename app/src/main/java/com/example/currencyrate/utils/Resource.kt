package com.example.currencyrate.utils

sealed class Resource<T> {
    class Loading<T> : Resource<T>()
    class Success<T : Any>(val data: T) : Resource<T>()
    class Failure<T : Any>(val t: Throwable) : Resource<T>()
}

package com.hamza.newsapp.util

sealed class Resource<T>(
    val data: List<T>? = null,
    val msg: String? = null
) {
    class Success<T>(data: List<T>) : Resource<T>(data)
    class Error<T>(msg: String?, data: List<T>? = null) : Resource<T>(data, msg)
    class Loading<T> : Resource<T>()
}

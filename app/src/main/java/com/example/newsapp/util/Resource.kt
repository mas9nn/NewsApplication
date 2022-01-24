package com.example.newsapp.util

import retrofit2.Response

sealed class Resource<T>(val data: T? = null, val message: String? = null) {
    class Success<T>(data: T?) : Resource<T>(data)
    class NoContent<T>(data: T? = null) : Resource<T>(data)
    class Error<T>(message: String, data: T? = null) : Resource<T>(data, message)
    class Unauthorized<T>(data: T? = null) : Resource<T>(data)
    class Loading<T>(data: T? = null) : Resource<T>(data)
    class NoInternet<T>(message: String? = null) : Resource<T>(null, message)

    fun <T : Any> checkResponse(data: Response<T>): Resource<T> {
        return if (data.isSuccessful) {
            when (data.code()) {
                in 200..202 -> Success(data.body())
                204 -> NoContent()
                else -> Error(data.message(), data.body())
            }
        } else {
            Error(data.message(), data.body())
        }
    }
}
fun <T> Response<T>.checkResponse(): Resource<T> {
    if (this.isSuccessful) {
        return when (this.code()) {
            in 200..202 -> Resource.Success(this.body())
            204 -> Resource.NoContent()
            else -> Resource.Error(this.message(), this.body())
        }
    } else {
        if (this.code() in 400..499) {
            return Resource.Unauthorized()
        }
        return Resource.Error(this.message(), this.body())
    }
}
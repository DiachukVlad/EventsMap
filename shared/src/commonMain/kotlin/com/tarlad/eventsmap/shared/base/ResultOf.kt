package com.tarlad.eventsmap.shared.base

import io.ktor.client.call.*
import io.ktor.client.statement.*
import io.ktor.http.*

sealed class ResultOf<T>() {
    data class Success<T>(val data: T) : ResultOf<T>()
    data class Error<T>(
        val exception: Throwable
    ) : ResultOf<T>()

    data class StatusError<T>(
        val statusCode: HttpStatusCode
    ) : ResultOf<T>()

    suspend fun onSuccess(callback: suspend (T) -> Unit) {
        if (this is Success<T>) {
            callback(data)
        }
    }

    suspend fun onError(callback: suspend (Throwable) -> Unit) {
        if (this is Error<T>) {
            callback(exception)
        }
    }

    suspend fun onStatusError(callback: suspend (HttpStatusCode) -> Unit) {
        if (this is StatusError<T>) {
            callback(statusCode)
        }
    }

    suspend fun onAnyError(callback: suspend (HttpStatusCode?, Throwable?) -> Unit) {
        if (this is StatusError<T>) {
            callback(statusCode, null)
        } else if (this is Error<T>) {
            callback(null, exception)
        }
    }
}

suspend inline fun <reified T> HttpResponse.handleResult(): ResultOf<T> =
    if (call.response.status == HttpStatusCode.OK) {
        try {
            ResultOf.Success(body())
        } catch (e: Throwable) {
            ResultOf.Error(e)
        }
    } else {
        ResultOf.StatusError(call.response.status)
    }
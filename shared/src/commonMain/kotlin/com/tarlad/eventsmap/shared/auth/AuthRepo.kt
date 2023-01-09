package com.tarlad.eventsmap.shared.auth

import com.tarlad.eventsmap.shared.ClientConstants.SERVER_URL
import com.tarlad.eventsmap.shared.base.ResultOf
import com.tarlad.eventsmap.shared.base.handleResult
import io.ktor.client.*
import io.ktor.client.request.*
import io.ktor.http.*

class AuthRepo(private val client: HttpClient) {
    suspend fun signUp(credentials: User): ResultOf<User> =
        client.post("$SERVER_URL/registerStudent") {
            contentType(ContentType.Application.Json)
            setBody(credentials)
        }.handleResult()

    suspend fun signIn(credentials: User): ResultOf<User> =
        client.post("$SERVER_URL/login") {
            contentType(ContentType.Application.Json)
            setBody(credentials)
        }.handleResult()

}

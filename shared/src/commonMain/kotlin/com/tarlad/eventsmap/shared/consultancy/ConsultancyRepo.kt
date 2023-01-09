package com.tarlad.eventsmap.shared.consultancy

import io.ktor.client.*

class ConsultancyRepo(private val client: HttpClient) {
    suspend fun getAllConsultancy(): List<Consultancy> {
        return listOf(
            Consultancy(
                name="Vlad Industries"
            ),
            Consultancy(
                name="Taras Industries"
            ),
            Consultancy(
                name="Anime Industries"
            )
        )
    }
}

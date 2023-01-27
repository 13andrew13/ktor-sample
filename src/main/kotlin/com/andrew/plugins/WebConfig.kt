package com.andrew.plugins

import io.ktor.http.*
import io.ktor.serialization.jackson.*
import io.ktor.server.application.*
import io.ktor.server.plugins.contentnegotiation.*
import io.ktor.server.plugins.statuspages.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

object WebConfig {

    fun Application.webConfig() {
        install(ContentNegotiation) {
            jackson()
        }
        install(StatusPages) {
            exception<IllegalArgumentException> { call, cause ->
                cause.printStackTrace()
                call.respondText(text = "400: ${cause.message}", status = HttpStatusCode.BadRequest)
            }
            exception<Throwable> { call, cause ->
                cause.printStackTrace()
                call.respondText(text = "500: ${cause.message}", status = HttpStatusCode.InternalServerError)
            }
        }
    }
}
package com.andrew

import com.andrew.domain.user.UserModule.userModule
import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import com.andrew.plugins.DatabaseConfig.databaseConfig
import com.andrew.plugins.WebConfig.webConfig


fun main(args: Array<String>) {
    embeddedServer(
        Netty,
        port = 8080,
        host = "0.0.0.0",
        module = Application::module,
    )
        .start(wait = true)
}

fun Application.module() {
    webConfig()
    databaseConfig()
    userModule()

}


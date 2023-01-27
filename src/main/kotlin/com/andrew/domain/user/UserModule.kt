package com.andrew.domain.user

import com.andrew.domain.user.UserRouting.userRouting
import io.ktor.server.application.*
import io.ktor.server.routing.*

object UserModule {
    val userRepository = UserRepository()
    val userService = UserService(userRepository = userRepository)

    fun Application.userModule() {
        routing {
            userRouting(userService)
        }
    }
}
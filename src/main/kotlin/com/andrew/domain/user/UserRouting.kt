package com.andrew.domain.user

import com.andrew.domain.user.domain.UserCreateRequest
import com.andrew.domain.user.domain.UserUpdateRequest
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

object UserRouting {
    fun Route.userRouting(userService: UserService) {
        get("/api/users") {
            call.respond(userService.getAllUsers())
        }

        get("/api/user/{id}") {
            call.parameters["id"]?.toInt()
                ?.let { userService.getUser(it) }
                ?.let { call.respond(it) }
                ?: throw IllegalArgumentException("Value for id is missing")
        }

        delete("/api/user/{id}") {
            call.parameters["id"]?.toInt()
                ?.let { userService.deleteUser(it) }
                ?.let { call.respond(it) }
                ?: throw IllegalArgumentException("Value for id is missing")
        }

        put("/api/user/{id}") {
            val userId = call.parameters["id"]?.toInt() ?: throw IllegalArgumentException("Value for id is missing")
            val request = call.receive<UserUpdateRequest>()
            val updatedUser = userService.updateUser(userId, request)
            call.respond(updatedUser)
        }

        post("/api/user") {
            val request = call.receive<UserCreateRequest>()
            val updatedUser = userService.createUser(request)
            call.respond(updatedUser)
        }
    }
}
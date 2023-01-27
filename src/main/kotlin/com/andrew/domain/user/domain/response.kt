package com.andrew.domain.user.domain

data class UserResponse(
    val id: Int,
    val name: String,
    val surname: String,
    val email: String,
)

fun User.toDto() = UserResponse(id!!, name, surname, email)

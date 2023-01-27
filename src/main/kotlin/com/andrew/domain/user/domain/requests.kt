package com.andrew.domain.user.domain

data class UserCreateRequest(
    val name: String,
    val surname: String,
    val email: String,
)

fun UserCreateRequest.toUser() = User(
    name = name,
    email = email,
    surname = surname,
)

data class UserUpdateRequest(
    val name: String? = null,
    val surname: String? = null,
)

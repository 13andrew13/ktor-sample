package com.andrew.domain.user.domain

import kotlinx.serialization.Serializable

data class User(
    val id: Int? = null,
    val name: String,
    val surname: String,
    val email: String,
    val password: String,
)

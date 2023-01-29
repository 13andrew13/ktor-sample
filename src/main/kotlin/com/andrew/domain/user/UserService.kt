package com.andrew.domain.user

import com.andrew.domain.user.domain.*

class UserService(
    val userRepository: UserRepository,
) {
    suspend fun getUser(id: Int): User =
        userRepository.findById(id)
            ?: throw IllegalArgumentException("There is no user with such id: $id")

    suspend fun getAllUsers(): List<User> = userRepository.findAll()

    suspend fun deleteUser(id: Int): String =
        userRepository.findById(id)
            ?.let { userRepository.deleteUser(it.id!!) }
            ?.let { "User with id $id is deleted" }
            ?: "There is not user user with id: $id"

    suspend fun updateUser(id: Int, updateRequest: UserUpdateRequest): UserResponse =
        userRepository.findById(id)
            ?.let {
                userRepository.updateUser(
                    it.copy(
                        name = updateRequest.name ?: it.name,
                        surname = updateRequest.surname ?: it.surname,
                    )
                )
            }
            ?.let { userRepository.findById(id) }
            ?.toDto()
            ?: throw IllegalArgumentException("Cannot find user with id: $id")


    suspend fun createUser(request: UserCreateRequest): UserResponse =
        userRepository.findByEmail(request.email)
            ?.let { throw IllegalArgumentException("User with email ${request.email} already exists") }
            ?: request.toUser()
                .let { userRepository.insertUser(it) }
                .toDto()


}
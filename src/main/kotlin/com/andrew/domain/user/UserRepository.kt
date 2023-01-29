package com.andrew.domain.user

import com.andrew.domain.user.domain.User
import com.andrew.plugins.DatabaseConfig.dbQuery
import com.andrew.plugins.migrations.UserTable
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq


class UserRepository {

    suspend fun findAll(): List<User> = dbQuery {
        UserTable.selectAll().map { it.toUser() }
    }

    suspend fun findById(id: Int): User? = dbQuery {
        UserTable.select { UserTable.id eq id }.map { it.toUser() }.firstOrNull()
    }

    suspend fun updateUser(user: User): Int = dbQuery {
        UserTable.update ({ UserTable.id eq user.id!! }) {
            it[email] = user.email
            it[name] = user.name
            it[surname] = user.surname
        }
    }

    suspend fun insertUser(user: User) = dbQuery {
        UserTable.insert {
            it[email] = user.email
            it[name] = user.name
            it[surname] = user.surname
            it[password] = user.password
         }
            .resultedValues
            ?.map { it.toUser() }
            ?.first()!!
    }

    suspend fun deleteUser(id: Int) = dbQuery {
        UserTable.deleteWhere { UserTable.id eq id }
    }

    suspend fun findByEmail(email: String) = dbQuery {
        UserTable.select { UserTable.email eq email }.map { it.toUser() }.firstOrNull()
    }

    private fun ResultRow.toUser() = User(
        id = this[UserTable.id].toInt(),
        name = this[UserTable.name].toString(),
        surname = this[UserTable.surname].toString(),
        email = this[UserTable.email].toString(),
        password = this[UserTable.password].toString()
    )


}
package com.andrew.plugins.migrations

import org.jetbrains.exposed.sql.Table

object UserTable : Table() {
    val id = integer("id").autoIncrement()
    val name = varchar("name", 255)
    val surname = varchar("surname", 255)
    val email = varchar("email", 255)

    override val primaryKey = PrimaryKey(id)
}
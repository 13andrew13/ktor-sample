package com.andrew.plugins.migrations

import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.transactions.transaction

object MigrationFactory {

    fun applyMigration(){
        transaction{
            SchemaUtils.createMissingTablesAndColumns(UserTable)
        }
    }
}
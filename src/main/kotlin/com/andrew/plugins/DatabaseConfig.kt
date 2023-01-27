package com.andrew.plugins

import com.andrew.plugins.migrations.MigrationFactory
import kotlinx.coroutines.Dispatchers
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.transactions.experimental.newSuspendedTransaction

object DatabaseConfig {

    fun databaseConfig() {
        val url = "jdbc:postgresql://localhost:5432/postgres"
        val username = "postgres"
        val password = "password"
        Database.connect(url = url, driver = "org.postgresql.Driver", user = username, password = password)
        MigrationFactory.applyMigration()
    }

    suspend fun <T> dbQuery(block: suspend () -> T): T =
        newSuspendedTransaction(Dispatchers.IO) { block() }
}





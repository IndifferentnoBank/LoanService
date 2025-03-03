package ru.bezdar.bank.data.database.common

import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.Transaction
import org.jetbrains.exposed.sql.transactions.experimental.newSuspendedTransaction

interface DatabaseDataSourse {

    val database: Database

    suspend fun <T> dbQuery(expression: suspend Transaction.() -> T): T = newSuspendedTransaction(db = database) {
        expression()
    }

    suspend fun dbQueryWithoutResult(expression: suspend Transaction.() -> Unit) =
        dbQuery(expression)
}

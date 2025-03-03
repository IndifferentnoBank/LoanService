package ru.bezdar.bank.data.database.loan

import org.jetbrains.exposed.sql.Database
import ru.bezdar.bank.data.database.common.DatabaseDataSourse
import ru.bezdar.bank.domain.loan.LoanDbDataSource

class LoanDbDataSourceImpl(override val database: Database) : LoanDbDataSource, DatabaseDataSourse {
}

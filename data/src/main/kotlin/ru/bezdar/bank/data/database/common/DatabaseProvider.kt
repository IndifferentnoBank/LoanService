package ru.bezdar.bank.data.database.common

import com.zaxxer.hikari.HikariDataSource
import org.flywaydb.core.Flyway
import org.jetbrains.exposed.sql.Database
import javax.sql.DataSource

object DatabaseProvider {

    private const val DRIVER = "org.postgresql.Driver"
    private const val TRANSACTION_ISOLATION = "TRANSACTION_REPEATABLE_READ"

    lateinit var database: Database

    fun getDataSource(config: DatabaseConfig) =
        HikariDataSource().apply {
            jdbcUrl = config.jdbcUrl
            username = config.username
            password = config.password
            maximumPoolSize = config.connectionLimit
            transactionIsolation = TRANSACTION_ISOLATION
            driverClassName = DRIVER
            isAutoCommit = false
            validate()
        }

    fun getDatabase(dataSourse: HikariDataSource) = Database.connect(dataSourse).also { database = it }

    fun runMigration(dataSource: DataSource) {
        Flyway.configure().baselineOnMigrate(true).baselineVersion("0").dataSource(dataSource).load().migrate()
    }
}

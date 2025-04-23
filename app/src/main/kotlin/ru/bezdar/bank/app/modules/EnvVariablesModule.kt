package ru.bezdar.bank.app.modules

import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module
import ru.bezdar.bank.app.api.common.auth.JwtConfig
import ru.bezdar.bank.data.common.EnvVariablesReader
import ru.bezdar.bank.data.database.common.DatabaseConfig

val envVariablesModule = module {
    singleOf(::createJwtConfig)
    singleOf(::createDatabaseConfig)
}

private fun createJwtConfig(): JwtConfig = JwtConfig(
    accessTokenSecret = EnvVariablesReader.accessTokenSecret,
    accessTokenValidity = EnvVariablesReader.accessTokenValidity,
)

private fun createDatabaseConfig(): DatabaseConfig = DatabaseConfig(
    jdbcUrl = EnvVariablesReader.jdbcURL,
    username = EnvVariablesReader.username,
    password = EnvVariablesReader.password,
    connectionLimit = EnvVariablesReader.connectionLimit,
)

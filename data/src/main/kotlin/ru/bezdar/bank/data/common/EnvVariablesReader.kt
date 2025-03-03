package ru.bezdar.bank.data.common

object EnvVariablesReader {

    private const val KEY_DATABASE_JDBC_URL = "DATABASE_JDBC_URL"
    private const val KEY_DATABASE_USERNAME = "DATABASE_USERNAME"
    private const val KEY_DATABASE_PASSWORD = "DATABASE_PASSWORD"
    private const val KEY_DATABASE_CONNECTION_LIMIT = "DATABASE_CONNECTION_LIMIT"

    val jdbcURL: String get() = System.getenv(KEY_DATABASE_JDBC_URL)
    val username: String get() = System.getenv(KEY_DATABASE_USERNAME)
    val password: String get() = System.getenv(KEY_DATABASE_PASSWORD)
    val connectionLimit get() = System.getenv(KEY_DATABASE_CONNECTION_LIMIT).toInt()
}

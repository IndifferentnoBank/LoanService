package ru.bezdar.bank.app.api.common.auth

data class JwtConfig(
    val accessTokenSecret: String,
    val accessTokenValidity: Long,
)

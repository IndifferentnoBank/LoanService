package ru.bezdar.bank.app.api.common.token

import com.auth0.jwt.JWT
import com.auth0.jwt.JWTVerifier
import com.auth0.jwt.algorithms.Algorithm
import com.auth0.jwt.interfaces.Payload
import ru.bezdar.bank.app.api.common.auth.JwtConfig
import ru.bezdar.bank.app.api.common.model.IdDto
import ru.bezdar.bank.app.common.extentions.toUUIDOrNull

class JwtTokenManagerImpl(private val config: JwtConfig) : TokenValidator {
    private val accessTokenAlgorithm = Algorithm.HMAC256(config.accessTokenSecret.toByteArray())

    override val accessTokenVerifier: JWTVerifier = JWT.require(accessTokenAlgorithm).build()

    override fun getUserIdFromPayload(payload: Payload): IdDto? =
        payload.claims?.get(KEY_CLAIM_USER)?.asString()?.toUUIDOrNull()?.let { IdDto(it) }

    private companion object {
        const val KEY_CLAIM_USER = "userId"
    }
}

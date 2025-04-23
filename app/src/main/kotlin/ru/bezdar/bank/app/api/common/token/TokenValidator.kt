package ru.bezdar.bank.app.api.common.token

import com.auth0.jwt.JWTVerifier
import com.auth0.jwt.interfaces.Payload
import ru.bezdar.bank.app.api.common.model.IdDto

interface TokenValidator {
    val accessTokenVerifier: JWTVerifier

    fun getUserIdFromPayload(payload: Payload): IdDto?
}

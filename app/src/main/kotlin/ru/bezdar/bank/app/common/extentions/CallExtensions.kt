package ru.bezdar.bank.app.common.extentions

import io.ktor.http.HttpStatusCode
import io.ktor.server.application.ApplicationCall
import io.ktor.server.auth.principal
import io.ktor.server.request.authorization
import io.ktor.server.request.receive
import io.ktor.server.response.respond
import ru.bezdar.bank.app.api.common.ErrorResponse
import ru.bezdar.bank.app.api.common.auth.UserPrincipal
import ru.bezdar.bank.app.common.validation.Validated
import ru.bezdar.bank.domain.common.error.InvalidRefreshToken
import kotlin.also

suspend inline fun <reified T : Validated<*>> ApplicationCall.receiveAndValidate(): T =
    this.receive<T>().also { it.validate() }

suspend inline fun <reified T : Any> ApplicationCall.respondSuccess(data: T) =
    this.respond(status = HttpStatusCode.OK, message = data)

suspend inline fun <reified T : Any> ApplicationCall.respondCreated(data: T) =
    this.respond(status = HttpStatusCode.Created, message = data)

suspend inline fun ApplicationCall.respondNoContent() =
    this.respond(status = HttpStatusCode.NoContent, message = Unit)

suspend fun ApplicationCall.respondError(status: HttpStatusCode, errorCode: String, message: String?) =
    this.respond(status = status, message = ErrorResponse(code = errorCode, message = message))

fun ApplicationCall.getAuthorizationToken() = this.request.authorization().orEmpty().removePrefix(AUTHORIZATION_PREFIX)

fun ApplicationCall.getAuthorizedUserId() = this.principal<UserPrincipal>()?.userId ?: throw InvalidRefreshToken()

private const val AUTHORIZATION_PREFIX = "Bearer "

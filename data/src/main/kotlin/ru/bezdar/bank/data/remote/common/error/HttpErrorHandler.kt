package ru.bezdar.bank.data.remote.common.error

import io.ktor.client.statement.HttpResponse

interface HttpErrorHandler {

    suspend fun handleResponse(response: HttpResponse)
    suspend fun handleException(throwable: Throwable)
}

package ru.bezdar.bank.data.remote.common

import io.ktor.client.HttpClient
import io.ktor.client.engine.cio.CIO
import io.ktor.client.plugins.HttpResponseValidator
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.DEFAULT
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.request.header
import io.ktor.http.ContentType
import io.ktor.http.contentType
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import ru.bezdar.bank.data.remote.common.error.HttpErrorHandler

open class HttpClientBuilder {

    private val loggingConfigBuilder: Logging.Config.() -> Unit = {
        logger = Logger.DEFAULT
        level = LogLevel.BODY
    }
    private var baseUrl: String? = null
    private val headersMap: MutableMap<String, String> = mutableMapOf()
    private var httpErrorHandler: HttpErrorHandler? = null

    open fun setBaseUrl(url: String): HttpClientBuilder {
        baseUrl = url
        return this
    }

    fun setHttpErrorHandler(handler: HttpErrorHandler): HttpClientBuilder {
        httpErrorHandler = handler
        return this
    }

    open fun build(): HttpClient {
        return HttpClient(CIO) {
            install(Logging, loggingConfigBuilder)

            install(ContentNegotiation) {
                json(json = Json {
                    ignoreUnknownKeys = true
                })
            }

            HttpResponseValidator {
                validateResponse { response ->
                    httpErrorHandler?.handleResponse(response)
                }

                handleResponseExceptionWithRequest { error, _ ->
                    httpErrorHandler?.handleException(error)
                }
            }

            defaultRequest {
                baseUrl?.let { url(it) }

                contentType(ContentType.Application.Json)
                headersMap.forEach { (header, value) ->
                    header(header, value)
                }
            }
        }
    }
}

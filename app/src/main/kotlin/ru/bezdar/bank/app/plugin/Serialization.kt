package ru.bezdar.bank.app.plugin

import io.ktor.serialization.kotlinx.json.json
import io.ktor.server.application.Application
import io.ktor.server.application.install
import io.ktor.server.plugins.contentnegotiation.ContentNegotiation
import kotlinx.serialization.json.Json
import kotlinx.serialization.modules.SerializersModule
import kotlinx.serialization.modules.contextual
import ru.bezdar.bank.app.common.serializers.UUIDAsStringSerializer

fun Application.configureSerialization() {
    install(ContentNegotiation) {
        json(Json {
            serializersModule = SerializationConfig.serializersModule
            ignoreUnknownKeys = true
        })
    }
}

object SerializationConfig {
    val serializersModule = SerializersModule {
        contextual(UUIDAsStringSerializer)
    }
}

package ru.bezdar.bank.app.plugin

import io.ktor.server.application.Application
import io.ktor.server.application.install
import io.ktor.server.resources.Resources
import io.ktor.server.routing.route
import io.ktor.server.routing.routing
import ru.bezdar.bank.app.api.loan.configureLoanRouting
import ru.bezdar.bank.app.api.tariff.configureTariffRouting

fun Application.configureRouting() {
    install(Resources) {
        serializersModule = SerializationConfig.serializersModule
    }

    routing {
        route("api") {
            route("loan-service") {
                configureLoanRouting()
                configureTariffRouting()
            }
        }
    }
}

package ru.bezdar.bank.app

import io.ktor.server.application.Application
import io.ktor.server.netty.EngineMain
import ru.bezdar.bank.app.plugin.configureCors
import ru.bezdar.bank.app.plugin.configureFlyway
import ru.bezdar.bank.app.plugin.configureKoin
import ru.bezdar.bank.app.plugin.configureMonitoring
import ru.bezdar.bank.app.plugin.configureRouting
import ru.bezdar.bank.app.plugin.configureSerialization
import ru.bezdar.bank.app.plugin.configureStatusPages

fun main(args: Array<String>) = EngineMain.main(args)

@Suppress("unused")
fun Application.module() {
    configureCors()
    configureKoin()
    configureFlyway()
    configureMonitoring()
    configureRouting()
    configureSerialization()
    configureStatusPages()
}

package ru.bezdar.bank.app.plugin

import io.ktor.server.application.Application
import org.koin.ktor.plugin.koin
import ru.bezdar.bank.app.modules.KoinModules

fun Application.configureKoin() {
    koin {
        modules(KoinModules.all)
    }
}

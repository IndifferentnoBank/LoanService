package ru.bezdar.bank.app.modules

import org.koin.core.module.dsl.binds
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module
import ru.bezdar.bank.app.api.common.token.JwtTokenManagerImpl
import ru.bezdar.bank.app.api.common.token.TokenValidator

val authModule = module {
    singleOf(::JwtTokenManagerImpl) {
        binds(listOf(TokenValidator::class))
    }
}

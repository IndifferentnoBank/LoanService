package ru.bezdar.bank.app.modules

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import org.koin.core.qualifier.qualifier
import org.koin.dsl.bind
import org.koin.dsl.module

val coroutineModule = module {
    single(qualifier("DefaultDispatcher")) { Dispatchers.IO } bind CoroutineDispatcher::class
}

package ru.bezdar.bank.app.modules

import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module
import ru.bezdar.bank.data.database.common.DatabaseProvider

val databaseModule = module {
    singleOf(DatabaseProvider::getDataSource)
    singleOf(DatabaseProvider::getDatabase)
}

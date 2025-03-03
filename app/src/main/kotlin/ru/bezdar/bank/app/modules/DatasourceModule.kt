package ru.bezdar.bank.app.modules

import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.bind
import org.koin.dsl.module
import ru.bezdar.bank.data.database.loan.LoanDbDataSourceImpl
import ru.bezdar.bank.data.database.tariff.TariffDbDataSourceImpl
import ru.bezdar.bank.domain.loan.LoanDbDataSource
import ru.bezdar.bank.domain.tariff.TariffDbDataSource

val datasourceModule = module {
    // region Database
    factoryOf(::TariffDbDataSourceImpl) bind TariffDbDataSource::class
    factoryOf(::LoanDbDataSourceImpl) bind LoanDbDataSource::class
    // endregion
}

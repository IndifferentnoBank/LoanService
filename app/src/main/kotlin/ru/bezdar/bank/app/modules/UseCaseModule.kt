package ru.bezdar.bank.app.modules

import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.bind
import org.koin.dsl.module
import ru.bezdar.bank.domain.tariff.usecase.CreateTariffUseCase
import ru.bezdar.bank.domain.tariff.usecase.CreateTariffUseCaseImpl
import ru.bezdar.bank.domain.tariff.usecase.GetTariffUseCase
import ru.bezdar.bank.domain.tariff.usecase.GetTariffUseCaseImpl

val useCaseModule = module {

    // region Tariff
    factoryOf(::CreateTariffUseCaseImpl) bind CreateTariffUseCase::class
    factoryOf(::GetTariffUseCaseImpl) bind GetTariffUseCase::class
    // endregion
}

package ru.bezdar.bank.app.modules

import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.bind
import org.koin.dsl.module
import ru.bezdar.bank.domain.tariff.usecase.CreateTariffUseCase
import ru.bezdar.bank.domain.tariff.usecase.CreateTariffUseCaseImpl
import ru.bezdar.bank.domain.tariff.usecase.DeleteTariffUseCase
import ru.bezdar.bank.domain.tariff.usecase.DeleteTariffUseCaseImpl
import ru.bezdar.bank.domain.tariff.usecase.GetTariffUseCase
import ru.bezdar.bank.domain.tariff.usecase.GetTariffUseCaseImpl
import ru.bezdar.bank.domain.tariff.usecase.UpdateTariffUseCase
import ru.bezdar.bank.domain.tariff.usecase.UpdateTariffUseCaseImpl

val useCaseModule = module {

    // region Tariff
    factoryOf(::CreateTariffUseCaseImpl) bind CreateTariffUseCase::class
    factoryOf(::DeleteTariffUseCaseImpl) bind DeleteTariffUseCase::class
    factoryOf(::GetTariffUseCaseImpl) bind GetTariffUseCase::class
    factoryOf(::UpdateTariffUseCaseImpl) bind UpdateTariffUseCase::class
    // endregion
}

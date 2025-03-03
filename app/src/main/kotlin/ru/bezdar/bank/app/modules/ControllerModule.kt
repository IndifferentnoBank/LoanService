package ru.bezdar.bank.app.modules

import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module
import ru.bezdar.bank.app.api.loan.controller.LoanController
import ru.bezdar.bank.app.api.tariff.controller.TariffController

val controllerModule = module {
    factoryOf(::LoanController)
    factoryOf(::TariffController)
}

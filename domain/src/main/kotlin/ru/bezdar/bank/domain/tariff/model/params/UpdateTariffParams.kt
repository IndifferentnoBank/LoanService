package ru.bezdar.bank.domain.tariff.model.params

import ru.bezdar.bank.domain.common.model.Id
import ru.bezdar.bank.domain.tariff.model.Tariff

data class UpdateTariffParams(
    val tariffId: Id<Tariff>,
    val name: String,
    val interestRate: Double,
)

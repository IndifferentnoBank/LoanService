package ru.bezdar.bank.domain.tariff.model

import ru.bezdar.bank.domain.common.model.Id

data class Tariff(
    val id: Id<Tariff>,
    val name: String,
    val interestRate: Double,
)

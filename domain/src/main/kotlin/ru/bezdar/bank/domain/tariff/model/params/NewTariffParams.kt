package ru.bezdar.bank.domain.tariff.model.params

data class NewTariffParams(
    val name: String,
    val interestRate: Double,
)

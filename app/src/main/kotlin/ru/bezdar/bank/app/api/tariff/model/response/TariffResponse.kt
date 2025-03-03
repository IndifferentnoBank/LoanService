package ru.bezdar.bank.app.api.tariff.model.response

import kotlinx.serialization.Serializable
import ru.bezdar.bank.app.api.common.model.IdDto
import ru.bezdar.bank.app.api.common.model.toResponse
import ru.bezdar.bank.domain.tariff.model.Tariff

@Serializable
data class TariffResponse(
    val id: IdDto,
    val name: String,
    val interestRate: Double,
)

fun Tariff.toResponse() = TariffResponse(
    id = id.toResponse(),
    name = name,
    interestRate = interestRate,
)
